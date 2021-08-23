package com.snowcattle.game.code.writer.java;

import com.alibaba.fastjson.JSONArray;
import com.snowcattle.game.code.config.EnvParam;
import com.snowcattle.game.code.prase.SheetResult;
import com.snowcattle.game.code.writer.json.JSonFileWriter;
import freemarker.core.ParseException;
import freemarker.template.*;
import org.springframework.util.ResourceUtils;

import java.io.*;

public class JavaPoGenerater {

    private static Configuration cfg = new Configuration();

    public void writeJavaFile(String relativePath, SheetResult sheetResult){

        try {
            Template temp = null;
            File loadTemplateFile = ResourceUtils.getFile("classpath:ftl/test.ftl");

            //设置freemaker 文件加载目录
            cfg.setDirectoryForTemplateLoading(loadTemplateFile.getParentFile());

            writeJavaFile(relativePath);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static void writeJavaFile(String relativePath) throws FileNotFoundException {

        String dirPath = EnvParam.getJsonPath();
        String filePath = relativePath;
        try {
            new JavaPoWriter().writeFile(dirPath, filePath, cfg, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
