package com.zistone.gprs.file_listener;

import java.util.Date;

public class FileData
{
    @Override
    public String toString()
    {
        return "FileData{" +
                "path='" + path + '\'' +
                ", encode='" + encode + '\'' +
                ", content='" + content + '\'' +
                ", timeLength=" + timeLength +
                '}';
    }

    private String path;
    private String encode;
    private String content;
    private long timeLength;

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public String getEncode()
    {
        return encode;
    }

    public void setEncode(String encode)
    {
        this.encode = encode;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public long getTimeLength()
    {
        return timeLength;
    }

    public void setTimeLength(long timeLength)
    {
        this.timeLength = timeLength;
    }

}
