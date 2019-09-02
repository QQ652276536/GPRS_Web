package com.zistone.file_listener;

import org.springframework.context.ApplicationEvent;

public class FileContentEvent extends ApplicationEvent
{
    public FileContentEvent(Object source)
    {
        super(source);
    }
}
