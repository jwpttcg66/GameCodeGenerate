package com.snowcattle.game.code.prase;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.io.Files;
import com.snowcattle.game.code.utils.CheckException;
import com.snowcattle.game.code.utils.SqlUtils;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.util.TablesNamesFinder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *  单文件sql解析
 */
public class SqlParser {

    public List<TableResult> tableResultList = new ArrayList<TableResult>();

    public void prase(String sqlPath) throws CheckException {
        try {
            File file = new File(sqlPath);
            String sqlString = new String(Files.toByteArray(file), Charsets.UTF_8).toLowerCase();
            List<Statement> statements  = CCJSqlParserUtil.parseStatements(sqlString).getStatements();
            for(Statement statement: statements) {
                if(statement instanceof CreateTable) {


                    CreateTable createStatement = (CreateTable) statement;
                    TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
                    List<String> tableList = tablesNamesFinder.getTableList(createStatement);
                    String tableName = tableList.get(0).toString();
                    System.out.println("解析文件名：" + file.getName() + " 表名：" + tableName + " ");

                    TableResult  tableResult = new TableResult();
                    tableResult.setTableName(tableName);

                    List<ColumnDefinition>  columnDefinitionList = createStatement.getColumnDefinitions();
                    for(ColumnDefinition columnDefinition: columnDefinitionList){
//                        System.out.println("表名：" + tableName + " 列名 " + columnDefinition.getColumnName() + " 类型 " + columnDefinition.getColDataType());
//                        System.out.println(SqlUtils.findSqlColnamePoFileTypeName(columnDefinition.getColDataType().toString()));
                        //获取注释
                        List<String>  stringList = columnDefinition.getColumnSpecs();
                        String commentString = null;
                        if(stringList != null && stringList.size() > 0) {
                            String commment = Joiner.on(",").join(stringList);
                            if(commment.contains("comment")){
                                //找到紧随其后的注释
                                for(int i = 0; i < stringList.size(); i++){
                                    String string = stringList.get(i);
                                    if(string.contains("comment")){
                                        commentString = stringList.get(i+1);
                                        break;
                                    }
                                }
//							System.out.println("找到注释" + commentString);
                            }
                        }
                        TableColumnDefinitionParam tableColumnDefinitionParam = new TableColumnDefinitionParam();
                        tableColumnDefinitionParam.setFiledName(columnDefinition.getColumnName());
                        tableColumnDefinitionParam.setFiledType(SqlUtils.findSqlColnamePoFileTypeName(columnDefinition.getColDataType().toString()));
                        tableColumnDefinitionParam.setFileldComment(commentString);
                        tableResult.addTableColumnDefinitionParam(tableColumnDefinitionParam);
                    }

                    tableResultList.add(tableResult);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            if( e instanceof  CheckException){
                throw new CheckException(e.getMessage());
            }
        }

//        System.out.println(tableResultList.toString());

    }

    public List<TableResult> getTableResultList() {
        return tableResultList;
    }

    public void setTableResultList(List<TableResult> tableResultList) {
        this.tableResultList = tableResultList;
    }


}
