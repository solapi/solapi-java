package model.request;

import java.util.ArrayList;
import com.google.gson.JsonObject;

public class MessageList {
    private ArrayList<JsonObject> messages;

    public MessageList() {
      messages = new ArrayList<JsonObject>();
    }

    public MessageList(ArrayList<JsonObject> messages) {
        this.messages = messages;
    }

    public void add(Message message) {
        messages.add(message.toJson());
    }
}
