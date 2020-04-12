package com.hy.yyzx.common.utils;

import java.security.MessageDigest;

public class MD5 {

    private static final String[] hexDigits = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public MD5() {
    }

    public static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        byte[] var2 = b;
        int var3 = b.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            byte aB = var2[var4];
            resultSb.append(byteToHexString(aB));
        }

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (b < 0) {
            n = 256 + b;
        }

        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin) {
        byte[] md5Byte = MD5Byte(origin, (String)null);
        return md5Byte == null ? origin : byteArrayToHexString(md5Byte);
    }

    public static byte[] MD5Byte(String origin, String charsetname) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname != null && !"".equals(charsetname)) {
                byte[] bytes = origin.getBytes(charsetname);
                return md.digest(bytes);
            } else {
                return md.digest(origin.getBytes());
            }
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }

    private static String MD5Encode(String origin, String charsetname) {
        String resultString = null;

        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname != null && !"".equals(charsetname)) {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
            } else {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            }
        } catch (Exception var4) {
            ;
        }

        return resultString.toUpperCase();
    }

    public static String byte2hex(byte[] bytes) {
        StringBuffer sb = new StringBuffer(bytes.length * 2);
        String tmp = "";

        for(int n = 0; n < bytes.length; ++n) {
            tmp = Integer.toHexString(bytes[n] & 255);
            if (tmp.length() == 1) {
                sb.append("0");
            }

            sb.append(tmp);
        }

        return sb.toString().toUpperCase();
    }

    public static byte[] hex2byte(String str) {
        if (str != null && str.length() >= 2) {
            str = str.toLowerCase();
            int l = str.length() / 2;
            byte[] result = new byte[l];

            for(int i = 0; i < l; ++i) {
                String tmp = str.substring(2 * i, 2 * i + 2);
                result[i] = (byte)(Integer.parseInt(tmp, 16) & 255);
            }

            return result;
        } else {
            return new byte[0];
        }
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString != null && !hexString.equals("")) {
            hexString = hexString.toUpperCase();
            int length = hexString.length() / 2;
            char[] hexChars = hexString.toCharArray();
            byte[] d = new byte[length];

            for(int i = 0; i < length; ++i) {
                int pos = i * 2;
                d[i] = (byte)(charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
            }

            return d;
        } else {
            return null;
        }
    }

    private static byte charToByte(char c) {
        return (byte)"0123456789ABCDEF".indexOf(c);
    }
}
