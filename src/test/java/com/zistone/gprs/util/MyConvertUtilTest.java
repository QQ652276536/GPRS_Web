package com.zistone.gprs.util;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalTime;

public class MyConvertUtilTest
{
    @Test
    public void createDifferent4Random()
    {
        System.out.println("____________________________________________________________________");
    }

    @Test
    public void hexStrAddCharacter()
    {
        System.out.println("____________________________________________________________________");
    }

    @Test
    public void createCheckCode() throws Exception
    {
        String str = MyConvertUtil.StrAddCharacter("8103001305510300633419980200000009040009000000000029040000003C", " ");
        System.out.println("生成的检验码为:" + MyConvertUtil.CreateCheckCode(str));
        System.out.println("____________________________________________________________________");
    }

    @Test
    public void strArrayToStr()
    {
        System.out.println("____________________________________________________________________");
    }

    @Test
    public void intToHexStr()
    {
        String dayOfYear = String.valueOf(LocalDate.now().getDayOfYear());
        String hour = String.valueOf(LocalTime.now().getHour());
        String minute = String.valueOf(LocalTime.now().getMinute());
        String second = String.valueOf(LocalTime.now().getSecond());
        String timeStr = dayOfYear + hour + minute + second;
        int timeNum = Integer.valueOf(timeStr);
        String timeHexStr = MyConvertUtil.IntToHexStr(timeNum);
        //补齐4位
        if (timeHexStr.length() < 8)
        {
            int i = 8 - timeHexStr.length();
            StringBuffer stringBuffer = new StringBuffer(timeHexStr);
            for (; i > 0; i--)
            {
                stringBuffer.insert(0, "0");
            }
            timeHexStr = stringBuffer.toString();
        }
        System.out.println(timeHexStr);
        System.out.println("____________________________________________________________________");
    }

    @Test
    public void hexStrToStr() throws UnsupportedEncodingException
    {
        System.out
                .println("16进制Str转普通Str:\n" + MyConvertUtil.HexStrToStr("47455420687474703A2F2F3132392E3230342E3136352E3230363A" +
                        "353030302F20485454502F312E310D0A4163636570743A20746578742F68746D6C2C617070" +
                        "6C69636174696F6E2F7868746D6C2B786D6C2C6170706C69636174696F6E2F786D6C3B713D" +
                        "302E392C2A2F2A3B713D302E380D0A4163636570742D456E636F64696E673A20677A69700D" +
                        "0A436F6E6E656374696F6E3A206B6565702D616C6976650D0A486F73743A203132392E3230" +
                        "342E3136352E3230363A353030300D0A512D5375622D427573696E6573733A20636F6E7465" +
                        "6E7473656375726974790D0A557365722D4167656E743A204D6F7A696C6C612F352E302028" +
                        "57696E646F7773204E5420362E313B20574F5736343B2072763A35372E3029204765636B6F" +
                        "2F32303130303130312046697265666F782F35372E300D0A582D466F727761726465642D46" +
                        "6F723A2031302E31302E302E310D0A582D4E676E2D4E6574776F726B3A20696E7472616E65" +
                        "740D0A582D5265616C2D49703A2031302E31302E302E310D0A582D52696F2D5365713A2031" +
                        "333032316330613A3031366530306637623332623A3063653961300D0A582D53672D49702D" +
                        "436861696E3A2031302E32382E322E31392C392E31392E3136312E36370D0A0D0A"));
        System.out
                .println("16进制Str转普通Str:\n" + MyConvertUtil.HexStrToStr("22540605"));
        System.out.println("____________________________________________________________________");
    }

    @Test
    public void strToHexStr()
    {
        System.out.println("普通Str转16进制Str:" + MyConvertUtil.StrToHexStr("rD9TcH"));
        System.out.println("普通Str转16进制Str:" + MyConvertUtil.StrToHexStr("GMT"));
        System.out.println("____________________________________________________________________");
    }

    @Test
    public void binaryStrToByte()
    {
        System.out.println("____________________________________________________________________");
    }

    @Test
    public void longToByteArray8()
    {
        System.out.println("____________________________________________________________________");
    }

    @Test
    public void byteArray4ToLong()
    {
        System.out.println((double) MyConvertUtil.ByteArray4ToLong(new byte[]{(byte) 6, (byte) -18, (byte) -9, (byte) -15}) / 1000000);
        System.out.println("____________________________________________________________________");
    }

    @Test
    public void byteArray4ToInt()
    {
        System.out.println("____________________________________________________________________");
    }

    @Test
    public void byteArray2ToInt()
    {
        System.out.println("____________________________________________________________________");
    }

    @Test
    public void byteArray1ToInt()
    {
        System.out.println("____________________________________________________________________");
    }

    @Test
    public void hexStrToByteArray()
    {
        System.out.println("____________________________________________________________________");
    }

    @Test
    public void byteArrayToHexStr()
    {
        System.out.println(MyConvertUtil.ByteArrayToHexStr(new byte[]{(byte) 2, (byte) 97, (byte) 51, (byte) 52}));
        System.out.println("____________________________________________________________________");
    }

    @Test
    public void enUnicode()
    {
        System.out.println("16进制的Str转成Unicode编码的中文:" + MyConvertUtil.EnUnicode("674E5C0F4F1F"));
        System.out.println("16进制的Str转成Unicode编码的中文:" + MyConvertUtil.EnUnicode("004C0069005700650069"));
        System.out.println("____________________________________________________________________");
    }

    @Test
    public void deUnicode()
    {
        System.out.println("Unicode编码的中文转16进制的Str:" + MyConvertUtil.DeUnicode("李小伟"));
        System.out.println("Unicode编码的中文转16进制的Str:" + MyConvertUtil.DeUnicode("LiWei"));
        System.out.println("____________________________________________________________________");
    }

    @Test
    public void hexStrSplit()
    {
        String[] strArray = MyConvertUtil.HexStrSplit("0200123C0F000000C800003201DAFE8506E1BBAC006F00005E0D0BD668");
        System.out.println("不带空格不带0x的16进制str转array:");
        for (String str : strArray)
        {
            System.out.println(str);
        }
        System.out.println("____________________________________________________________________");
    }
}