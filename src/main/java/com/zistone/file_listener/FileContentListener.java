package com.zistone.file_listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class FileContentListener implements ApplicationListener<FileContentEvent>
{
    Logger logger = Logger.getLogger(FileContentListener.class.getName());

    @Override
    public void onApplicationEvent(FileContentEvent fileContentEvent)
    {
        logger.info(">>>事件发布");
    }
}
