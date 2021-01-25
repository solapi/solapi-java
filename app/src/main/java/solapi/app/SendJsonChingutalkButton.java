package solapi.app;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.io.IOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import model.response.GroupModel;
import utilities.APIInit;

/**
 * 한번 요청으로 1만건까지 친구톡 발송이 가능합니다.
 */
public class SendJsonChingutalkButton {
    public static void main(String[] args) {
        JsonObject params = new JsonObject();
        JsonArray messages = new JsonArray();

        // 웹링크 버튼
        JsonObject btn1 = new JsonObject();
        btn1.addProperty("buttonType", "WL");
        btn1.addProperty("buttonName", "버튼 이름");
        btn1.addProperty("linkMo", "https://m.example.com");
        btn1.addProperty("linkPc", "https://example.com");

        // 앱링크 버튼
        JsonObject btn2 = new JsonObject();
        btn2.addProperty("buttonType", "AL");
        btn2.addProperty("buttonName", "앱 실행 버튼");
        btn2.addProperty("linkAnd", "examplescheme://"); // 안드로이드
        btn2.addProperty("linkIos", "examplescheme://"); // IOS

        // 챗봇에게 키워드를 전달합니다. 버튼이름의 키워드가 그대로 전달됩니다.
        JsonObject btn3 = new JsonObject();
        btn3.addProperty("buttonType", "BK");
        btn3.addProperty("buttonName", "봇키워드");

        // 버튼을 누르면 상담사에게 메시지가 그대로 전달됩니다.
        JsonObject btn4 = new JsonObject();
        btn4.addProperty("buttonType", "MD");
        btn4.addProperty("buttonName", "상담요청하기");

        JsonArray  buttons = new JsonArray();
        buttons.add(btn1);
        buttons.add(btn2);
        buttons.add(btn3);
        buttons.add(btn4);

        JsonObject kakaoOptions1 = new JsonObject();
        kakaoOptions1.addProperty("pfId", "KA01PF200323182344986oTFz9CIabcx");
        kakaoOptions1.add("buttons", buttons);

        JsonObject msg = new JsonObject();
        msg.addProperty("to", "01000010001");
        msg.addProperty("from", "029302266");
        msg.addProperty("text", "카카오톡채널 친구로 추가되어 있어야 친구톡 발송이 가능하며 템플릿 등록없이 버튼을 포함하여 자유롭게 메시지 전송이 가능합니다.");
        msg.add("kakaoOptions", kakaoOptions1);
        messages.add(msg);

        JsonObject msg2 = new JsonObject();
        msg2.addProperty("to", "01000010002");
        msg2.addProperty("from", "029302266");
        msg2.addProperty("text", "카카오톡채널 친구로 추가되어 있어야 친구톡 발송이 가능하며 템플릿 등록없이 버튼을 포함하여 자유롭게 메시지 전송이 가능합니다.");
        msg2.add("kakaoOptions", kakaoOptions1);
        messages.add(msg2);

        // 수신번호를 Array 형식으로 입력하여 동일한 내용으로 여러명에게 발송할 수 있습니다.
        JsonObject msg3 = new JsonObject();
        JsonArray toList = new JsonArray();
        toList.add("01000010003");
        toList.add("01000010004");
        msg3.add("to", toList);
        msg3.addProperty("from", "029302266");
        msg3.addProperty("text", "카카오톡채널 친구로 추가되어 있어야 친구톡 발송이 가능하며 템플릿 등록없이 버튼을 포함하여 자유롭게 메시지 전송이 가능합니다.");
        msg3.add("kakaoOptions", kakaoOptions1);
        messages.add(msg3);


        // ... 최대 1만건까지 추가 가능

        params.add("messages", messages);

        Call<GroupModel> api = APIInit.getAPI().sendMessages(APIInit.getHeaders(), params);
        api.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<GroupModel> call, Response<GroupModel> response) {
                // 성공 시 200이 출력됩니다.
                if (response.isSuccessful()) {
                    System.out.println("statusCode : " + response.code());
                    GroupModel body = response.body();
                    System.out.println("groupId : " + body.getGroupId());
                    System.out.println("status: " + body.getStatus());
                    System.out.println("count: " + body.getCount().toString());
                } else {
                    try {
                        System.out.println(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<GroupModel> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
