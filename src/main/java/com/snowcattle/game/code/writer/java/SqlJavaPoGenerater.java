package com.snowcattle.game.code.writer.java;

import com.snowcattle.game.code.config.EnvParam;
import com.snowcattle.game.code.prase.SheetCellHeader;
import com.snowcattle.game.code.prase.SheetResult;
import com.snowcattle.game.code.prase.TableColumnDefinitionParam;
import com.snowcattle.game.code.prase.TableResult;
import com.snowcattle.game.code.utils.FileUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class SqlJavaPoGenerater {
    private static Configuration cfg = new Configuration();

    public void writeJavaPoFile(String relativePath, TableResult tableResult){

        try {
            Template temp = null;
            File loadTemplateFile = ResourceUtils.getFile("classpath:ftl/entity.ftl");

            //设置freemaker 文件加载目录
            cfg.setDirectoryForTemplateLoading(loadTemplateFile.getParentFile());
            String packageName = EnvParam.getJavaSqlPoDictPackage();

            PoClassParam poClassParam = tranferPoClass(tableResult);
            writeJavaFile(relativePath, poClassParam);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void writeJavaFile(String relativePath, PoClassParam poClassParam) throws FileNotFoundException {

        String dirPath = EnvParam.getJavaSqlPoPath();
        String filePath = relativePath;
        filePath = FileUtils.getEndDestRootPath(filePath);
        try {
            new JavaPoWriter().writeFile(dirPath, filePath, cfg, poClassParam);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private PoClassParam tranferPoClass(TableResult tableResult){
        PoClassParam poClassParam = new PoClassParam();
        String packgeName = EnvParam.getJavaSqlPoDictPackage();
        poClassParam.setPackageName(packgeName);
        poClassParam.setClassName(transfeSqlName(tableResult.getTableName()));

        //生成字段
        List<TableColumnDefinitionParam> tableColumnDefinitionParams = tableResult.getTableColumnDefinitionParamList();
        for(TableColumnDefinitionParam tableColumnDefinitionParam: tableColumnDefinitionParams){
            FieldParam fieldParam = new FieldParam();
            fieldParam.setFiledName(transfeSqlName(tableColumnDefinitionParam.getFiledName()));
            fieldParam.setFiledType(tableColumnDefinitionParam.getFiledType());
            fieldParam.setFileldComment(transfeSqlName(tableColumnDefinitionParam.getFileldComment()));
            poClassParam.addFieldParam(fieldParam);
        }
        return poClassParam;
    }

    public String transfeSqlName(String sqlName){
        if(sqlName == null){
            return null;
        }

        return sqlName.substring(1, sqlName.length() -1);
    }

}
