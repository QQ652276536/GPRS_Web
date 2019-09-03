package com.zistone.file_listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

//普通POJO实例化到SPring容器中
@Component
@Transactional
public class FileContentListener implements ApplicationListener<FileContentEvent>
{
    Logger logger = LoggerFactory.getLogger(FileContentListener.class);

    @Override
    public void onApplicationEvent(FileContentEvent fileContentEvent)
    {
        if (Objects.isNull(fileContentEvent))
        {
            return;
        }
        FileData fileData = fileContentEvent.GetFileData();
        logger.info(">>>接收到监听对象:" + fileData.toString());
    }
}
