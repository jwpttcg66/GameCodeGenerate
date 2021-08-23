package com.snowcattle.game.code.writer.java;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * 对象
 */
public class PoClassParam {

    /**
     * 对象名字
     */
    private String className;

    /**
     * 对象注释
     */
    private String classComment;

    /**
     * 方法列表
     */
    private List<FieldParam> list = new ArrayList<FieldParam>();

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassComment() {
        return classComment;
    }

    public void setClassComment(String classComment) {
        this.classComment = classComment;
    }

    public List<FieldParam> getList() {
        return list;
    }

    public void setList(List<FieldParam> list) {
        this.list = list;
    }
}
