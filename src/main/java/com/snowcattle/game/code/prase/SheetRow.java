package com.snowcattle.game.code.prase;

import java.util.ArrayList;
import java.util.List;

/**
 * 每行的数据
 */
public class SheetRow {


    /**
     * 缓存数据
     */
    private List<String > rowData = new ArrayList<String>();
    public void addData(String data){
        rowData.add(data);
    }

    public String getValueByIndex(int index){
        return rowData.get(index);
    }

    public List<String> getRowData() {
        return rowData;
    }

    public void setRowData(List<String> rowData) {
        this.rowData = rowData;
    }

}
