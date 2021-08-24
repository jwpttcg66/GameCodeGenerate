package com.snowcattle.game.code.writer.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.snowcattle.game.code.prase.SheetResult;
import com.snowcattle.game.code.prase.SheetRow;
import com.snowcattle.game.code.config.EnvParam;

import java.io.IOException;
import java.util.List;

/**
 * 生成Json
 */
public class JSonGenerater {

    private JSONArray generateJson(SheetResult sheetResult){

        JSONArray jsonArray = new JSONArray();
        List<SheetRow > sheetRowList = sheetResult.getRowList();
        for(int i = 0; i < sheetRowList.size() ; i++){
            SheetRow sheetRow = sheetRowList.get(i);
            JSONObject jsonObject = new JSONObject();
            List<String> dataList = sheetRow.getRowData();
            for(int k = 0; k < dataList.size(); k++){
                jsonObject.put(sheetResult.getSheetCellHeaderByIndex(k).getEnglishName(), sheetRow.getValueByIndex(k));
            }

            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    public void writeJsonFile(String relativePath, SheetResult sheetResult, boolean xmlFlag){
        JSONArray jsonArray = generateJson(sheetResult);

        String dirPath = EnvParam.getJsonPath();
        if(xmlFlag){
            dirPath =  EnvParam.getXmlJsonPath();
        }

        String filePath = relativePath;
        try {
            new JSonFileWriter().writeFile(dirPath, filePath, jsonArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
