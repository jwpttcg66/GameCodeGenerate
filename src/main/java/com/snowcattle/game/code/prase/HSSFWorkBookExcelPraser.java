package com.snowcattle.game.code.prase;

import org.apache.poi.hssf.usermodel.HSSFName;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * 解析2003excel
 */
public class HSSFWorkBookExcelPraser {

    public void praseExcel(String excelPath){
        try {
            FileInputStream fileInputStream = new FileInputStream(excelPath);
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
