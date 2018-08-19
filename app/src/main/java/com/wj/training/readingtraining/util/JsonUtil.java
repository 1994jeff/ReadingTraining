package com.wj.training.readingtraining.util;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by jeff on 18-8-8.
 */

public class JsonUtil {

    public static final String RET_CODE = "retCode";
    public static final String RET_MSG = "retMsg";
    public static final String SUCCESS = "success";
    public static final String RET_DATA = "retData";
    public static final String FAILED = "failed";

    public static JSONObject getJSONObject(String result){
        String json = result.replace("\\", "");
        json = json.substring(1, json.length() - 1);
        JSONObject object = (JSONObject) JSON.parse(json);
        return object;
    }

    public static JSONArray getJSONArray(JSONObject object){
        JSONArray jsonArray = object.getJSONArray(RET_DATA);
        if(jsonArray.size()<=0){
            return null;
        }
        return jsonArray;
    }

}
