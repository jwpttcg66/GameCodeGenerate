package com.snowcattle.game.code.prase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 进行sheet检查，缓存解析结果
 */
public class SheetResult {

    /**
     * sheet名字
     */
    private String sheetName;

    /**
     * field缓存
     */
    private Map<String, SheetCellHeader> headCache = new HashMap<String, SheetCellHeader>();
    /**
     * cell列索引缓存
     */
    private Map<Integer, SheetCellHeader> indexCache = new HashMap<Integer, SheetCellHeader>();

    /**
     * 行数据
     */
    private List<SheetRow> rowList = new ArrayList<SheetRow>();
    public boolean checkField(SheetCellHeader sheetCellHeader, int col){
        if(headCache.containsKey(sheetCellHeader.getEnglishName())){
            return false;
        }

        headCache.put(sheetCellHeader.getEnglishName(), sheetCellHeader);
        indexCache.put(col, sheetCellHeader);
        return true;
    }

    public void addSheerRow(SheetRow sheetRow){
        rowList.add(sheetRow);
    }

    public List<SheetRow> getRowList() {
        return rowList;
    }

    public void setRowList(List<SheetRow> rowList) {
        this.rowList = rowList;
    }

    public SheetCellHeader getSheetCellHeaderByIndex(int index){
        return indexCache.get(index);
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    /**
     * 获取所有cell头部
     * @return
     */
    public List<SheetCellHeader> getCellHeads(){
        return new ArrayList<SheetCellHeader>(headCache.values());
    }
 }
