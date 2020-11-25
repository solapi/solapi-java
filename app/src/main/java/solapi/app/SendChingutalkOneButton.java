package solapi.app;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.io.IOException;
import model.request.Message;
import model.request.KakaoOptions;
import model.request.KakaoButtons;
import model.request.KakaoButtonItem;
import model.response.MessageModel;
import utilities.APIInit;

public class SendChingutalkOneButton {
    public static void main(String[] args) {
        KakaoButtons buttons = new KakaoButtons();
        buttons.add(new KakaoButtonItem("[버튼이름]", "[버튼 종류(AL: 앱링크, WL: 웹링크, DS: 배송조회, BK: 키워드, MD: 전달)]", "[모바일 링크]", "[PC 링크]", "[안드로이드 앱링크]", "[IOS 앱링크]"));
        // buttons.add(new KakaoButtonItem("웹링크", "WL", "https://m.example.com", "https://example.com", null, null));
        // buttons.add(new KakaoButtonItem("앱실행", "AL", null, null, "android_scheme://", "ios_scheme://"));
        // buttons.add(new KakaoButtonItem("배송조회", "DS", null, null, null, null)); // 알림톡에서만 DS 타입 발송 가능
        // buttons.add(new KakaoButtonItem("봇키워드", "BK", null, null, null, null)); // 봇키워드를 챗봇에 전달
        // buttons.add(new KakaoButtonItem("메시지 전달", "MD", null, null, null, null)); // 상담원에게 메시지 전달

        // KakaoOptions kakaoOptions = new KakaoOptions("[pfID를 입력하세요]", "[친구톡은 템플릿ID값을 null 입력]", [문자대체발송 여부(Boolean)], "[이미지아이디(친구톡)]", [버튼정보(KakaoButtons)]);
        KakaoOptions kakaoOptions = new KakaoOptions("[pfID를 입력하세요]", null, true, null, buttons);

        Message message = new Message("[수신번호를 입력하세요]", "[발신번호를 입력하세요]", "[전송할 메시지를 입력하세요]", kakaoOptions);
        Call<MessageModel> api = APIInit.getAPI().sendMessage(APIInit.getHeaders(), message);
        api.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                // 성공 시 200이 출력됩니다.
                if (response.isSuccessful()) {
                    System.out.println("statusCode : " + response.code());
                    MessageModel body = response.body();
                    System.out.println("groupId : " + body.getGroupId());
                    System.out.println("messageId : " + body.getMessageId());
                    System.out.println("to : " + body.getTo());
                    System.out.println("from : " + body.getFrom());
                    System.out.println("type : " + body.getType());
                    System.out.println("statusCode : " + body.getStatusCode());
                    System.out.println("statusMessage : " + body.getStatusMessage());
                    System.out.println("customFields : " + body.getCustomFields());
                } else {
                    try {
                        System.out.println(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MessageModel> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
