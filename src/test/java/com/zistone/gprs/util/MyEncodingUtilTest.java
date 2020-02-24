package com.zistone.gprs.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyEncodingUtilTest
{

    @Test
    public void GBKToUnicode()
    {
        System.out.println("GBK转Unicode:" + MyEncodingUtil.GBKToUnicode("B2E2CAD4"));
        System.out.println("____________________________________________________________________");
    }

    @Test
    public void unicodeToGBK()
    {
        System.out.println("Unicode转GBK:" + MyEncodingUtil.UnicodeToGBK("测试"));
        System.out.println("____________________________________________________________________");
    }
}