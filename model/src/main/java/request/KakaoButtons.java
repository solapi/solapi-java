package model.request;

import java.util.ArrayList;
import com.google.gson.JsonArray;

public class KakaoButtons {
    ArrayList<KakaoButtonItem> buttons;

    public KakaoButtons() {
        buttons = new ArrayList<KakaoButtonItem>();
    }

    public void add(KakaoButtonItem item) {
        buttons.add(item);
    }

    public JsonArray toJson() {
        JsonArray array = new JsonArray();
        for (KakaoButtonItem but: buttons) {
            array.add(but.toJson());
        }
        return array;
    }
}
