package com.snowcattle.game.code.prase;

/**
 * 每个单元格需要具备的头部
 */
public class ExcelCellHeader {

    private String chineseName;
    private String englishName;
    private String fieldName;


    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

}
