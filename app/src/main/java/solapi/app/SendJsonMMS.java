package solapi.app;

import org.apache.commons.codec.binary.Base64;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import model.request.ImageModel;
import model.response.GroupModel;
import model.response.ImageResult;
import utilities.APIInit;

/**
 * 한번 요청으로 1만건까지 MMS 발송이 가능합니다.
 * 한번 업로드된 이미지는 계속 사용 가능합니다.(약 7일 후 삭제)
 * subject 입력이 없는 경우 자동으로 내용 앞 부분을 MMS 제목으로 사용합니다.
 */
public class SendJsonMMS {
    public static void main(String[] args) {
        File imgFile = new File("../images/testImage.jpg");
        long length = imgFile.length();
        byte[] imageByte = new byte[(int) length];
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(imgFile);
            fis.read(imageByte);
        } catch (Exception e) {
            // 혹시 FileNotFoundException이 뜬다면 아래의 코드로 경로를 확인해보고 맞추시면 됩니다.
            // System.out.println(new File("../images/testImage.jpg").getAbsoluteFile());
            e.printStackTrace();
        }
        String result = new String(Base64.encodeBase64(imageByte));
        Call<ImageResult> api = APIInit.getImageAPI().createImage(APIInit.getHeaders(), new ImageModel(result));
        api.enqueue(new Callback<ImageResult>() {
            @Override
            public void onResponse(Call<ImageResult> call, Response<ImageResult> response) {
                System.out.println("image Status Code: " + response.code());
                // 성공시에 M4V로 시작하는 imageId가 넘어옵니다.
                if (response.isSuccessful()) {
                    String imageId = response.body().getImageId();
                    System.out.println("imageId: " + imageId + "\n");

                    JsonObject params = new JsonObject();
                    JsonArray messages = new JsonArray();

                    JsonObject msg = new JsonObject();
                    msg.addProperty("to", "01000010001");
                    msg.addProperty("from", "029302266");
                    msg.addProperty("subject", "MMS 제목");
                    msg.addProperty("imageId", imageId); // 이미지 아이디 입력
                    msg.addProperty("text", "이미지 아이디가 입력되면 MMS로 발송됩니다.");
                    messages.add(msg);

                    JsonObject msg2 = new JsonObject();
                    msg2.addProperty("to", "01000010002");
                    msg2.addProperty("from", "029302266");
                    msg2.addProperty("subject", "MMS 제목");
                    msg2.addProperty("imageId", imageId); // 이미지 아이디 입력
                    msg2.addProperty("text", "동일한 이미지 아이디가 입력되면 동일한 이미지가 MMS로 발송됩니다.");
                    messages.add(msg2);

                    // 수신번호를 Array 형식으로 입력하여 동일한 내용으로 여러명에게 발송할 수 있습니다.
                    JsonObject msg3 = new JsonObject();
                    JsonArray toList = new JsonArray();
                    toList.add("01000010003");
                    toList.add("01000010004");
                    msg3.add("to", toList);
                    msg3.addProperty("from", "029302266");
                    msg3.addProperty("subject", "MMS 제목");
                    msg3.addProperty("imageId", imageId); // 이미지 아이디 입력
                    msg3.addProperty("text", "동일한 이미지 아이디가 입력되면 동일한 이미지가 MMS로 발송됩니다.");
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

            @Override
            public void onFailure(Call<ImageResult> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
