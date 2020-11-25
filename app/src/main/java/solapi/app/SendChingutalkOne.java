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

public class SendChingutalkOne {
    public static void main(String[] args) {
        // 친구톡은 템플릿ID값을 null로 입력합니다.
        // KakaoOptions kakaoOptions = new KakaoOptions("[pfID를 입력하세요]", "[친구톡은 템플릿ID값을 null 입력]", [문자대체발송 여부(Boolean)], "[이미지아이디(친구톡)]", [버튼정보(KakaoButtons)]);
        KakaoOptions kakaoOptions = new KakaoOptions("[pfID를 입력하세요]", null, true, null, null);

        // 카카오톡 채널을 친구로 등록한 가입자에게 어떤 내용이든 발송 가능합니다.
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
