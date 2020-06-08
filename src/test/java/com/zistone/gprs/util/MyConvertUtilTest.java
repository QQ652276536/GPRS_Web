package com.zistone.gprs.util;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalTime;

public class MyConvertUtilTest {
    @Test
    public void createDifferent4Random() {
        System.out.println(MyConvertUtil.StrAddCharacter("ABCDEFGHIJKLMN",2,"|"));
        String result = MyConvertUtil.SortStringArray(new String[]{"02","28","5c"},false);
        System.out.println(result);
        System.out.println("____________________________________________________________________");
    }

    @Test
    public void hexStrAddCharacter() {
        byte[] temps = MyConvertUtil.HexStrToByteArray("3F07000000017A9D");
        for (byte b : temps) {
            System.out.println("-----" + b);
        }
        System.out.println("____________________________________________________________________");
    }

    @Test
    public void createCheckCode() throws Exception {
        byte[] srcBytes1 = new byte[]{2, 4, 1, 2, 3, 5, 6};
        byte[] srcBytes2 = new byte[]{10, 15, 50};
        byte[] destBytes = new byte[srcBytes1.length + srcBytes2.length];
        System.arraycopy(srcBytes1, 0, destBytes, 0, srcBytes1.length);
        System.arraycopy(srcBytes2, 0, destBytes, srcBytes1.length, srcBytes2.length);
        for (byte b : destBytes) {
            System.out.println("-----" + b);
        }
        //固件名字，将高字节放在前面
        String blockName = MyConvertUtil.StrToHexStr("kernel.bin");
        //左对齐后面补空格，空格替换为零
        blockName = String.format("%-32s", blockName).replace(' ', '0');
        System.out.println(blockName);
        System.out.println("____________________________________________________________________");
        System.out.println(String.format("%06d", 12));
        System.out.println("____________________________________________________________________");
        String str = String.format("%-4s", "928");
        System.out.println(str);
        System.out.println(str.replace(' ', '0'));
        System.out.println("____________________________________________________________________");
        byte[] data1 = MyConvertUtil.HexStrToByteArray("3F11020000217265736F7572636500000000000000005C28020100D0D5002039130101B55501014D5D0101DD5D01016D5E0101FD5E010100000000000000000000000000000000E1110101D113010100000000C112010147A40101D7130101F1590101FB590101055A0101EF3E010151AB01010F5A0101195A0101235A01012D5A0101375A0101415A01014B5A0101555A01015F5A0101695A0101735A010157D301017D5A0101875A01013BD30101915A01019B5A0101A55A0101AF5A0101B95A0101C35A0101CD5A0101D75A0101DFF80CD01EF0A4FF004800476F5A0201D0D500200020704710B505F0BBFD002010BD7047704700004D504F53005500000000000000100101041001016B65726E656C00000000000000000000000000000000000000000000000000004731434B32303136303930313030310000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000072B600BF00BF00BF00BF30BF00BF00BF00BF00BF62B67047EFF3108072B6704780F31088704762B6704772B67047EFF3148040F0010080F3148870470AAB009911F0010F0CBF02B001B0694609B4084604F05EFD09BC079981F30388EFF3148141EA000181F31488069941F0010143F8041C01BC43F8080CA3F10800BDE80E50854601BD1EF0040F0CBFEFF308800AE001468A6932F8023C");
        System.out.println(MyConvertUtil.IntToHexStr(MyConvertUtil.CalculateCRC_Zistone_LP108(data1)));
        System.out.println("____________________________________________________________________");
        String bitStr1 = "11111111";
        String bitStr2 = "00000000";
        String bitStr3 = "10000100";
        String bitStr4 = "01111110";
        String bitStr5 = "00111100";
        String bitStr6 = "00011000";
        String bitStr7 = "00010000";
        String bitStr8 = "10000100";
        String bitStr9 = "00010010";
        String bitStr10 = "00010100";
        String bitStr11 = "01000000";
        System.out.println(bitStr1 + "\t二进制Str转十进制Int:" + Integer.parseInt(bitStr1, 2) + "\t\t" + "十进制Int转十六进制Int:" + Integer.toHexString(Integer.parseInt(bitStr1, 2)));
        System.out.println(bitStr2 + "\t二进制Str转十进制Int:" + Integer.parseInt(bitStr2, 2) + "\t\t" + "十进制Int转十六进制Int:" + Integer.toHexString(Integer.parseInt(bitStr2, 2)));
        System.out.println(bitStr3 + "\t二进制Str转十进制Int:" + Integer.parseInt(bitStr3, 2) + "\t\t" + "十进制Int转十六进制Int:" + Integer.toHexString(Integer.parseInt(bitStr3, 2)));
        System.out.println(bitStr4 + "\t二进制Str转十进制Int:" + Integer.parseInt(bitStr4, 2) + "\t\t" + "十进制Int转十六进制Int:" + Integer.toHexString(Integer.parseInt(bitStr4, 2)));
        System.out.println(bitStr5 + "\t二进制Str转十进制Int:" + Integer.parseInt(bitStr5, 2) + "\t\t" + "十进制Int转十六进制Int:" + Integer.toHexString(Integer.parseInt(bitStr5, 2)));
        System.out.println(bitStr6 + "\t二进制Str转十进制Int:" + Integer.parseInt(bitStr6, 2) + "\t\t" + "十进制Int转十六进制Int:" + Integer.toHexString(Integer.parseInt(bitStr6, 2)));
        System.out.println(bitStr7 + "\t二进制Str转十进制Int:" + Integer.parseInt(bitStr7, 2) + "\t\t" + "十进制Int转十六进制Int:" + Integer.toHexString(Integer.parseInt(bitStr7, 2)));
        System.out.println(bitStr8 + "\t二进制Str转十进制Int:" + Integer.parseInt(bitStr8, 2) + "\t\t" + "十进制Int转十六进制Int" + ":" + Integer.toHexString(Integer.parseInt(bitStr8, 2)));
        System.out.println(bitStr9 + "\t二进制Str转十进制Int:" + Integer.parseInt(bitStr9, 2) + "\t\t" + "十进制Int转十六进制Int" + ":" + Integer.toHexString(Integer.parseInt(bitStr9, 2)));
        System.out.println(bitStr10 + "\t二进制Str转十进制Int:" + Integer.parseInt(bitStr10, 2) + "\t\t" + "十进制Int转十六进制Int" + ":" + Integer.toHexString(Integer.parseInt(bitStr10, 2)));
        System.out.println(bitStr11 + "\t二进制Str转十进制Int:" + Integer.parseInt(bitStr11, 2) + "\t\t" + "十进制Int转十六进制Int" + ":" + Integer.toHexString(Integer.parseInt(bitStr11, 2)));
        System.out.println("____________________________________________________________________");
    }

