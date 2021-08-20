package com.snowcattle.game.code.generate;

import com.snowcattle.game.code.prase.HSSFWorkBookExcelPraser;
import com.snowcattle.game.code.prase.XSSFWorkBookExcelPraser;
import com.snowcattle.game.code.utils.StartCmdEnum;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		doCmd(args);
	}

	public static void doCmd(String[] args){
		//输出启动参数，第一个参数为命令
		String cmd = args[0];
		if(cmd.equals(StartCmdEnum.generateJson.toString())){

			String excelFilePath = "/Users/jiangwenping/data/gameley/github/GameCodeGenerate/src/main/resources/example.xlsx";
			System.out.println("start cmd " + StartCmdEnum.generateJson.toString());
			new XSSFWorkBookExcelPraser().praseExcel(excelFilePath);
//			new HSSFWorkBookExcelPraser().praseExcel(excelFilePath);
		}
	}

}
