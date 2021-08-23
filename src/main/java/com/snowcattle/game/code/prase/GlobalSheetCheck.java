package com.snowcattle.game.code.prase;

import java.util.HashSet;
import java.util.Set;

/**
 * 所有的sheet缓存
 */
public class GlobalSheetCheck {

    /**
     * 记录所有表名
     */
    private Set<String> sheetSets = new HashSet<String>();

    public boolean isExsitSheet(String sheetName){
        return sheetSets.contains(sheetName);
    }

    public void addSheetName(String sheetName){
        sheetSets.add(sheetName);
    }
}
