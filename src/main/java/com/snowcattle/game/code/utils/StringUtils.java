package com.snowcattle.game.code.utils;

public class StringUtils {
    public static String changeToJavaFiled(String field, boolean firstUp) {
        String[] fields = field.toLowerCase().split("_");
        StringBuilder sbuilder = new StringBuilder();
        String firstString = fields[0];
        if(firstUp){
            sbuilder.append(getTransferFirstUpString(firstString));
        }else{
            sbuilder.append(firstString.toLowerCase());
        }
        for (int i = 1; i < fields.length; i++) {
            sbuilder.append(getTransferFirstUpString(fields[i]));
        }
        return sbuilder.toString();
    }

    /**
     * 首字母大写字符串
     * @param field
     * @return
     */
    public static String getTransferFirstUpString(String field){
        String result = field.toLowerCase();
        result = result.substring(0, 1).toUpperCase() + result.substring(1);
        return result;
    }

//    public static void main(String[] args) {
//        String t = "a_s";
//        System.out.println(changeToJavaFiled(t,true));
//    }
}
