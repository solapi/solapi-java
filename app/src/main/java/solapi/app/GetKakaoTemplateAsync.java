package solapi.app;

import java.io.IOException;
import java.util.Map;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import model.response.KakaoTemplate;
import utilities.APIInit;

public class GetKakaoTemplateAsync {
    public static void main(String[] args) {
        Call<KakaoTemplate> api = APIInit.getAPI().getKakaoTemplate(APIInit.getHeaders(), "[Template ID 값을 입력하세요]");
        api.enqueue(new Callback<KakaoTemplate>() {
            @Override
            public void onResponse(Call<KakaoTemplate> call, Response<KakaoTemplate> response) {
                // 성공 시 200이 출력됩니다.
                System.out.println("statusCode : " + response.code());
                if (response.isSuccessful()) {
                    KakaoTemplate template = response.body();
                    System.out.println("Template Id: " + template.getTemplateId());
                    System.out.println("Template Name: " + template.getName());
                    System.out.println("PfID: " + template.getPfId());
                    System.out.println("Content: " + template.getContent());
                    System.out.println("Date Created: " + template.getDateCreated());
                    System.out.println("Date Updated: " + template.getDateUpdated());
                } else {
                    try {
                        System.out.println(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<KakaoTemplate> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
