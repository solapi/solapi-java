package solapi.app;

import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import model.response.KakaotalkChannel;
import utilities.APIInit;

public class GetKakaotalkChannel {
    public static void main(String[] args) {
        Call<KakaotalkChannel> api = APIInit.getAPI().getKakaotalkChannel(APIInit.getHeaders(), "[PFID 값을 입력하세요]");
        getKakaotalkChannelInfo(api);
    }

    static void getKakaotalkChannelInfo(Call<KakaotalkChannel> api) {
        api.enqueue(new Callback<KakaotalkChannel>() {
            @Override
            public void onResponse(Call<KakaotalkChannel> call, Response<KakaotalkChannel> response) {
                // 성공 시 200이 출력됩니다.
                System.out.println("statusCode : " + response.code());
                if (response.isSuccessful()) {
                    KakaotalkChannel body = response.body();
                    System.out.println("PfId: " + body.getPfId());
                    System.out.println("dateCreated : " + body.getDateCreated());
                    System.out.println("dateUpdated : " + body.getDateUpdated());
                } else {
                    try {
                        System.out.println(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<KakaotalkChannel> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
