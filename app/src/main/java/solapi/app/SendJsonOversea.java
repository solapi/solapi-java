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
 * 한번 요청으로 1만건까지 해외 SMS 발송이 가능합니다.
 */
public class SendJsonOversea {
    public static void main(String[] args) {
        JsonObject params = new JsonObject();
        JsonArray messages = new JsonArray();

        JsonObject first = new JsonObject();
        first.addProperty("country", "1"); // 미국으로 발송
        first.addProperty("to", "01000010001"); // 미국 현지 전화번호
        first.addProperty("from", "029302266"); // 발신번호 지정 불가, 특별히 지정된 발신번호로 변경되어 발송.
        first.addProperty("text", "한글 45자, 영자 90자 이하로 입력가능.");
        messages.add(first);

        JsonObject second = new JsonObject();
        second.addProperty("country", "81"); // 일본으로 발송
        second.addProperty("to", "01000010002"); // 일본 현지 전화번호
        second.addProperty("from", "029302266"); // 발신번호 지정 불가, 특별히 지정된 발신번호로 변경되어 발송.
        second.addProperty("text", "한글 45자, 영자 90자 이하로 입력가능.");
        messages.add(second);

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
