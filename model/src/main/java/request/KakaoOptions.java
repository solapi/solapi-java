package model.request;

import com.google.gson.JsonObject;

public class KakaoOptions {
    String pfId;
    String templateId;
    boolean disableSms;
    String imageId;
    KakaoButtons buttons;

    public KakaoOptions(String pfId, String templateId, boolean enableSms, String imageId, KakaoButtons buttons) {
        this.pfId = pfId;
        this.templateId = templateId;
        this.disableSms = !enableSms;
        this.imageId = imageId;
        this.buttons = buttons;
    }

    String getPfId() {
        return pfId;
    }

    String getTemplateId() {
        return templateId;
    }

    public JsonObject toJson() {
        JsonObject options = new JsonObject();

        options.addProperty("pfId", pfId);
        options.addProperty("templateId", templateId);
        options.addProperty("disableSms", disableSms);
        options.addProperty("imageId", imageId);
        if (buttons != null) options.add("buttons", buttons.toJson());

        return options;
    }
}
