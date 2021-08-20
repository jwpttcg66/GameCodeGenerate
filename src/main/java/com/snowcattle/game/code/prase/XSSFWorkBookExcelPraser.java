package com.snowcattle.game.code.prase;

import com.snowcattle.game.code.utils.CheckException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * excel2007解析器
 */
public class XSSFWorkBookExcelPraser {

    private SheetResult sheetResult = new SheetResult();

    public void praseExcel(String excelPath) throws CheckException, IOException, InvalidFormatException {
        try {
            FileInputStream fileInputStream = new FileInputStream(excelPath);
            XSSFWorkbook workbook = new XSSFWorkbook(OPCPackage.open(fileInputStream));
            int size = workbook.getNumberOfSheets();
            for(int i = 0; i<size; i++){
                XSSFSheet sheet = workbook.getSheetAt(i);
                String sheetName = sheet.getSheetName();
                System.out.println("解析文件: " + excelPath + " 表名 " + sheetName );

                initHeader(sheet);

            }
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            if( e instanceof  CheckException){
                throw e;
            }
        }

    }

    public void initHeader(XSSFSheet sheet) throws CheckException {
        //前三行为解析头部
        int headRowSize = 3;
        XSSFRow row = sheet.getRow(0);
        int colSize = row.getPhysicalNumberOfCells();
        System.out.println(colSize);
        for(int k = 0;  k< colSize; k++){

            XSSFRow row0 = sheet.getRow(0);
            XSSFCell celll0 = row0.getCell(k);
            String chineseName = celll0.getStringCellValue();

            XSSFRow row1 = sheet.getRow(1);
            XSSFCell celll1 = row1.getCell(k);
            String englishName = celll1.getStringCellValue();

            XSSFRow row2 = sheet.getRow(2);
            XSSFCell celll2 = row2.getCell(k);
            String filedName = celll2.getStringCellValue();

            ExcelCellHeader excelCellHeader = new ExcelCellHeader();
            excelCellHeader.setChineseName(chineseName);
            excelCellHeader.setEnglishName(englishName);
            excelCellHeader.setFieldName(filedName);

            boolean check = sheetResult.checkField(excelCellHeader);
            if(check){
                throw new CheckException("filed check is error");
            }

        }
    }




}
