package com.snowcattle.game.code.prase;

import com.snowcattle.game.code.utils.WorkbookUtils;
import org.apache.poi.hssf.usermodel.HSSFName;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFName;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * excel2007解析器
 */
public class XSSFWorkBookExcelPraser {

    public void praseExcel(String excelPath){
        try {
            FileInputStream fileInputStream = new FileInputStream(excelPath);
//            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(excelPath));
            XSSFWorkbook workbook = new XSSFWorkbook(OPCPackage.open(fileInputStream));
//            XSSFWorkbook workbook = (XSSFWorkbook) WorkbookUtils.createworkbook(fileInputStream);
            int size = workbook.getNumberOfSheets();

//            HSSFSheet hssfSheet = workbook.getSheetAt(0);
//            HSSFRow row = hssfSheet.getRow(0);//Row
            for(int i = 0; i<size; i++){
                XSSFSheet sheet = workbook.getSheetAt(i);
                String sheetName = sheet.getSheetName();
                System.out.println("解析文件: " + excelPath + " 表名 " + sheetName );

                //前三行为解析头部
                int headRowSize = 3;
                XSSFRow row = sheet.getRow(0);
                int colSize = row.getPhysicalNumberOfCells();
                System.out.println(colSize);
//                row = hssfSheet.getRow(i);
//                for(int j =0;j<row.getLastCellNum();j++){
//                    HSSFCell cell = row.getCell((short)j);//Cell
//                    if(cell!=null){
//                        @SuppressWarnings("deprecation")
//                        String s=getCellContent(cell);//读取单元格String内容
//                        System.out.println(s);
//                    }else{
//
//                        System.out.println("nullllllll");
//                    }
//                }
            }
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
