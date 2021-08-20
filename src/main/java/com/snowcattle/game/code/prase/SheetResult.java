package com.snowcattle.game.code.prase;

import java.util.HashMap;
import java.util.Map;

/**
 * 进行sheet检查
 */
public class SheetResult {

    private Map<String, ExcelCellHeader> cache = new HashMap<String, ExcelCellHeader>();

    public boolean checkField(ExcelCellHeader excelCellHeader){
        if(cache.containsKey(excelCellHeader.getEnglishName())){
            return false;
        }

        cache.put(excelCellHeader.getEnglishName(), excelCellHeader);
        return true;
    }

}
