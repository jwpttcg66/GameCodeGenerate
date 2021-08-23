package com.snowcattle.game.code.generate;

import com.snowcattle.game.code.prase.GlobalSheetCheck;
import com.snowcattle.game.code.prase.SheetResult;
import com.snowcattle.game.code.prase.XSSFWorkBookExcelPraser;
import com.snowcattle.game.code.utils.CheckException;
import com.snowcattle.game.code.config.EnvParam;
import com.snowcattle.game.code.utils.FileUtils;
import com.snowcattle.game.code.utils.StartCmdEnum;
import com.snowcattle.game.code.writer.java.JavaPoGenerater;
import com.snowcattle.game.code.writer.json.JSonGenerater;
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
		GlobalSheetCheck globalSheetCheck = new GlobalSheetCheck();

		if(cmd.equals(StartCmdEnum.generateJson.toString())){
			exportJSONAndJava(true, false);
		}else if(cmd.equals(StartCmdEnum.generateJavaPo.toString())){
			exportJSONAndJava(false, true);
		}else if(cmd.equals(StartCmdEnum.getGenerateJsonJavaPo.toString())){
			exportJSONAndJava(true, true);
		}
	}

	public static void exportJSONAndJava(boolean jsonFlag, boolean javaFlag) throws IOException, CheckException {
		GlobalSheetCheck globalSheetCheck = new GlobalSheetCheck();
		String dirPath = EnvParam.getxlsPath();
		Map<String, File> allFiles = FileUtils.recursiveFiles(dirPath);
		for(String key: allFiles.keySet()){
			File file = allFiles.get(key);
			XSSFWorkBookExcelPraser xssfWorkBookExcelPraser = new XSSFWorkBookExcelPraser();
			xssfWorkBookExcelPraser.praseExcel(file.getPath());
			//解析所有sheet
			List<SheetResult> resultList = xssfWorkBookExcelPraser.getSheetResultList();
			for(SheetResult sheetResult: resultList){
				String sheetName = sheetResult.getSheetName();
				String newSheeTName = sheetName;
				if(jsonFlag){
					String fileEndName = ".json";
					newSheeTName = sheetName + fileEndName;
					String destFileRootPath = FileUtils.getFrontDestRootPath(key);
					if(globalSheetCheck.isExsitSheet(newSheeTName)){
						throw new CheckException(" newSheeTName: " + sheetName + " is exsit");
					}
					globalSheetCheck.addSheetName(newSheeTName);
					new JSonGenerater().writeJsonFile(destFileRootPath + newSheeTName, sheetResult);
				}

				if(javaFlag) {
					String fileEndName = ".java";
					newSheeTName = sheetName + fileEndName;
					String destFileRootPath = FileUtils.getFrontDestRootPath(key);
					if(globalSheetCheck.isExsitSheet(newSheeTName)){
						throw new CheckException(" newSheeTName: " + sheetName + " is exsit");
					}
					globalSheetCheck.addSheetName(newSheeTName);
					new JavaPoGenerater().writeJavaPoFile(destFileRootPath + newSheeTName, sheetResult);
				}

			}
		}
	}

}
