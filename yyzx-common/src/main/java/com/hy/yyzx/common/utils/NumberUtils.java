package com.hy.yyzx.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberUtils {
	/**
	 * 浮点四舍五入
	 * @param value 值
	 * @param digit 精度
	 * @return
	 */
	public static Double doubleRound(Double value,Integer digit){
		BigDecimal btotal=new BigDecimal(value).setScale(digit, BigDecimal.ROUND_HALF_UP);//营业额
		return btotal.doubleValue();
	}
	
	/**
	 * 浮点只舍不入
	 * @param value 值
	 * @return
	 */
	public static Double doubleRoundDown(Double value){
		BigDecimal btotal=new BigDecimal(value).setScale(0, BigDecimal.ROUND_DOWN);//营业额
		return btotal.doubleValue();
	}
	
	/**
	 * 浮点只入不舍
	 * @param value 值
	 * @return
	 */
	public static Integer intRoundUp(Double value){
		BigDecimal btotal=new BigDecimal(value).setScale(0, BigDecimal.ROUND_UP);//营业额
		return btotal.intValue();
	}
	
	/**
	 * 数值四舍五入
	 * @param value
	 * @return
	 */
	public static Integer intRound(Double value){
		BigDecimal btotal=new BigDecimal(value).setScale(0, BigDecimal.ROUND_HALF_UP);//营业额
		return btotal.intValue();
	}
	/**
	 * 保证有两位小数，##0.00格式
	 * @param value
	 * @return
	 */
	public static String getPointAfterTwo(Double value){
		value=doubleRound(value,2);
		
		DecimalFormat df = new DecimalFormat("0.00");
//		String str=value+"";
//		String tmp=str.substring(str.lastIndexOf(".")+1,str.length());
//		if(tmp==null){
//			tmp="";
//		}
//		if(tmp.length()<2){
//			for(int i=tmp.length();i<2;i++){
//				str=str+"0";
//			}
//		}
		return df.format(value);
	}
	public static Double getPointAfterTwoDouble(Double value){
		return Double.parseDouble(getPointAfterTwo(value));
	}
	
	public static String getPointAfterTwo(Integer value){
		return getPointAfterTwo(Double.valueOf(value+""));
	}
	/**
	 * 判断是否是数据正则表达式
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){ 
		   Pattern pattern = Pattern.compile("[0-9]*"); 
		   Matcher isNum = pattern.matcher(str);
		   if( !isNum.matches() ){
		       return false; 
		   } 
		   return true; 
		}
	/** 
     * 提供精确的加法运算。 
     *  
     * @param v1 
     *            被加数 
     * @param v2 
     *            加数 
     * @return 两个参数的和 
     */  
  
    public static double add(double v1, double v2) {  
        BigDecimal b1 = new BigDecimal(Double.toString(v1));  
        BigDecimal b2 = new BigDecimal(Double.toString(v2));  
        return b1.add(b2).doubleValue();  
    }  
    /** 
     * 提供精确的减法运算。 
     *  
     * @param v1 
     *            被减数 
     * @param v2 
     *            减数 
     * @return 两个参数的差 
     */  
  
    public static double sub(double v1, double v2) {  
        BigDecimal b1 = new BigDecimal(Double.toString(v1));  
        BigDecimal b2 = new BigDecimal(Double.toString(v2));  
        return b1.subtract(b2).doubleValue();  
    }
    /** 
     * 提供精确的乘法运算。 
     *  
     * @param v1 
     *            被乘数 
     * @param v2 
     *            乘数 
     * @return 两个参数的积 
     */  
  
    public static double mul(double v1, double v2) {  
        BigDecimal b1 = new BigDecimal(Double.toString(v1));  
        BigDecimal b2 = new BigDecimal(Double.toString(v2));  
        return b1.multiply(b2).doubleValue();  
    }  
    
    /** 
     * 提供精确的除法运算。 
     *  
     * @param v1 
     *            被除数 
     * @param v2 
     *            除数数 
     * @return 两个参数的积商
     */  
  
    public static double div(double v1, double v2) {  
        BigDecimal b1 = new BigDecimal(Double.toString(v1));  
        BigDecimal b2 = new BigDecimal(Double.toString(v2));  
        return b1.divide(b2).doubleValue();  
    }

	/**
	 * 浮点只入不舍
	 * @param value 值
	 * @return
	 */
	public static double doubleRoundUp(double value){
		NumberFormat nf = NumberFormat.getInstance();
		String number=nf.format(value);
		System.out.println(number.length());
		if(number.length()==3 || number.length()==4){//若数字为0.1000之类 不入
			BigDecimal btotal=new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP);
			return btotal.doubleValue();
		}else{
			BigDecimal btotal=new BigDecimal(value).setScale(2, BigDecimal.ROUND_UP);
			DecimalFormat df = new DecimalFormat("0.00");
			return Double.valueOf(df.format(btotal));
		}
	}

	public static void main(String[] args) {
		System.out.println(doubleRoundUp(1.1233));
	}

    
}
