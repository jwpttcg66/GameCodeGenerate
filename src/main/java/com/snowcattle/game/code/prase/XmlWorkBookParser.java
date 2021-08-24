package com.snowcattle.game.code.prase;

import com.snowcattle.game.code.utils.CheckException;
import com.snowcattle.game.code.utils.WorkbookUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.dom4j.Attribute;
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

    public void praseXml(String xmlPath) throws CheckException, DocumentException {
        try {
            SAXReader reader = new SAXReader();
            FileInputStream fileInputStream = new FileInputStream(xmlPath);
            Document doc = reader.read(fileInputStream);
            //获得文档对象的根元素
            Element root = doc.getRootElement();
            //获得根元素下的孩子元素
            List<Element> rootList = root.elements();

            List<Element> workSheetList = new ArrayList<Element>();
            //遍历子元素
            for(Element element:rootList) {
                //关注worksheet节点
                String eleName = element.getName();
//                System.out.println(eleName);
                if(eleName.equals("Worksheet")){
                    workSheetList.add(element);
                }
            }
            int sheetSize = workSheetList.size();
            for(int i = 0; i<sheetSize; i++){
                Element element = workSheetList.get(i);
                Attribute attribute = element.attribute(0);
                String sheetName = attribute.getValue();
                System.out.println("解析文件: " + xmlPath + " 表名 " + sheetName );
                SheetResult sheetResult = new SheetResult();
                sheetResult.setSheetName(sheetName);

                initHeader(element, sheetResult);
                praseBody(element, sheetResult);
                sheetResultList.add(sheetResult);
            }
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            if( e instanceof  CheckException){
                throw new CheckException(e.getMessage());
            }
        }

    }

    public void initHeader(Element element, SheetResult sheetResult) throws CheckException {
        //前三行为解析头部
        int headRowSize = 3;
        Element tableElement = element.element("Table");
        List<Element> allRows = tableElement.elements("Row");
        Element firstElement = allRows.get(0);
        List<Element> cellElements = firstElement.elements("Cell");
        int colSize = cellElements.size();
        for(int k = 0;  k< colSize; k++){
            Element row0 = allRows.get(0);
            List<Element> cellElements0= row0.elements("Cell");
            Element cell0 = cellElements0.get(k);
            Element dataElement0 = cell0.element("Data");
            String chineseName = dataElement0.getStringValue();
//            System.out.println(chineseName);

            Element row1 = allRows.get(1);
            List<Element> cellElements1= row1.elements("Cell");
            Element cell1 = cellElements1.get(k);
            Element dataElement1 = cell1.element("Data");
            String englishName = dataElement1.getStringValue();
//            System.out.println(englishName);

            Element row2 = allRows.get(2);
            List<Element> cellElements2= row2.elements("Cell");
            Element cell2 = cellElements2.get(k);
            Element dataElement2 = cell2.element("Data");
            String filedName = dataElement2.getStringValue();
//            System.out.println(filedName);

            SheetCellHeader sheetCellHeader = new SheetCellHeader();
            sheetCellHeader.setChineseName(chineseName);
            sheetCellHeader.setEnglishName(englishName);
            sheetCellHeader.setFieldType(filedName);

            boolean check = sheetResult.checkField(sheetCellHeader, k);
            if(!check){
                throw new CheckException("filed check is error");
            }

        }
    }


    /**
     * 解析整个sheet数据
     * @param sheetResult
     */
    public void praseBody(Element element, SheetResult sheetResult){
        Element tableElement = element.element("Table");
        List<Element> allRows = tableElement.elements("Row");

        for(int i = headRowSize;i < allRows.size(); i++){

            Element row = allRows.get(i);
            SheetRow sheetRow = new SheetRow();
            List<Element> cellElements = row.elements("Cell");
            int colSize = cellElements.size();

            for(int k = 0;  k < colSize; k++){
                Element cellElement = cellElements.get(k);
                String value = WorkbookUtils.getString(cellElement);
                sheetRow.addData(value);
            }
            sheetResult.addSheerRow(sheetRow);
        }

        String sheetName = sheetResult.getSheetName();
        System.out.println("sheet " + sheetName +"解析完成");

    }


}
