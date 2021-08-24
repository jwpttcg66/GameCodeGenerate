package com.snowcattle.game.code.prase;

import com.snowcattle.game.code.utils.CheckException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * wps的xml解析器
 */
public class XmlWorkBookParser {
    private int headRowSize = 3;

    /**
     * 表格解析的结果
     */
    private List<SheetResult> sheetResultList = new ArrayList<SheetResult>();

    public void praseXml(String excelPath) throws CheckException, DocumentException {
        try {
            SAXReader reader = new SAXReader();
            FileInputStream fileInputStream = new FileInputStream(excelPath);
            Document doc = reader.read(fileInputStream);
            //获得文档对象的根元素
            Element root = doc.getRootElement();
            System.out.println(root);
//            int size = workbook.getNumberOfSheets();
//            for(int i = 0; i<size; i++){
//                XSSFSheet sheet = workbook.getSheetAt(i);
//                String sheetName = sheet.getSheetName();
//                System.out.println("解析文件: " + excelPath + " 表名 " + sheetName );
//
//                SheetResult sheetResult = new SheetResult();
//                sheetResult.setSheetName(sheetName);
////                initHeader(sheet, sheetResult);
////                praseBody(sheet, sheetResult);
//                sheetResultList.add(sheetResult);
//            }
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            if( e instanceof  CheckException){
                throw new CheckException(e.getMessage());
            }
        }

    }

}
