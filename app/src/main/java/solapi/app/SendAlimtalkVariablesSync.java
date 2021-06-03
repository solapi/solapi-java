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
 * 알림톡 템플릿의 변수값만 넘겨 발송하는 방식의 예제입니다.
 * 한번 요청으로 1만건까지 알림톡 발송이 가능합니다.
 */
public class SendAlimtalkVariablesSync {
    public static void main(String[] args) {
        JsonObject params = new JsonObject();
        JsonArray messages = new JsonArray();

        // 아래 형식으로 등록된 알림톡 템플릿으로 가정합니다.
        // 템플릿 내용: #{사원명}님은 #{회사명}에 #{입사일}에 입사하셨습니다.
        // 템플릿 버튼1 이름: 사원정보 확인
        // 템플릿 버튼1 모바일링크 URL: https://#{link}

        // variables에 본문내용과 버튼 전체에 걸쳐 변수, 값 형식으로 채워넣습니다.
        JsonObject variables = new JsonObject();
        variables.addProperty("#{회사명}", "누리고");
        variables.addProperty("#{사원명}", "홍길동");
        variables.addProperty("#{입사일}", "2021년 6월 3일");
        variables.addProperty("#{link}", "www.nurigo.net/사원정보확인/사원ID");

        JsonObject kakaoOptions = new JsonObject();
        kakaoOptions.addProperty("pfId", "KA01PF200323182344986oTFz9CIabcx");
        kakaoOptions.addProperty("templateId", "KA01TP200323182345741y9yF20aabcx");
        kakaoOptions.add("variables", variables);

        JsonObject msg = new JsonObject();
        msg.addProperty("to", "01000010001");
        msg.addProperty("from", "029302266");
        msg.add("kakaoOptions", kakaoOptions);
        messages.add(msg);

        // ... 최대 1만건까지 추가 가능

        params.add("messages", messages);

        try {
            Call<GroupModel> api = APIInit.getAPI().sendMessages(APIInit.getHeaders(), params);
            Response<GroupModel> response = api.execute();

            // 성공 시 200이 출력됩니다.
            System.out.println("statusCode : " + response.code());
            if (response.isSuccessful()) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
