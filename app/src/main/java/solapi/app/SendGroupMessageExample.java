package solapi.app;

import java.io.IOException;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import okhttp3.ResponseBody;
import model.request.Message;
import model.request.MessageList;
import model.response.AddMessageListModel;
import model.response.MessageModel;
import model.response.GroupModel;
import utilities.APIInit;

public class SendGroupMessageExample {
    static String groupId = null;

    public static void main(String[] args) {

        // Step 1. 그룹 생성
        Call<GroupModel> api = APIInit.getAPI().createGroup(APIInit.getHeaders());
        api.enqueue(new Callback<GroupModel>() {
            @Override
            public void onResponse(Call<GroupModel> call, Response<GroupModel> response) {
                GroupModel body = response.body();
                groupId = body.getGroupId();
                System.out.println("groupId : " + groupId);

                // Step 2. 메시지 추가
                MessageList messages = new MessageList();
                for( int i = 0; i < 1; i++ ) {
                    Message msg = new Message("수신번호 #1", "발신번호", "전송할 문자를 입력하세요 #1");

                    // 커스텀 필드와 함께 메시지 추가
                    // JsonObject customFields = new JsonObject();
                    // customFields.addProperty("CustomField", "CustomValue");
                    // msg.setCustomFields(customFields);
                    // messages.add(msg);

                    messages.add(msg);
                }
                Call<AddMessageListModel> api2 = APIInit.getAPI().addGroupMessage(APIInit.getHeaders(), groupId, messages);
                api2.enqueue(new Callback<AddMessageListModel>() {
                    @Override
                    public void onResponse(Call<AddMessageListModel> call, Response<AddMessageListModel> response) {
                        AddMessageListModel body = response.body();
                        try {
                            System.out.println("errorCount : " + body.getErrorCount());
                            for (MessageModel message : body.getResultList()) {
                                System.out.println("groupId : " + message.getGroupId());
                                System.out.println("messageId : " + message.getMessageId());

                                // Step 3. 그룹메시지 발송
                                Call<ResponseBody> api3 = APIInit.getAPI().sendGroupMessage(APIInit.getHeaders(), groupId);
                                api3.enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        // 성공시 200이 출력됩니다.
                                        System.out.println(response.code());
                                        try {
                                            // 성공 시 "Success" 가 출력됩니다.
                                            System.out.println(response.body().string());
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                                        throwable.printStackTrace();
                                    }
                                });
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

            @Override
            public void onFailure(Call<GroupModel> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
