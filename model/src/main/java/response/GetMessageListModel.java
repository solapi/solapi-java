package model.response;

import com.google.gson.JsonObject;

public class GetMessageListModel {
    private int offset = 0;
    private int limit = 0;
    private String nextKey = null;
    private JsonObject messageList;

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public String getNextKey() {
        return nextKey;
    }

    public JsonObject getMessageList() {
        return messageList;
    }
}
