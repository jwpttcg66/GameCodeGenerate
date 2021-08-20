package com.snowcattle.game.code.prase;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONPObject;
import com.alibaba.fastjson.JSONWriter;
import com.snowcattle.game.code.generate.JSonFileWriter;
import com.snowcattle.game.code.generate.JSonGenerater;
import com.snowcattle.game.code.utils.CheckException;

import com.snowcattle.game.code.utils.EnvParam;
import com.snowcattle.game.code.utils.WorkbookUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * excel2007解析器
 */
public class XSSFWorkBookExcelPraser {

    private int headRowSize = 3;

    private List<SheetResult> sheetResultList = new ArrayList<SheetResult>();

    public List<SheetResult> getSheetResultList() {
        return sheetResultList;
    }

    public void setSheetResultList(List<SheetResult> sheetResultList) {
        this.sheetResultList = sheetResultList;
    }

    public void praseExcel(String excelPath) throws CheckException {
        try {
            FileInputStream fileInputStream = new FileInputStream(excelPath);
            XSSFWorkbook workbook = new XSSFWorkbook(OPCPackage.open(fileInputStream));
            int size = workbook.getNumberOfSheets();
            for(int i = 0; i<size; i++){
                XSSFSheet sheet = workbook.getSheetAt(i);
                String sheetName = sheet.getSheetName();
                System.out.println("解析文件: " + excelPath + " 表名 " + sheetName );

                SheetResult sheetResult = new SheetResult();
                sheetResult.setSheetName(sheetName);
                initHeader(sheet, sheetResult);
                praseBody(sheet, sheetResult);
                sheetResultList.add(sheetResult);
//                generateJson(sheetResult);
//                writeJsonFile(sheetResult);
            }
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            if( e instanceof  CheckException){
                throw new CheckException(e.getMessage());
            }
        }

    }

    public void initHeader(XSSFSheet sheet, SheetResult sheetResult) throws CheckException {
        //前三行为解析头部
        int headRowSize = 3;
        XSSFRow row = sheet.getRow(0);
        int colSize = row.getPhysicalNumberOfCells();
//        System.out.println(colSize);
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

            SheetCellHeader sheetCellHeader = new SheetCellHeader();
            sheetCellHeader.setChineseName(chineseName);
            sheetCellHeader.setEnglishName(englishName);
            sheetCellHeader.setFieldName(filedName);

            boolean check = sheetResult.checkField(sheetCellHeader, k);
            if(!check){
                throw new CheckException("filed check is error");
            }

        }
    }

    /**
     * 解析整个sheet数据
     * @param sheet
     */
    public void praseBody(XSSFSheet sheet, SheetResult sheetResult){
        for(int i = headRowSize;i < sheet.getPhysicalNumberOfRows(); i++){
            XSSFRow row = sheet.getRow(i);
            SheetRow sheetRow = new SheetRow();
            int colSize = row.getPhysicalNumberOfCells();
            for(int k = 0;  k < colSize; k++){
                XSSFCell celll = row.getCell(k);
                String value = WorkbookUtils.getString(celll);
                sheetRow.addData(value);
            }
            sheetResult.addSheerRow(sheetRow);
        }

        String sheetName = sheet.getSheetName();
        System.out.println("sheet " + sheetName +"解析完成");

    }

    private JSONArray generateJson(SheetResult sheetResult){
        JSONArray jsonpObject = new JSonGenerater().generateJson(sheetResult);
        return jsonpObject;
    }

    public void writeJsonFile(SheetResult sheetResult){
        JSONArray jsonArray = generateJson(sheetResult);

        String dirPath = EnvParam.getJsonPath();
        String filePath = "example.json";
        try {
            new JSonFileWriter().writeFile(dirPath, filePath, jsonArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
