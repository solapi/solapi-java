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
 * 한번 요청으로 1만건까지 알림톡 발송이 가능합니다.
 */
public class SendJsonAlimtalk {
    public static void main(String[] args) {
        JsonObject params = new JsonObject();
        JsonArray messages = new JsonArray();



        JsonObject kakaoOptions1 = new JsonObject();
        kakaoOptions1.addProperty("pfId", "KA01PF200323182344986oTFz9CIabcx");
        kakaoOptions1.addProperty("templateId", "KA01TP200323182345741y9yF20aabcx");

        JsonObject msg = new JsonObject();
        msg.addProperty("to", "01000010001");
        msg.addProperty("from", "029302266");
        msg.addProperty("text", "홍길동님 가입을 환영합니다.");
        msg.add("kakaoOptions", kakaoOptions1);
        messages.add(msg);

        JsonObject msg2 = new JsonObject();
        msg2.addProperty("to", "01000010002");
        msg2.addProperty("from", "029302266");
        msg2.addProperty("text", "이길동님 가입을 환영합니다.");
        msg2.add("kakaoOptions", kakaoOptions1);
        messages.add(msg2);

        // 수신번호를 Array 형식으로 입력하여 동일한 내용으로 여러명에게 발송할 수 있습니다.
        JsonObject msg3 = new JsonObject();
        JsonArray toList = new JsonArray();
        toList.add("01000010003");
        toList.add("01000010004");
        msg3.add("to", toList);
        msg3.addProperty("from", "029302266");
        msg3.addProperty("text", "모두님 가입을 환영합니다.");
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

                    JsonObject count = body.getCount();
                    System.out.println("발송건수");
                    System.out.println(" - Total: " + count.get("total"));
                    System.out.println(" - 발송건수(전체): " + count.get("sentTotal"));
                    System.out.println(" - 발송실패: " + count.get("sentFailed"));
                    System.out.println(" - 발송성공: " + count.get("sentSuccess"));
                    System.out.println(" - 발송중: " + count.get("sentPending"));
                    System.out.println(" - 대체발송: " + count.get("sentReplacement"));
                    System.out.println(" - 환급: " + count.get("refund"));
                    System.out.println(" - 접수실패: " + count.get("registeredFailed"));
                    System.out.println(" - 접수성공: " + count.get("registeredSuccess"));

                    JsonObject countForCharge = body.getCountForCharge();
                    System.out.println("타입별 건수");
                    System.out.println(" - SMS: " + countForCharge.get("sms")); // 국가: 건수 형식
                    System.out.println(" - LMS: " + countForCharge.get("lms"));
                    System.out.println(" - MMS: " + countForCharge.get("mms"));
                    System.out.println(" - ATA: " + countForCharge.get("ata"));
                    System.out.println(" - CTA: " + countForCharge.get("cta"));
                    System.out.println(" - NSA: " + countForCharge.get("nsa"));

                    JsonObject balance = body.getBalance();
                    System.out.println("차감 잔액");
                    System.out.println(" - 예상 차감액: " + balance.get("requested"));
                    System.out.println(" - 대체발송: " + balance.get("replacement"));
                    System.out.println(" - 환급: " + balance.get("refund"));
                    System.out.println(" - 합계: " + balance.get("sum"));

                    JsonObject point = body.getPoint();
                    System.out.println("차감 포인트");
                    System.out.println(" - 예상 차감 포인트: " + point.get("requested"));
                    System.out.println(" - 대체발송: " + point.get("replacement"));
                    System.out.println(" - 환급: " + point.get("refund"));
                    System.out.println(" - 합계: " + point.get("sum"));
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
