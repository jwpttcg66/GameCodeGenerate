package com.snowcattle.game.code.generate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.snowcattle.game.code.utils.JsonFormat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class JSonFileWriter {

    public void writeFile(String  rootPath , String filePath, JSONArray jsonArray) throws IOException {
        File file  = new File(rootPath + File.separatorChar + filePath);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        String jsonString = JSON.toJSONString(jsonArray, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat);
        fileOutputStream.write(jsonString.getBytes());
        fileOutputStream.close();
        System.out.println("File " + filePath + "generate 完成");
    }
}
