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
import org.dom4j.DocumentException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.io.IOException;
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
			xlsxExportJSONAndJava(true, false);
		}else if(cmd.equals(StartCmdEnum.generateJavaPo.toString())){
			xlsxExportJSONAndJava(false, true);
		}else if(cmd.equals(StartCmdEnum.generateJsonAndJavaPo.toString())){
			xlsxExportJSONAndJava(true, true);
		}else if(cmd.equals(StartCmdEnum.generateaSqlPo.toString())){
			exportSqlPo();
		}else if(cmd.equals(StartCmdEnum.generateXmlJson.toString())){
			xmlExportJSONAndJava(true, false);
		}else if(cmd.equals(StartCmdEnum.generateXmlJavaPo.toString())){
			xmlExportJSONAndJava(false, true);
		}else if(cmd.equals(StartCmdEnum.generateXmlJsonAndJavaPo.toString())){
			xmlExportJSONAndJava(true, true);
		}
	}

	public static void exportSqlPo() throws IOException, JSQLParserException, CheckException  {
		GlobalFileCheck globalFileCheck = new GlobalFileCheck();
		String dirPath = EnvParam.getSqlPath();
		String fileExtension = ".sql";
		Map<String, File> allFiles = FileUtils.recursiveFiles(dirPath, fileExtension);
		for(String key: allFiles.keySet()){
			File file = allFiles.get(key);
			SqlParser sqlParser = new SqlParser();
			sqlParser.prase(file.getPath());
			List<TableResult> tableResults = sqlParser.getTableResultList();
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

	public static void xlsxExportJSONAndJava(boolean jsonFlag, boolean javaFlag) throws IOException, CheckException {
		GlobalFileCheck globalFileCheck = new GlobalFileCheck();
		String dirPath = EnvParam.getxlsPath();
		String fileExtension = ".xlsx";
		Map<String, File> allFiles = FileUtils.recursiveFiles(dirPath, fileExtension);
		for(String key: allFiles.keySet()){
			File file = allFiles.get(key);
			XSSFWorkBookExcelParser xssfWorkBookExcelParser = new XSSFWorkBookExcelParser();
			xssfWorkBookExcelParser.praseExcel(file.getPath());
			//解析所有sheetnew
			List<SheetResult> resultList = xssfWorkBookExcelParser.getSheetResultList();
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
					new JSonGenerater().writeJsonFile(destFileRootPath + newSheeTName, sheetResult, false);
				}

				if(javaFlag) {
					String fileEndName = ".java";
					newSheeTName = sheetName + fileEndName;
					String destFileRootPath = FileUtils.getFrontDestRootPath(key);
					if(globalFileCheck.isExsitFile(newSheeTName)){
						throw new CheckException(" newSheeTName: " + sheetName + " is exsit");
					}
					globalFileCheck.addFileName(newSheeTName);
					new JavaPoGenerater().writeJavaPoFile(destFileRootPath + newSheeTName, sheetResult,false);
				}

			}
		}
	}

	public static void xmlExportJSONAndJava(boolean jsonFlag, boolean javaFlag) throws IOException, CheckException, DocumentException {
		GlobalFileCheck globalFileCheck = new GlobalFileCheck();
		String dirPath = EnvParam.getXmlPath();
		String fileExtension = ".xml";
		Map<String, File> allFiles = FileUtils.recursiveFiles(dirPath, fileExtension);
		for(String key: allFiles.keySet()){
			File file = allFiles.get(key);
			XmlWorkBookParser xmlWorkBookParser = new XmlWorkBookParser();
			xmlWorkBookParser.praseXml(file.getPath());
//			//解析所有sheetnew
			List<SheetResult> resultList = xmlWorkBookParser.getSheetResultList();
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
					new JSonGenerater().writeJsonFile(destFileRootPath + newSheeTName, sheetResult, true);
				}

				if(javaFlag) {
					String fileEndName = ".java";
					newSheeTName = sheetName + fileEndName;
					String destFileRootPath = FileUtils.getFrontDestRootPath(key);
					if(globalFileCheck.isExsitFile(newSheeTName)){
						throw new CheckException(" newSheeTName: " + sheetName + " is exsit");
					}
					globalFileCheck.addFileName(newSheeTName);
					new JavaPoGenerater().writeJavaPoFile(destFileRootPath + newSheeTName, sheetResult, true);
				}

			}
		}
	}

}
