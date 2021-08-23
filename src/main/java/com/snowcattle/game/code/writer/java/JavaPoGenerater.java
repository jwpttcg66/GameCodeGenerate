package com.snowcattle.game.code.writer.java;

import com.snowcattle.game.code.config.EnvParam;
import com.snowcattle.game.code.prase.SheetCellHeader;
import com.snowcattle.game.code.prase.SheetResult;
import freemarker.template.*;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.List;

public class JavaPoGenerater {

    private static Configuration cfg = new Configuration();

    public void writeJavaPoFile(String relativePath, SheetResult sheetResult){

        try {
            Template temp = null;
            File loadTemplateFile = ResourceUtils.getFile("classpath:ftl/test.ftl");

            //设置freemaker 文件加载目录
            cfg.setDirectoryForTemplateLoading(loadTemplateFile.getParentFile());

            String packageName = EnvParam.getJavaDictPackage();

            PoClassParam poClassParam = tranferPoClass(sheetResult);
            writeJavaFile(relativePath, poClassParam);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    private void writeJavaFile(String relativePath, PoClassParam poClassParam) throws FileNotFoundException {

        String dirPath = EnvParam.getJavaDictPath();
        String filePath = relativePath;
        try {
            new JavaPoWriter().writeFile(dirPath, filePath, cfg, poClassParam);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private PoClassParam tranferPoClass(SheetResult sheetResult){
        PoClassParam poClassParam = new PoClassParam();
        String packgeName = EnvParam.getJavaDictPackage();
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
