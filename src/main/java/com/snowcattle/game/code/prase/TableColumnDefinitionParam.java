package com.snowcattle.game.code.prase;

/**
 * 列定义
 */
public class TableColumnDefinitionParam {
    /**
     * 字段名字
     */
    private String filedName;

    /**
     * 类型（short, int）
     */
    private String filedType;

    /**
     * 注释
     */
    private String fileldComment;

    public String getFiledName() {
        return filedName;
    }

    public void setFiledName(String filedName) {
        this.filedName = filedName;
    }

    public String getFiledType() {
        return filedType;
    }

    public void setFiledType(String filedType) {
        this.filedType = filedType;
    }

    public String getFileldComment() {
        return fileldComment;
    }

    public void setFileldComment(String fileldComment) {
        this.fileldComment = fileldComment;
    }
}
