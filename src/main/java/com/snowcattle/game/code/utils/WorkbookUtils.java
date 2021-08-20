package com.snowcattle.game.code.utils;

import com.sun.java.accessibility.util.AccessibilityListenerList;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.text.NumberFormat;

public class WorkbookUtils {
    /**
     * 静态方法  解决创建Workbook 创建产生的问题
     * @param inp
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     */
    public static Workbook createworkbook(InputStream inp) throws IOException, InvalidFormatException {
        if (!inp.markSupported()) {
            inp = new PushbackInputStream(inp, 8);
        }
//        if (POIFSFileSystem.hasPOIFSHeader(inp)) {
//            return new HSSFWorkbook(inp);
//        }
//        if (POIXMLDocument.hasOOXMLHeader(inp)) {
//            return new XSSFWorkbook(OPCPackage.open(inp));
//        }
        throw new IllegalArgumentException("你的excel版本目前poi解析不了");
    }

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

}
