package model.request;

import com.google.gson.JsonObject;

public class KakaoButtonItem {
    String buttonName;
    String buttonType;
    String linkMo;
    String linkPc;
    String linkAnd;
    String linkIos;

    public KakaoButtonItem(String buttonName, String buttonType, String linkMo, String linkPc, String linkAnd, String linkIos) {
      this.buttonName = buttonName;
      this.buttonType = buttonType;
      this.linkMo = linkMo;
      this.linkPc = linkPc;
      this.linkAnd = linkAnd;
      this.linkIos = linkIos;
    }

    public JsonObject toJson() {
      JsonObject obj = new JsonObject();

      obj.addProperty("buttonName", buttonName);
      obj.addProperty("buttonType", buttonType);
      obj.addProperty("linkMo", linkMo);
      obj.addProperty("linkPc", linkPc);
      obj.addProperty("linkAnd", linkAnd);
      obj.addProperty("linkIos", linkIos);

      return obj;
    }
}
