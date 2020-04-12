package com.hy.yyzx.common.utils;


import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class RandomUtil {


    /**
     * 获取随机数
     *
     * @param length     随机数长度
     * @param onlynumber 是否纯数字
     * @return
     */
    public static String getRandomNum(int length, boolean onlynumber) {
        String str = onlynumber ? "0123456789" : "23456789qwertyuipasdfghjkzxcvbnm23456789QWERTYUPASDFGHJKLZXCVBNM23456789";
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            //System.out.println(r.nextInt(str.length()));
            sb.append(str.charAt(r.nextInt(str.length())));
        }
        return sb.toString();
    }

    /**
     * 随机长度字符
     *
     * @param length
     * @return
     */
    public static String getRandomNum(int length) {
        return getRandomNum(length, false);
    }


    /**
     * 普通流水号生成工具（一位大写字符串+时间格式+6位随机字符串）
     *
     * @param str 任意字符串
     * @return
     */
    public static String serialNum(String str) {
        DateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeString = dateformat.format(new Date());
        String random = StringUtils.genRandomNum(5);
        return str + timeString +"_"+ random;
    }

    public static String serialNum(String prefix, String datefrom){
        if(datefrom == null) datefrom = "yyyyMMddHHmmss";
        DateFormat dateformat = new SimpleDateFormat(datefrom);
        String timeString = dateformat.format(new Date());
        String random = StringUtils.genRandomNum(5);
        return prefix + timeString +"_"+ random;
    }

    public static String serialNum(String prefix, String datefrom, boolean isSymbol){
        if(datefrom == null) datefrom = "yyyyMMddHHmmss";
        DateFormat dateformat = new SimpleDateFormat(datefrom);
        String timeString = dateformat.format(new Date());
        String random = StringUtils.genRandomNum(5);
        if(isSymbol)
            return prefix + timeString +"_"+ random;
        return prefix + timeString + random;
    }

    //保留point位小数，超出进一位
    public static Double ddValue(Double f, int point) {
        BigDecimal b = new BigDecimal(String.valueOf(f));
        String pointStr = "0.";
        for (int i = 0; i < point; i++) {
            pointStr = pointStr + "0";
        }
        DecimalFormat df = new DecimalFormat(pointStr);

        return Double.valueOf(df.format(b.setScale(point, BigDecimal.ROUND_UP)));
    }

    private static String[] telFirst = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");

    public static String getTel(String selfTelFirst) {
        int index=getNum(0,telFirst.length-1);
        String first = telFirst[index];
        if(selfTelFirst != null && !"".equals(selfTelFirst)){
            first = selfTelFirst;
        }
        String body = String.valueOf(getNum(1,888)+10000).substring(1);
        String tail = String.valueOf(getNum(1,9100)+10000).substring(1);
        StringBuilder builder = new StringBuilder();
        builder.append(first)
                .append(body)
                .append(tail);
        return builder.toString();
    }

    public static int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }


    public static void main(String[] args) {

        String pharmacyid = ",4";
        if(pharmacyid.indexOf(",") == 0){
            pharmacyid = pharmacyid.substring(1);
        }
        System.out.println(pharmacyid);


    }

}
