package com.zistone.file_listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.stereotype.Component;

@Component
public class FileContentListener implements ApplicationListener<ApplicationContextEvent>
{
    Logger logger = LoggerFactory.getLogger(FileContentListener.class);

    @Override
    public void onApplicationEvent(ApplicationContextEvent applicationContextEvent)
    {
        if (applicationContextEvent.getApplicationContext().getParent() == null)
        {
            logger.info(">>>事件发布");
        }
    }
}
