package com.snowcattle.game.code.writer.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.snowcattle.game.code.prase.SheetResult;
import com.snowcattle.game.code.prase.SheetRow;
import org.springframework.boot.autoconfigure.jsonb.JsonbAutoConfiguration;

import java.util.List;

/**
 * 生成Json
 */
public class JSonGenerater {

    public JSONArray generateJson(SheetResult sheetResult){

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

}