    @Test
    public void strArrayToStr() {
        System.out.println("____________________________________________________________________");
    }

    @Test
    public void intToHexStr() {
        System.out.println(MyConvertUtil.IntToHexStr(10));
        String dayOfYear = String.valueOf(LocalDate.now().getDayOfYear());
        String hour = String.valueOf(LocalTime.now().getHour());
        String minute = String.valueOf(LocalTime.now().getMinute());
        String second = String.valueOf(LocalTime.now().getSecond());
        String timeStr = dayOfYear + hour + minute + second;
        int timeNum = Integer.valueOf(timeStr);
        String timeHexStr = MyConvertUtil.IntToHexStr(timeNum);
        //补齐4位
        if (timeHexStr.length() < 8) {
            int i = 8 - timeHexStr.length();
            StringBuffer stringBuffer = new StringBuffer(timeHexStr);
            for (; i > 0; i--) {
                stringBuffer.insert(0, "0");
            }
            timeHexStr = stringBuffer.toString();
        }
        System.out.println(timeHexStr);
        System.out.println("____________________________________________________________________");
    }

    @Test
    public void hexStrToStr() throws UnsupportedEncodingException {
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
    public void strToHexStr() {
        System.out.println("普通Str转16进制Str:" + MyConvertUtil.StrToHexStr("rD9TcH"));
        System.out.println("普通Str转16进制Str:" + MyConvertUtil.StrToHexStr("kernel.bin"));
        System.out.println("____________________________________________________________________");
    }

    @Test
    public void binaryStrToByte() {
        System.out.println("____________________________________________________________________");
    }

    @Test
    public void longToByteArray8() {
        System.out.println("____________________________________________________________________");
    }

    @Test
    public void byteArray4ToLong() {
        System.out.println((double) MyConvertUtil.ByteArray4ToLong(new byte[]{(byte) 6, (byte) -18, (byte) -9, (byte) -15}) / 1000000);
        System.out.println("____________________________________________________________________");
    }

    @Test
    public void byteArray4ToInt() {
        System.out.println("____________________________________________________________________");
    }

    @Test
    public void byteArray2ToInt() {
        System.out.println("____________________________________________________________________");
    }

    @Test
    public void byteArray1ToInt() {
        System.out.println("____________________________________________________________________");
    }

    @Test
    public void hexStrToByteArray() {
        String hexStr = MyConvertUtil.StrToHexStr("resource");
        byte[] bytes = MyConvertUtil.HexStrToByteArray(hexStr);
        for (byte b : bytes) {
            System.out.println(b);
        }
        System.out.println("____________________________________________________________________");
    }

    @Test
    public void byteArrayToHexStr() {
        System.out.println(MyConvertUtil.ByteArrayToHexStr(new byte[]{(byte) 2, (byte) 97, (byte) 51, (byte) 52}));
        System.out.println("____________________________________________________________________");
    }

    @Test
    public void enUnicode() {
        System.out.println("16进制的Str转成Unicode编码的中文:" + MyConvertUtil.EnUnicode("674E5C0F4F1F"));
        System.out.println("16进制的Str转成Unicode编码的中文:" + MyConvertUtil.EnUnicode("004C0069005700650069"));
        System.out.println("____________________________________________________________________");
    }

    @Test
    public void deUnicode() {
        System.out.println("Unicode编码的中文转16进制的Str:" + MyConvertUtil.DeUnicode("李小伟"));
        System.out.println("Unicode编码的中文转16进制的Str:" + MyConvertUtil.DeUnicode("LiWei"));
        System.out.println("____________________________________________________________________");
    }

    @Test
    public void hexStrSplit() {
        String[] strArray = MyConvertUtil.HexStrSplit("0200123C0F000000C800003201DAFE8506E1BBAC006F00005E0D0BD668");
        System.out.println("不带空格不带0x的16进制str转array:");
        for (String str : strArray) {
            System.out.println(str);
        }
        System.out.println("____________________________________________________________________");
    }
}