package com.hy.yyzx.common.utils;

public class WxTxtRep {
    /**
     * 医生回复提醒（咨询反馈结果通知）
     * */
    public static String CONSULTATION_RESULT="%d医生回复了您的消息，消息内容：%c \n\n<a href='%a'> 点击回复>></a>";
    public static String[] CONSULTATION_RESULT_NAME=new String[]{"%d","%c","%a"};


    /**
     * 替换标签
     * @param names 标签
     * @param values 替换值
     * @param msg 语句
     * @return
     */
    public static String repTxt(String[] names,String[] values,String msg){
        if(names!=null && values!=null && names.length==values.length){
            for(int i=0 ; i<names.length;i++){
                msg=msg.replaceAll(names[i],values[i]);
            }
        }
        return msg;
    }

    public static void main(String[] args){
        System.out.println(repTxt(WxTxtRep.CONSULTATION_RESULT_NAME,new String[]{"吴医生","呆头呆脑","http://sdsdsdsd.com"},WxTxtRep.CONSULTATION_RESULT));
    }
}
