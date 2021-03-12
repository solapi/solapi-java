package model.response;

import com.google.gson.JsonObject;
import java.util.ArrayList;

public class KakaoTemplate {
    private String templateId;
    private String name;
    private String pfId;
    private String content;
    private String dateCreated;
    private String dateUpdated;
    private ArrayList<JsonObject> buttons;

    public String getTemplateId() {
        return templateId;
    }

    public String getName() {
        return name;
    }

    public String getPfId() {
        return pfId;
    }

    public String getContent() {
        return content;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public ArrayList<JsonObject> getButtons() {
        return buttons;
    }
}
