package com.toutiao.web.common.util;

/**
 * Created with IntelliJ IDEA
 *
 * @Project :boot-demo
 * @Author : kewei@nash.work
 * @Date : 2017-09-27 下午9:09 星期三
 * @Version : v1.0
 **/
public class UnicodeUtils {
    /**
     * 中文转换成 unicode
     * @param s
     * @return
     */
    public static String encode(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) <= 256) {
                sb.append("\\u00");
            } else {
                sb.append("\\u");
            }
            sb.append(Integer.toHexString(s.charAt(i)));
        }
        return sb.toString();
    }

    /**
     * unicode 转换成中文
     * @param unicode
     * @return
     */
    public static String decode(String unicode) {
        char aChar;
        int len = unicode.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = unicode.charAt(x++);
            if (aChar == '\\') {
                aChar = unicode.charAt(x++);
                if (aChar == 'u') {
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = unicode.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't') {
                        aChar = '\t';
                    } else if (aChar == 'r') {
                        aChar = '\r';
                    } else if (aChar == 'n')
                    {
                        aChar = '\n';
                    } else if (aChar == 'f')
                    {
                        aChar = '\f';
                    }
                    outBuffer.append(aChar);
                }

            } else {
                outBuffer.append(aChar);
            }
        }
        return outBuffer.toString();
    }
}
