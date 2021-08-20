package com.snowcattle.game.code.generate;

import com.alibaba.fastjson.JSONArray;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class JSonFileWriter {

    public void writeFile(String  rootPath , String filePath, JSONArray jsonArray) throws IOException {
        File file  = new File(rootPath + File.separatorChar + filePath);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        String writeString= jsonArray.toJSONString();
        fileOutputStream.write(writeString.getBytes());
        fileOutputStream.close();
    }
}
