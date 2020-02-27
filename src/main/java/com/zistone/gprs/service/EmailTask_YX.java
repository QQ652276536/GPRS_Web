package com.zistone.gprs.service;

import com.zistone.gprs.bean.LocationInfo;
import com.zistone.gprs.util.MyIMAPReceiveMailUtil;
import com.zistone.gprs.util.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;

public class EmailTask_YX
{
    private static Logger _logger = LoggerFactory.getLogger(EmailTask_YX.class);
    private List<LocationInfo> _locationInfoList;
    private MyIMAPReceiveMailUtil.IMAPListener _imapListener;

    public EmailTask_YX() throws Exception
    {
        InitListener();
        Message[] messages = MyIMAPReceiveMailUtil.Init("zstwx9xx@163.com", "123456a", _imapListener);
        MyIMAPReceiveMailUtil.Receive(messages);
    }

    private void InitListener()
    {
        _imapListener = new MyIMAPReceiveMailUtil.IMAPListener()
        {
            @Override
            public void ParseMessage(Message[] messages)
            {
                if (messages == null || messages.length < 1)
                {
                    _logger.warn(">>>没有要解析的邮件!");
                }
                _locationInfoList = new ArrayList<>();
                //遍历所有邮件
                for (int i = 0, count = messages.length; i < count; i++)
                {
                    try
                    {
                        MimeMessage msg = (MimeMessage) messages[i];
                        boolean isReadFlag = MyIMAPReceiveMailUtil.IsSeen(msg);
                        //只解析铱星网关发过来的未读邮件
                        if (!isReadFlag && MyIMAPReceiveMailUtil.GetFrom(msg).equals("<sbdservice@sbd.iridium.com>"))
                        {
                            //设为已读
                            msg.setFlag(Flags.Flag.SEEN, true);
                            _logger.info(String.format(">>>------------------解析第%d封邮件------------------", msg.getMessageNumber()));
                            String theme = MyIMAPReceiveMailUtil.GetSubject(msg);
                            _logger.info(">>>主题:" + theme);
                            _logger.info(">>>发件人:" + MyIMAPReceiveMailUtil.GetFrom(msg));
                            _logger.info(">>>收件人:" + MyIMAPReceiveMailUtil.GetReceiveAddress(msg, null));
                            _logger.info(">>>发送时间:" + MyIMAPReceiveMailUtil.GetSentDate(msg, null));
                            _logger.info(">>>是否已读:" + isReadFlag);
                            _logger.info(">>>邮件优先级:" + MyIMAPReceiveMailUtil.GetPriority(msg));
                            _logger.info(">>>是否需要回执:" + MyIMAPReceiveMailUtil.IsReplySign(msg));
                            _logger.info(String.format(">>>邮件大小:%d B", msg.getSize()));
                            //是否包含附件
                            boolean flag = MyIMAPReceiveMailUtil.IsContainAttachment(msg);
                            _logger.info(">>>是否包含附件:" + flag);
                            if (flag)
                            {
                                //保存附件
                                MyIMAPReceiveMailUtil.SaveAttachment(msg, String.format("C:\\Users\\zistone\\Desktop\\YX_Email\\", msg.getSubject()));
                            }
                            StringBuffer content = new StringBuffer();
                            MyIMAPReceiveMailUtil.GetMailTextContent(msg, content);
                            //截取经纬度和辐射半径
                            String tempContent = content.toString();
                            if (tempContent.contains("Lat") && tempContent.contains("Long"))
                            {
                                tempContent = tempContent.replace("\r\n", "");
                                tempContent = tempContent.replace("\n", "");
                                int index1 = tempContent.indexOf("Lat = ");
                                int index2 = tempContent.indexOf(" Long = ");
                                int index3 = tempContent.indexOf("CEPradius = ");
                                String latStr = tempContent.substring(index1, index2).split("Lat = ")[1];
                                String lotStr = tempContent.substring(index2, index3).split(" Long = ")[1];
                                String radiusStr = tempContent.substring(index3).split("CEPradius = ")[1];
                                String deviceId = theme.split("SBD Msg From Unit: ")[1];
                                LocationInfo locationInfo = new LocationInfo();
                                locationInfo.setDeviceId(deviceId);
                                locationInfo.setLat(Double.valueOf(latStr));
                                locationInfo.setLot(Double.valueOf(lotStr));
                                _locationInfoList.add(locationInfo);
                            }
                            _logger.info(String.format(">>>邮件正文:\n", (content.length() > 500 ? content.substring(0, 500) + "..." : content)));
                        }
                        else
                        {
                            _logger.info(">>>该邮件与发件人不符,不解析!");
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                if (_locationInfoList.size() > 0)
                {
                    //获取Spring管理的Service
                    LocationInfoService locationInfoService = ServiceUtil.GetBean("locationInfoService", LocationInfoService.class);
                    int count = locationInfoService.InsertList(_locationInfoList);
                    _logger.info(String.format(">>>本次共新增%d条历史位置信息", count));
                }
            }
        };
    }
}
