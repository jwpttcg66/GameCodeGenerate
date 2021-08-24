package com.snowcattle.game.code.generate;

import com.snowcattle.game.code.prase.*;
import com.snowcattle.game.code.utils.CheckException;
import com.snowcattle.game.code.config.EnvParam;
import com.snowcattle.game.code.utils.FileUtils;
import com.snowcattle.game.code.utils.StartCmdEnum;
import com.snowcattle.game.code.writer.java.JavaPoGenerater;
import com.snowcattle.game.code.writer.java.SqlJavaPoGenerater;
import com.snowcattle.game.code.writer.json.JSonGenerater;
import net.sf.jsqlparser.JSQLParserException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws  Exception{
		ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);

		ConfigBean configBean = (ConfigBean) applicationContext.getBean("configBean");
		EnvParam.setConfigBean(configBean);

		doCmd(args);
	}

	public static void doCmd(String[] args) throws  Exception{

		//输出启动参数，第一个参数为命令
		String cmd = args[0];

		if(cmd.equals(StartCmdEnum.generateJson.toString())){
			exportJSONAndJava(true, false);
		}else if(cmd.equals(StartCmdEnum.generateJavaPo.toString())){
			exportJSONAndJava(false, true);
		}else if(cmd.equals(StartCmdEnum.generateJsonAndJavaPo.toString())){
			exportJSONAndJava(true, true);
		}else if(cmd.equals(StartCmdEnum.generateaSqlPo.toString())){
			exportSqlPo();
		}
	}

	public static void exportSqlPo() throws IOException, JSQLParserException, CheckException  {
		GlobalFileCheck globalFileCheck = new GlobalFileCheck();
		String dirPath = EnvParam.getSqlPath();
		String fileExtension = ".sql";
		Map<String, File> allFiles = FileUtils.recursiveFiles(dirPath, fileExtension);
		for(String key: allFiles.keySet()){
			File file = allFiles.get(key);
			SqlPraser sqlPraser = new SqlPraser();
			sqlPraser.prase(file.getPath());
			List<TableResult> tableResults = sqlPraser.getTableResultList();
			for(TableResult tableResult: tableResults){
				String tableName = tableResult.getTableName();
				String newTableName = tableName;
				String fileEndName = ".java";
				SqlJavaPoGenerater sqlJavaPoGenerater  = new SqlJavaPoGenerater();
				newTableName = sqlJavaPoGenerater.transfeSqlName(newTableName);
				newTableName = newTableName + fileEndName;
				String destFileRootPath = FileUtils.getFrontDestRootPath(key);
				if(globalFileCheck.isExsitFile(newTableName)){
					throw new CheckException(" newTableName: " + tableName + " is exsit");
				}
				globalFileCheck.addFileName(newTableName);
				sqlJavaPoGenerater.writeJavaPoFile(destFileRootPath + newTableName, tableResult);
			}
		}

	}

	public static void exportJSONAndJava(boolean jsonFlag, boolean javaFlag) throws IOException, CheckException {
		GlobalFileCheck globalFileCheck = new GlobalFileCheck();
		String dirPath = EnvParam.getxlsPath();
		String fileExtension = ".xlsx";
		Map<String, File> allFiles = FileUtils.recursiveFiles(dirPath, fileExtension);
		for(String key: allFiles.keySet()){
			File file = allFiles.get(key);
			XSSFWorkBookExcelPraser xssfWorkBookExcelPraser = new XSSFWorkBookExcelPraser();
			xssfWorkBookExcelPraser.praseExcel(file.getPath());
			//解析所有sheetnew
			List<SheetResult> resultList = xssfWorkBookExcelPraser.getSheetResultList();
			for(SheetResult sheetResult: resultList){
				String sheetName = sheetResult.getSheetName();
				String newSheeTName = sheetName;
				if(jsonFlag){
					String fileEndName = ".json";
					newSheeTName = sheetName + fileEndName;
					String destFileRootPath = FileUtils.getFrontDestRootPath(key);
					if(globalFileCheck.isExsitFile(newSheeTName)){
						throw new CheckException(" newSheeTName: " + sheetName + " is exsit");
					}
					globalFileCheck.addFileName(newSheeTName);
					new JSonGenerater().writeJsonFile(destFileRootPath + newSheeTName, sheetResult);
				}

				if(javaFlag) {
					String fileEndName = ".java";
					newSheeTName = sheetName + fileEndName;
					String destFileRootPath = FileUtils.getFrontDestRootPath(key);
					if(globalFileCheck.isExsitFile(newSheeTName)){
						throw new CheckException(" newSheeTName: " + sheetName + " is exsit");
					}
					globalFileCheck.addFileName(newSheeTName);
					new JavaPoGenerater().writeJavaPoFile(destFileRootPath + newSheeTName, sheetResult);
				}

			}
		}
	}

}
