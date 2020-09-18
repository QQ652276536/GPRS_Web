package com.zistone.gprs.util;

import org.junit.Test;
import org.springframework.lang.Nullable;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MyConvertUtilTest {

    private byte[] get6Bit(String strContent) {
        // 结果
        byte[] arrResult = null;
        try {
            // 编码方式
            byte[] arrs = strContent.getBytes("ASCII");
            System.out.println(new String(arrs));

            arrResult = new byte[arrs.length - (arrs.length / 8)];
            int intRight = 0;
            int intLeft = 6;
            int intIndex = 0;
            for (int i = 1; i <= arrs.length; i++, intRight++, intLeft--) {
                if (i % 8 == 0) {
                    intRight = -1;
                    intLeft = 8;
                    continue;
                }
                byte newItem = 0;
                if (i == arrs.length) {
                    newItem = (byte) (arrs[i - 1] >> intRight);
                } else {
                    newItem = (byte) ((arrs[i - 1] >> intRight) | (arrs[i] << intLeft));
                }

                arrResult[intIndex] = newItem;
                intIndex++;

            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return arrResult;
    }

    @Test
    public void createDifferent4Random() throws UnsupportedEncodingException {
        String str = "15Cgah00008LOnt>1Cf`s6NT00SU";
        String resultBit = "";
        System.out.println("对应的ASCII码（Hex）：");
        for (int i = 0; i < str.length(); i++) {
            System.out.print(str.charAt(i) + "    ASCII：");
            int ascii = str.charAt(i);
            String hexAscii = MyConvertUtil.IntToHexStr(ascii);
            System.out.print(hexAscii + "   6位Bit：");
            //加0x28（40）大于等于0x80（128），则加0x48（72）取bit位，否则加0x50（80）取bit位
            if (ascii + 40 >= 128) {
                String tempBit = MyConvertUtil.ByteTo6BitBig((byte) (ascii + 72));
                resultBit += tempBit;
                System.out.print(tempBit);
            } else {
                String tempBit = MyConvertUtil.ByteTo6BitBig((byte) (ascii + 80));
                resultBit += tempBit;
                System.out.print(tempBit);
            }
            System.out.println();
        }
        System.out.println("所有的Bit：" + resultBit + "\r\n长度：" + resultBit.length());
        //开始解析
        String[] bitArray = MyConvertUtil.StrAddCharacter(resultBit, 1, " ").split(" ");
        //消息ID
        String[] bitIdArray = new String[6];
        System.arraycopy(bitArray, 0, bitIdArray, 0, 6);
        String bitId = Arrays.toString(bitIdArray);
        System.out.println("消息ID：" + bitId);
        //转发指示符
        String[] bitRepeat = new String[2];
        System.arraycopy(bitArray, 6, bitRepeat, 0, 2);
        String repeat = Arrays.toString(bitRepeat);
        System.out.println("转发指示符：" + repeat);
        //用户ID/MMSI编号
        String[] bitMmsi = new String[30];
        System.arraycopy(bitArray, 8, bitMmsi, 0, 30);
        String mmsi = Arrays.toString(bitMmsi);
        System.out.println("用户ID：" + mmsi);
        //导航状态
        String[] bitNavigational = new String[4];
        System.arraycopy(bitArray, 38, bitNavigational, 0, 4);
        String navigational = Arrays.toString(bitNavigational);
        System.out.println("导航状态：" + navigational);
        //旋转速率
        String[] bitRot = new String[8];
        System.arraycopy(bitArray, 42, bitRot, 0, 8);
        String rot = Arrays.toString(bitRot);
        System.out.println("旋转速率：" + rot);
        //SOG，地面航速
        String[] bitSog = new String[10];
        System.arraycopy(bitArray, 50, bitSog, 0, 10);
        String sog = Arrays.toString(bitSog);
        System.out.println("SOG：" + sog);
        //位置准确度
        String[] bitAccuracy = new String[1];
        System.arraycopy(bitArray, 60, bitAccuracy, 0, 1);
        String accuracy = Arrays.toString(bitAccuracy);
        System.out.println("位置准确度：" + accuracy);
        //经度
        String[] bitLot = new String[28];
        System.arraycopy(bitArray, 61, bitLot, 0, 28);
        String lot = Arrays.toString(bitLot);
        System.out.println("经度：" + lot);
        //纬度
        String[] bitLat = new String[27];
        System.arraycopy(bitArray, 89, bitLat, 0, 27);
        String lat = Arrays.toString(bitLat);
        System.out.println("纬度：" + lat);
        //COG，地面航线
        String[] bitCog = new String[12];
        System.arraycopy(bitArray, 116, bitCog, 0, 12);
        String cog = Arrays.toString(bitCog);
        System.out.println("COG：" + cog);
        //实际航向
        String[] bitTrueHead = new String[9];
        System.arraycopy(bitArray, 128, bitTrueHead, 0, 9);
        String trueHead = Arrays.toString(bitTrueHead);
        System.out.println("实际航向：" + trueHead);
        //时戳
        String[] bitUtc = new String[6];
        System.arraycopy(bitArray, 137, bitUtc, 0, 6);
        String utc = Arrays.toString(bitUtc);
        System.out.println("时戳：" + utc);
        //特定操作指示符
        String[] bitRegional = new String[2];
        System.arraycopy(bitArray, 143, bitRegional, 0, 2);
        String regional = Arrays.toString(bitRegional);
        System.out.println("特定操作指示符：" + regional);
        //备用
        String[] bitSpare = new String[3];
        System.arraycopy(bitArray, 145, bitSpare, 0, 3);
        String spare = Arrays.toString(bitSpare);
        System.out.println("备用：" + spare);
        //RAIM标志
        String[] bitRaim = new String[1];
        System.arraycopy(bitArray, 148, bitRaim, 0, 1);
        String raim = Arrays.toString(bitRaim);
        System.out.println("RAIM标志：" + raim);
        //通信状态
        String[] bitCommState = new String[19];
        System.arraycopy(bitArray, 149, bitCommState, 0, 19);
        String commState = Arrays.toString(bitCommState);
        System.out.println("通信状态：" + commState);


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
        String tempBitStr1 = MyConvertUtil.ByteToBitBig((byte) 93);
        String tempBitStr2 = MyConvertUtil.ByteToBitLittle((byte) 93);
        System.out.println("大端模式，IntToBit，" + tempBitStr1 + "\t二进制Str转十进制Int:" + Integer.parseInt(tempBitStr1, 2));
        System.out.println("小端模式，IntToBit，" + tempBitStr2 + "\t二进制Str转十进制Int:" + Integer.parseInt(tempBitStr2, 2));
        System.out.println("____________________________________________________________________");

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