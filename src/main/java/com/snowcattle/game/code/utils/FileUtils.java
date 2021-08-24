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
    public static Map<String , File> recursiveFiles(String path, String fileExtension) throws IOException {
        Map<String , File> fileMap = new HashMap<String , File>();
        Iterable<File> files = Files.fileTraverser().depthFirstPreOrder(new File(path));
        for (File file: files){
            if(file.isDirectory()){
                continue;
            }
            if(!file.getName().endsWith(fileExtension)){
                continue;
            }
            String filePath = file.getPath();
            String relativePath = filePath.replace(path+File.separatorChar,"");
            System.out.println("find file relativePath: " + relativePath);
            fileMap.put(relativePath, file);
        }
        return fileMap;
    }

    /**
     * 获取文件相对路径的前面路径 比如com/test
     * @param path
     * @return
     */
    public static String getFrontDestRootPath(String path){
        int index = path.indexOf(File.separatorChar);
        if(index < 0){
            return "";
        }
        return path.substring(0, index+1);
    }

    /**
     * 获取文件相对路径的后面路径 比如example.xmls
     * @param path
     * @return
     */
    public static String getEndDestRootPath(String path){
        int index = path.indexOf(File.separatorChar);
        if(index < 0){
            return path;
        }
        return path.substring(index+1, path.length());
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
