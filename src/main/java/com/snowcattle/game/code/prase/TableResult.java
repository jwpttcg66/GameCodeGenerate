package com.snowcattle.game.code.prase;

import java.util.ArrayList;
import java.util.List;

/**
 * 表结果
 */
public class TableResult {

    private String tableName;

    private List<TableColumnDefinitionParam> tableColumnDefinitionParamList = new ArrayList<TableColumnDefinitionParam>();

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<TableColumnDefinitionParam> getTableColumnDefinitionParamList() {
        return tableColumnDefinitionParamList;
    }

    public void setTableColumnDefinitionParamList(List<TableColumnDefinitionParam> tableColumnDefinitionParamList) {
        this.tableColumnDefinitionParamList = tableColumnDefinitionParamList;
    }

    public void addTableColumnDefinitionParam(TableColumnDefinitionParam tableColumnDefinitionParam){
        this.tableColumnDefinitionParamList.add(tableColumnDefinitionParam);
    }


}
