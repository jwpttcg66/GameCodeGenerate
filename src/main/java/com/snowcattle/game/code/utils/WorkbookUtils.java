package com.snowcattle.game.code.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.dom4j.Element;

import java.text.NumberFormat;

public class WorkbookUtils {

    /**
     * 把单元格的内容转为字符串
     * @param cell 单元格
     * @return 字符串
     */
    public static String getString(XSSFCell cell) {

        String result = "";
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_STRING:
                result = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                result = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_BLANK:
                result = "";
                break;
            case Cell.CELL_TYPE_NUMERIC:
//                result = String.valueOf(cell.getNumericCellValue());
                NumberFormat nf = NumberFormat.getInstance();
                nf.setGroupingUsed(false);
                result = String.valueOf(nf.format(cell.getNumericCellValue()));

                break;
            case Cell.CELL_TYPE_FORMULA:
                result = String.valueOf(cell.getCellFormula());
                break;
        }

        return result;
    }

    /**
     * 把单元格的内容转为字符串
     * @param cellElement 单元格
     * @return 字符串
     */
    public static String getString(Element cellElement) {

        String result = "";
        Element Element= cellElement.element("Data");
        if(Element != null) {
            result = Element.getStringValue();
        }
//        switch (cell.getCellType()){
//            case Cell.CELL_TYPE_STRING:
//                result = cell.getStringCellValue();
//                break;
//            case Cell.CELL_TYPE_BOOLEAN:
//                result = String.valueOf(cell.getBooleanCellValue());
//                break;
//            case Cell.CELL_TYPE_BLANK:
//                result = "";
//                break;
//            case Cell.CELL_TYPE_NUMERIC:
////                result = String.valueOf(cell.getNumericCellValue());
//                NumberFormat nf = NumberFormat.getInstance();
//                nf.setGroupingUsed(false);
//                result = String.valueOf(nf.format(cell.getNumericCellValue()));
//
//                break;
//            case Cell.CELL_TYPE_FORMULA:
//                result = String.valueOf(cell.getCellFormula());
//                break;
//        }

        return result;
    }

}
