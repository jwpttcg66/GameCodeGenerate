package com.snowcattle.game.code.writer.java;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;

public class JavaPoWriter {
    public void writeFile(String  rootPath , String filePath, Configuration cfg, PoClassParam poClassParam) throws IOException {
        File file  = new File(rootPath + File.separatorChar + filePath);
        if(file.exists()){
            file.delete();
        }
        File parentFile = file.getParentFile();
        if(!parentFile.exists()){
            parentFile.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        Writer out = new OutputStreamWriter(fileOutputStream, "utf-8");
        Template temp = cfg.getTemplate("test.ftl");
        try {
            temp.process(null, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        System.out.println("File " + filePath + " po 完成");
    }
}
