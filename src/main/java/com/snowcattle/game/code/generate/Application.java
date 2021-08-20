package com.snowcattle.game.code.generate;

import com.snowcattle.game.code.prase.XSSFWorkBookExcelPraser;
import com.snowcattle.game.code.utils.EnvParam;
import com.snowcattle.game.code.utils.StartCmdEnum;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableConfigurationProperties({ConfigBean.class})
public class Application {

	public static void main(String[] args) throws  Exception{
		ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
		doCmd(args);
	}

	public static void doCmd(String[] args) throws  Exception{

		//输出启动参数，第一个参数为命令
		String cmd = args[0];
		if(cmd.equals(StartCmdEnum.generateJson.toString())){

			String excelFilePath = "/Users/jiangwenping/data/gameley/github/GameCodeGenerate/src/main/resources/example.xlsx";
			System.out.println("start cmd " + StartCmdEnum.generateJson.toString());
			new XSSFWorkBookExcelPraser().praseExcel(excelFilePath);

		}
	}

}
