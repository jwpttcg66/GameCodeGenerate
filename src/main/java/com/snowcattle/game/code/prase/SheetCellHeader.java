package com.snowcattle.game.code.prase;

/**
 * 每个单元格需要具备的头部
 */
public class SheetCellHeader {

    private String chineseName;
    private String englishName;
    private String fieldType;


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

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

}
