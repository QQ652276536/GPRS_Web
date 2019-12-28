package com.zistone.gprs.file_listener;

import com.zistone.gprs.bean.LocationInfo;
import com.zistone.gprs.service.LocationInfoService;
import com.zistone.gprs.service.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public class FileContentEvent_GPRS extends ApplicationEvent
{
    private static int LINECOUNT;
    private Logger m_logger = LoggerFactory.getLogger(this.getClass());
    private static final SimpleDateFormat SIMPLEDATEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private FileData m_fileData;
    private Timer m_timer = new Timer();

    public FileContentEvent_GPRS(FileData fileData)
    {
        super(fileData);
        this.m_fileData = fileData;
        ReadFileThread readFileThread = new ReadFileThread();
        readFileThread.start();
        m_logger.info(String.format(">>>线程%s执行...", readFileThread.getId()));
    }

    public FileData GetFileData()
    {
        return m_fileData;
    }

    public void ReadAllFile()
    {
        File file = new File(m_fileData.getM_path());
        FileInputStream fileInputStream;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        int lineCount;
        try
        {
            fileInputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            //过滤空行
            Stream<String> streams = bufferedReader.lines().filter(p -> p != null && !"".equals(p) && p.contains("L"));
            String[] array = streams.toArray(String[]::new);
            lineCount = array.length;
            List<LocationInfo> locationInfoList = new ArrayList<>();
            for (String line : array)
            {
                try
                {
                    String[] strArray1 = line.split("L");
                    //设备编号
                    String deviceId = strArray1[0].trim();
                    String[] tempArray1 = strArray1[1].split("  ");
                    //时间
                    String time1 = tempArray1[1].replace("/", "-");
                    Date date1 = SIMPLEDATEFORMAT.parse(time1);
                    String time2 = tempArray1[2];
                    //经纬度
                    String latStr = tempArray1[3].trim();
                    double lat = Double.valueOf(latStr);
                    String lotStr = tempArray1[4].trim();
                    double lot = Double.valueOf(lotStr);
                    //TODO:其它参数不知道什么意思
                    if (lat != 0.0 && lot != 0.0)
                    {
                        LocationInfo locationInfo = new LocationInfo();
                        locationInfo.setM_deviceId(deviceId);
                        locationInfo.setM_lat(lat);
                        locationInfo.setM_lot(lot);
                        locationInfo.setM_createTime(date1);
                        locationInfoList.add(locationInfo);
                    }
                }
                catch (Exception e)
                {
                    //e.printStackTrace();
                    continue;
                }
            }
            if (locationInfoList.size() > 0)
            {
                LocationInfoService locationInfoService = (LocationInfoService) ServiceUtil.getBean(
                        "locationInfoService");
                int count = locationInfoService.InsertList(locationInfoList);
                System.out.println(">>>本次共读取(过滤了空行):" + lineCount + "条数据,新增" + count + "条正确数据");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (null != bufferedReader)
                {
                    bufferedReader.close();
                }
                if (null != inputStreamReader)
                {
                    inputStreamReader.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    class ReadFileThread extends Thread
    {
        @Override
        public void start()
        {
            super.start();
        }

        @Override
        public void run()
        {
            TimerTask timerTask = new TimerTask()
            {
                @Override
                public void run()
                {
                    //ReadFile();
                    //ReadAllFile();
                }
            };
            m_timer.schedule(timerTask, 0, m_fileData.getM_time());
            m_logger.info(">>>定时读取文本内容的任务执行");
        }

        private void ReadFile()
        {
            File file = new File(m_fileData.getM_path());
            FileInputStream fileInputStream;
            InputStreamReader inputStreamReader = null;
            BufferedReader bufferedReader = null;
            int lineCount;
            try
            {
                fileInputStream = new FileInputStream(file);
                inputStreamReader = new InputStreamReader(fileInputStream, m_fileData.getM_encode());
                bufferedReader = new BufferedReader(inputStreamReader);
                //过滤空行
                Stream<String> streams = bufferedReader.lines().filter(p -> p != null && !"".equals(p) && p.contains(
                        "L"));
                Object[] array = streams.toArray();
                lineCount = array.length;
                m_logger.info(String.format(">>>本次共读取(过滤了空行):%s", lineCount));
                //文件内容有变动
                if (lineCount != LINECOUNT)
                {
                    //最新的一条数据
                    String line =
                            String.valueOf(Stream.of(array).filter(p -> p.equals("让过滤器的结果为false,执行返回最后一个元素")).findFirst()
                            .orElse(array[lineCount - 1]));
                    System.out.println(">>>监听的文本文件的内容有更新:" + line);
                    String[] strArray1 = line.split("L");
                    //设备编号
                    String deviceId = strArray1[0].trim();
                    String[] tempArray1 = strArray1[1].split("  ");
                    //时间
                    String time1 = tempArray1[1].replace("/", "-");
                    Date date1 = SIMPLEDATEFORMAT.parse(time1);
                    String time2 = tempArray1[2];
                    //经纬度
                    String latStr = tempArray1[3].trim();
                    double lat = Double.valueOf(latStr);
                    String lotStr = tempArray1[4].trim();
                    double lot = Double.valueOf(lotStr);
                    //TODO:其它参数不知道什么意思
                    if (lat != 0.0 && lot != 0.0)
                    {
                        LocationInfo locationInfo = new LocationInfo();
                        locationInfo.setM_deviceId(deviceId);
                        locationInfo.setM_lat(lat);
                        locationInfo.setM_lot(lot);
                        locationInfo.setM_createTime(date1);
                        LocationInfoService locationInfoService = (LocationInfoService) ServiceUtil.getBean(
                                "locationInfoService");
                        locationInfoService.Insert(locationInfo);
                        m_logger.info(String.format(">>>将本次数据%s更新至MySQL数据库...",locationInfo.toString()));
                    }
                    else
                    {
                        m_logger.error(">>>本次数据有错误,禁止更新至数据库");
                    }
                    LINECOUNT = lineCount;
                }
            }
            catch (Exception e)
            {
                m_logger.error(e.getMessage());
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    if (null != bufferedReader)
                    {
                        bufferedReader.close();
                    }
                    if (null != inputStreamReader)
                    {
                        inputStreamReader.close();
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

}
