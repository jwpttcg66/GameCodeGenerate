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
    private List<FieldParam> fieldParams = new ArrayList<FieldParam>();

    /**
     * 包名
     */
    private String packageName;

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

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<FieldParam> getFieldParams() {
        return fieldParams;
    }

    public void setFieldParams(List<FieldParam> fieldParams) {
        this.fieldParams = fieldParams;
    }

    public void addFieldParam(FieldParam fieldParam){
        this.fieldParams.add(fieldParam);
    }
}
