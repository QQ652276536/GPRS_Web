package com.zistone.file_listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Stream;

/**
 * 事件源
 */
public class FileContentEvent extends ApplicationEvent
{
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private static String PATH = "C:\\Users\\zistone\\Desktop\\gprs_info.txt";
    private static int TIME = 7 * 1000 * 60;
    private static int LINECOUNT;

    private Timer m_timer = new Timer();

    public FileContentEvent(Object source)
    {
        super(source);
        logger.info(">>>事件触发");
        ReadFileThread readFileThread = new ReadFileThread();
        readFileThread.start();
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
            m_timer.schedule(timerTask, 0, TIME);
        }

        private void ReadFile()
        {
            File file = new File(PATH);
            FileInputStream fileInputStream;
            InputStreamReader inputStreamReader = null;
            BufferedReader bufferedReader = null;
            //如果没有异常,这两个变量值应该是相同的
            int lineCount;
            try
            {
                fileInputStream = new FileInputStream(file);
                inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
                bufferedReader = new BufferedReader(inputStreamReader);
                //过滤空行
                Stream<String> streams = bufferedReader.lines().filter(p -> p != null && !"".equals(p) && p.contains("L"));
                Object[] array = streams.toArray();
                lineCount = array.length;
                logger.info(">>>内容行数(过滤空行):" + lineCount);
                //文件内容有变动
                if (lineCount != LINECOUNT)
                {
                    //最新的一条数据
                    String line = String.valueOf(Stream.of(array).findFirst().orElse(array[lineCount - 1]));
                    System.out.println(">>>最新数据:" + line);
                    String[] strArray1 = line.split("L");
                    //设备名(号)
                    String deviceName = strArray1[0].trim();
                    String[] tempArray1 = strArray1[1].split("  ");
                    //时间
                    String time1 = tempArray1[1];
                    String time2 = tempArray1[2];
                    //经纬度
                    String lat = tempArray1[3].trim();
                    String lot = tempArray1[4].trim();
                    //TODO:其它参数不知道什么意思
                    if (Double.valueOf(lat) != 0.0 && Double.valueOf(lot) != 0.0)
                    {
                        
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
