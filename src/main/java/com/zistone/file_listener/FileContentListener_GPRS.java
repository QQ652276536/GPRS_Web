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
public class FileContentListener_GPRS implements ApplicationListener<FileContentEvent_GPRS>
{
    private Logger m_logger = LoggerFactory.getLogger(FileContentListener_GPRS.class);

    @Override
    public void onApplicationEvent(FileContentEvent_GPRS fileContentEvent)
    {
        if (Objects.isNull(fileContentEvent))
        {
            return;
        }
        FileData fileData = fileContentEvent.GetFileData();
        m_logger.info(">>>接收到监听对象:" + fileData.toString());
    }
}
