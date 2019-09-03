package com.zistone.file_listener;

import com.zistone.bean.DeviceInfo;
import com.zistone.service.DeviceInfoService;
import com.zistone.service.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Stream;

public class FileContentEvent extends ApplicationEvent
{
    private static int LINECOUNT;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private FileData m_fileData;
    private Timer m_timer = new Timer();

    public FileContentEvent(FileData fileData)
    {
        super(fileData);
        this.m_fileData = fileData;
        ReadFileThread readFileThread = new ReadFileThread();
        readFileThread.start();
        logger.info(">>>线程" + readFileThread.getId() + "执行");
    }

    public FileData GetFileData()
    {
        return m_fileData;
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
                    ReadFile();
                }
            };
            m_timer.schedule(timerTask, 0, m_fileData.getM_time());
            logger.info(">>>定时读取文本内容的任务执行");
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
                Stream<String> streams = bufferedReader.lines().filter(p -> p != null && !"".equals(p) && p.contains("L"));
                Object[] array = streams.toArray();
                lineCount = array.length;
                logger.info(">>>本次内容行数(过滤空行):" + lineCount);
                //文件内容有变动
                if (lineCount != LINECOUNT)
                {
                    //最新的一条数据
                    String line = String.valueOf(Stream.of(array).filter(p -> p.equals("让过滤器的结果为false,执行返回最后一个元素")).findFirst()
                            .orElse(array[lineCount - 1]));
                    System.out.println(">>>有新的数据:" + line);
                    String[] strArray1 = line.split("L");
                    //设备名(号)
                    String deviceName = strArray1[0].trim();
                    String[] tempArray1 = strArray1[1].split("  ");
                    //时间
                    String time1 = tempArray1[1];
                    String time2 = tempArray1[2];
                    //经纬度
                    String latStr = tempArray1[3].trim();
                    double lat = Double.valueOf(latStr);
                    String lotStr = tempArray1[4].trim();
                    double lot = Double.valueOf(lotStr);
                    //TODO:其它参数不知道什么意思
                    if (lat != 0.0 && lot != 0.0)
                    {
                        DeviceInfo deviceInfo = new DeviceInfo();
                        deviceInfo.setM_name(deviceName);
                        deviceInfo.setM_lat(lat);
                        deviceInfo.setM_lot(lot);
                        DeviceInfoService deviceInfoService = (DeviceInfoService) ServiceUtil.getBean("deviceInfoService");
                        deviceInfoService.UpdateDeviceByName(deviceInfo);
                        logger.info(">>>监听的文本文件的内容有更新,将新数据" + deviceInfo.toString() + "更新至数据库");
                    }
                    LINECOUNT = lineCount;
                }
            }
            catch (Exception e)
            {
                logger.error(e.getMessage());
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
