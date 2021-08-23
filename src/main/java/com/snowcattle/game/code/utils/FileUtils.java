package com.snowcattle.game.code.utils;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileUtils {
    /**
     * 遍历文件/文件夹 - 函数
     * [String]path        文件路径
     */
    public static Map<String , File> recursiveFiles(String path) throws IOException {
        Map<String , File> fileMap = new HashMap<String , File>();
        Iterable<File> files = Files.fileTraverser().depthFirstPreOrder(new File(path));
        for (File file: files){
            if(file.isDirectory()){
                continue;
            }
            String filePath = file.getPath();
            String relativePath = filePath.replace(path+File.separatorChar,"");
            System.out.println("find file relativePath: " + relativePath);
            fileMap.put(relativePath, file);
        }
        return fileMap;
    }

    public static String getDestRootPath(String path){
        int index = path.indexOf(File.separatorChar);
        if(index < 0){
            return "";
        }
        return path.substring(0, index+1);
    }

    public static void doFileMakeDirs(File file){
        if(file.exists()){
            file.delete();
        }
        File parentFile = file.getParentFile();
        if(!parentFile.exists()){
            parentFile.mkdirs();
        }
    }
}
