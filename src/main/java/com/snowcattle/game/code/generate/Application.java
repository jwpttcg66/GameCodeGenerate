package com.snowcattle.game.code.generate;

import com.snowcattle.game.code.prase.SheetResult;
import com.snowcattle.game.code.prase.XSSFWorkBookExcelPraser;
import com.snowcattle.game.code.utils.EnvParam;
import com.snowcattle.game.code.utils.FileUtils;
import com.snowcattle.game.code.utils.StartCmdEnum;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
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

			String dirPath = EnvParam.getxlsPath();
			Map<String, File> allFiles = FileUtils.recursiveFiles(dirPath);
			for(String key: allFiles.keySet()){
				File file = allFiles.get(key);
				XSSFWorkBookExcelPraser xssfWorkBookExcelPraser = new XSSFWorkBookExcelPraser();
				xssfWorkBookExcelPraser.praseExcel(file.getPath());
				List<SheetResult> resultList = xssfWorkBookExcelPraser.getSheetResultList();

				for(SheetResult sheetResult: resultList){
					String sheetName = sheetResult.getSheetName()+ ".json";
					String filePath = FileUtils.getDestRootPath(key);

					xssfWorkBookExcelPraser.writeJsonFile(filePath + sheetName, sheetResult);
				}
			}
		}
	}

}
