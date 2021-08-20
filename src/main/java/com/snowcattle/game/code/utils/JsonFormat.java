package com.snowcattle.game.code.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonFormat{
    private  StringBuffer buffer=new StringBuffer();

    //格式化json
    public String format(Object json,int num,boolean isArray){
        if(json instanceof JSONObject){
            JSONObject jsonObject=(JSONObject) json;
            num+=5;
            if(isArray){
                buffer.append(getKg(num)+"{</br>");
            }else{
                buffer.append("{</br>");
            }
            for(String k:jsonObject.keySet()){
                buffer.append(getKg(num+2)+k+" : ");
                format(jsonObject.get(k), num,false); //格式化子目录
            }
            buffer.append(getKg(num)+"}</br>");
        }
        else if(json instanceof JSONArray){
            JSONArray jsonArray=(JSONArray) json;
            num+=5;
            buffer.append("[</br>");
            for(int k=0;k<jsonArray.size();k++){
                format(jsonArray.get(k), num,true); //格式化子目录
            }
            buffer.append(getKg(num)+"]</br>");
        }else{ //如果不是json对象就直接打印值
            buffer.append(json.toString()+"</br>");
        }
        return buffer.toString();
    }

    /**
     * 获取num个数个空格
     * @param num
     * @return
     */
    public String getKg(Integer num){
        StringBuffer kg=new StringBuffer();
        for(int i=0;i<num;i++){
            kg.append("&nbsp;");
        }
        return kg.toString();
    }

}
