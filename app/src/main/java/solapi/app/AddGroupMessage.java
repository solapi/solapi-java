package solapi.app;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.ArrayList;
import model.request.Message;
import model.request.MessageList;
import model.request.KakaoButtons;
import model.request.KakaoButtonItem;
import model.request.KakaoOptions;
import model.response.AddMessageListModel;
import model.response.MessageModel;
import utilities.APIInit;

public class AddGroupMessage {
    public static void main(String[] args) {
        MessageList messages = new MessageList();

        // #1 문자
        messages.add(new Message("수신번호 #1", "발신번호", "전송할 문자를 입력하세요 #1"));
        // #2 문자
        messages.add(new Message("수신번호 #2", "발신번호", "전송할 문자를 입력하세요 #2"));

        // #3 알림톡
        // 순서 및 내용을 등록된 템플릿 버튼 구성 그대로 입력해야 합니다.
        // 변수는 치환된 값으로 입력해 줍니다. 예) https://${url} ---> https://example.com
        KakaoButtons buttons = new KakaoButtons();
        // buttons.add(new KakaoButtonItem("버튼이름", "버튼 종류(AL: 앱링크, WL: 웹링크, DS: 배송조회, BK: 키워드, MD: 전달)", "모바일 링크", "PC 링크", "안드로이드 앱링크", "IOS 앱링크"));
        // buttons.add(new KakaoButtonItem("앱실행", "AL", null, null, "android_scheme://", "ios_scheme://"));
        // buttons.add(new KakaoButtonItem("배송조회", "DS", null, null, null, null)); // 알림톡에서만 DS 타입 발송 가능
        // buttons.add(new KakaoButtonItem("봇키워드", "BK", null, null, null, null)); // 봇키워드를 챗봇에 전달
        // buttons.add(new KakaoButtonItem("메시지 전달", "MD", null, null, null, null)); // 상담원에게 메시지 전달
        buttons.add(new KakaoButtonItem("웹링크", "WL", "https://m.example.com", "https://example.com", null, null));

        // 알림톡은 반드시 pfId와 templateId값을 입력해야 합니다.
        KakaoOptions kakaoOptions = new KakaoOptions("[pfID를 입력하세요]", "[템플릿ID를 입력하세요]", true, null, buttons);

        // 전송할 메시지는 변수를 치환하여 입력해 줍니다.  예) #{이름}님 가입을 환영합니다.  ---> 홍길동님 가입을 환영합니다.
        // 변수 이외의 내용은 100% 일치해야 하며, 단 줄내림은 마음껏 하실 수 있습니다. 예) #{이름}님 가입을 환영합니다. ----> 홍길동님\n\n가입을\n\n환영합니다.
        messages.add(new Message("[수신번호를 입력하세요]", "[발신번호를 입력하세요]", "[전송할 메시지를 입력하세요]", kakaoOptions));

        Call<AddMessageListModel> api = APIInit.getAPI().addGroupMessage(APIInit.getHeaders(), "Group ID를 입력하세요", messages);

        api.enqueue(new Callback<AddMessageListModel>() {
            @Override
            public void onResponse(Call<AddMessageListModel> call, Response<AddMessageListModel> response) {
                AddMessageListModel body = response.body();
                try {
                    System.out.println("errorCount : " + body.getErrorCount());
                    for (MessageModel message : body.getResultList()) {
                        System.out.println("groupId : " + message.getGroupId());
                        System.out.println("messageId : " + message.getMessageId());
                        System.out.println("to : " + message.getTo());
                        System.out.println("from : " + message.getFrom());
                        System.out.println("type : " + message.getType());
                        System.out.println("statusCode : " + message.getStatusCode());
                        System.out.println("statusMessage : " + message.getStatusMessage());
                        System.out.println("customFields : " + message.getCustomFields());
                        System.out.println("country : " + message.getCountry());
                        System.out.println("accountId : " + message.getAccountId());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<AddMessageListModel> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
