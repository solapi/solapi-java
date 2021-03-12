package model.response;

import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class KakaoTemplateList {
    private ArrayList<KakaoTemplate> templateList;
    private int offset;
    private int limit;
    private int hasNext;

    public ArrayList<KakaoTemplate> getTemplateList() {
        return templateList;
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public int getHasNext() {
        return hasNext;
    }
}
