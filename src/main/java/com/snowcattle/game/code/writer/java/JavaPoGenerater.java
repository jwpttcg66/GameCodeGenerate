package com.snowcattle.game.code.writer.java;

import com.snowcattle.game.code.config.EnvParam;
import com.snowcattle.game.code.prase.SheetCellHeader;
import com.snowcattle.game.code.prase.SheetResult;
import com.snowcattle.game.code.utils.FileUtils;
import freemarker.template.*;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.List;

public class JavaPoGenerater {

    private static Configuration cfg = new Configuration();

    public void writeJavaPoFile(String relativePath, SheetResult sheetResult, boolean xmlFlag){

        try {
            Template temp = null;
            File loadTemplateFile = ResourceUtils.getFile("classpath:ftl/entity.ftl");

            //设置freemaker 文件加载目录
            cfg.setDirectoryForTemplateLoading(loadTemplateFile.getParentFile());

            PoClassParam poClassParam = tranferPoClass(sheetResult, xmlFlag);
            writeJavaFile(relativePath, poClassParam, xmlFlag);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    private void writeJavaFile(String relativePath, PoClassParam poClassParam, boolean xmlFlag) throws FileNotFoundException {

        String dirPath = EnvParam.getJavaDictPath();
        if(xmlFlag){
            dirPath = EnvParam.getXmlJavaDictPoPath();
        }
        String filePath = relativePath;
        //这里需要转化下包名，还有excel路径
        filePath = FileUtils.getEndDestRootPath(filePath);
        try {
            new JavaPoWriter().writeFile(dirPath, filePath, cfg, poClassParam);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private PoClassParam tranferPoClass(SheetResult sheetResult, boolean xmlFlag){
        PoClassParam poClassParam = new PoClassParam();
        String packgeName = EnvParam.getJavaDictPackage();
        if(xmlFlag){
            packgeName = EnvParam.getXmlJavaDictPackage();
        }
        poClassParam.setPackageName(packgeName);
        poClassParam.setClassName(sheetResult.getSheetName());

        //生成字段
        List<SheetCellHeader>  headers = sheetResult.getCellHeads();
        for(SheetCellHeader sheetCellHeader: headers){
            FieldParam fieldParam = new FieldParam();
            fieldParam.setFiledName(sheetCellHeader.getEnglishName());
            fieldParam.setFiledType(sheetCellHeader.getFieldType());
            fieldParam.setFileldComment(sheetCellHeader.getChineseName());
            poClassParam.addFieldParam(fieldParam);
        }
        return poClassParam;
    }


}
