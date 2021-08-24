package com.snowcattle.game.code.utils;

import java.math.BigDecimal;
import java.util.Date;

public class SqlUtils {

    public static String findSqlColnamePoFileTypeName(String columnName){

        String fieldClass = "";
        if (columnName.startsWith("tinyint")) {
//            fieldClass = Short.class.getSimpleName();
            fieldClass = "short";
        } else if (columnName.startsWith("int") || columnName.startsWith("smallint")) {
//            fieldClass = Integer.class.getSimpleName();
            fieldClass = "int";
        } else if (columnName.startsWith("bigint")) {
//            fieldClass = Long.class.getSimpleName();
            fieldClass = "long";
        } else if (columnName.startsWith("float")) {
//            fieldClass = Float.class.getSimpleName();
            fieldClass = "float";
        } else if (columnName.startsWith("double")) {
//            fieldClass = Double.class.getSimpleName();
            fieldClass = "double";
        } else if (columnName.startsWith("time") || columnName.startsWith("date") || columnName.startsWith("datetime")
                || columnName.startsWith("timestamp")) {
//            fieldClass = Date.class.getSimpleName();
            fieldClass = "Date";
        } else if (columnName.startsWith("varchar") || columnName.startsWith("tindtext")
                || columnName.startsWith("char")
                || columnName.startsWith("clob") || columnName.startsWith("blob") || columnName.startsWith("json")) {
//            fieldClass = String.class.getSimpleName();
            fieldClass = "String";
        } else if (columnName.startsWith("decimal") || columnName.startsWith("number")) {
            //2018-11-22 lshz0088 建议对number类型增加int，long，BigDecimal的区分判断
            //如果startKh大于等于0，则表示有设置取值范围
            int startKh = columnName.indexOf("(");
            if (startKh >= 0) {
                int endKh = columnName.indexOf(")", startKh);
                String[] fanwei = columnName.substring(startKh + 1, endKh).split("，");
                //2019-1-5 zhengk 修复@arthaschan反馈的超出范围错误
                //System.out.println("fanwei"+ JSON.toJSONString(fanwei));
                //                            //number(20,6) fanwei["20","6"]
                //                            //number(0,6) fanwei["0","6"]
                //                            //number(20,0) fanwei["20","0"]
                //                            //number(20) fanwei["20"]
                //如果括号里是1位或者2位且第二位为0，则进行特殊处理。只有有小数位，都设置为BigDecimal。
                if ((fanwei.length > 1 && "0".equals(fanwei[1])) || fanwei.length == 1) {
                    int length = Integer.parseInt(fanwei[0]);
                    if (fanwei.length > 1) {
                        length = Integer.valueOf(fanwei[1]);
                    }
                    //数字范围9位及一下用Integer，大的用Long
                    if (length <= 9) {
//                        fieldClass = (isPackageType)?Integer.class.getSimpleName():"int";
//                        fieldClass = Integer.class.getSimpleName();
                        fieldClass = "int";
                    } else {
//                        fieldClass = (isPackageType)?Long.class.getSimpleName():"long";
//                        fieldClass = Long.class.getSimpleName();
                        fieldClass = "long";
                    }
                } else {
                    //有小数位数一律使用BigDecimal
                    fieldClass = BigDecimal.class.getSimpleName();
                }
            } else {
                fieldClass = BigDecimal.class.getSimpleName();
            }
        } else if (columnName.startsWith("boolean")) {
//            fieldClass = Boolean.class.getSimpleName();
            fieldClass = "boolean";
        } else {
//            fieldClass = String.class.getSimpleName();
            fieldClass = "String";
        }

        return fieldClass;
    }
}
