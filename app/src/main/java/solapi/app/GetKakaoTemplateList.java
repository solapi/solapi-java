package solapi.app;

import java.io.IOException;
import java.util.Map;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import model.response.KakaoTemplateList;
import model.response.KakaoTemplate;
import utilities.APIInit;

public class GetKakaoTemplateList {
    public static void main(String[] args) {
        // 등록된 전체 템플릿 조회
        Call<KakaoTemplateList> api = APIInit.getAPI().getKakaoTemplateList(APIInit.getHeaders());
        // 특정 PFID로 조회
        // Call<KakaoTemplateList> api = APIInit.getAPI().getKakaoTemplateList(APIInit.getHeaders(), "[PFID 값을 입력하세요]");
        // 상태값으로 조회
        // Call<KakaoTemplateList> api = APIInit.getAPI().getKakaoTemplateList(APIInit.getHeaders(), null, "상태값:APPROVED|PENDING");
        // 승인된 템플릿만 조회
        // Call<KakaoTemplateList> api = APIInit.getAPI().getKakaoTemplateList(APIInit.getHeaders(), null, "APPROVED");

        api.enqueue(new Callback<KakaoTemplateList>() {
            @Override
            public void onResponse(Call<KakaoTemplateList> call, Response<KakaoTemplateList> response) {
                // 성공 시 200이 출력됩니다.
                System.out.println("statusCode : " + response.code());
                if (response.isSuccessful()) {

                    KakaoTemplateList body = response.body();
                    ArrayList<KakaoTemplate> templateList = body.getTemplateList();
                    for (int i = 0; i < templateList.size(); i++) { 		      
                        KakaoTemplate template = (KakaoTemplate)templateList.get(i);
                        System.out.println("Template Id: " + template.getTemplateId());
                        System.out.println("Template Name: " + template.getName());
                        System.out.println("PfID: " + template.getPfId());
                        System.out.println("Content: " + template.getContent());
                        System.out.println("Date Created: " + template.getDateCreated());
                        System.out.println("Date Updated: " + template.getDateUpdated());
                    }
                } else {
                    try {
                        System.out.println(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<KakaoTemplateList> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
