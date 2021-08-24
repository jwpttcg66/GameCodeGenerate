package com.snowcattle.game.code.prase;

import java.util.HashSet;
import java.util.Set;

/**
 * 所有的sheet缓存
 */
public class GlobalFileCheck {

    /**
     * 记录所有表名
     */
    private Set<String> fileSets = new HashSet<String>();

    public boolean isExsitFile(String fileName){
        boolean result = false;
        synchronized (fileSets){
            result =  fileSets.contains(fileName);
        }
        return result;
    }

    public void addFileName(String fileName){
        synchronized (fileSets) {
            fileSets.add(fileName);
        }
    }
}
