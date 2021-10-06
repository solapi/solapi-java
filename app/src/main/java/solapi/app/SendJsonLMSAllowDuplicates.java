package solapi.app;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import model.response.GroupModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utilities.APIInit;

import java.io.IOException;

/**
 * 한번 요청으로 1만건까지 LMS 발송이 가능합니다.
 * subject 입력이 없는 경우 자동으로 내용 앞 부분을 LMS 제목으로 사용합니다.
 */
public class SendJsonLMSAllowDuplicates {
    public static void main(String[] args) {
        JsonObject params = new JsonObject();
        JsonArray messages = new JsonArray();
        JsonPrimitive allowDuplicates = new JsonPrimitive(true);

        // 수신번호를 Array 형식으로 입력하여 동일한 내용으로 여러명에게 발송할 수 있습니다.
        JsonObject msg = new JsonObject();
        JsonArray toList = new JsonArray();
        toList.add("01000010005");
        toList.add("01000010005");

        msg.add("to", toList);
        msg.addProperty("from", "029302266");
        msg.addProperty("text", "한글 45자123, 영자 90자 이상 입력되면 자동으로 LMS타입의 문자메시자가 발송됩니다. 0123456789 ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        messages.add(msg);

        // ... 최대 1만건까지 추가 가능

        params.add("messages", messages);

        // 중복 수신번호 허용할 경우에 추가
        params.add("allowDuplicates", allowDuplicates);

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
