package com.snowcattle.game.code.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

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
}
