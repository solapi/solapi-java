package solapi.app;

import java.io.IOException;
import java.util.Map;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import model.response.UnitPrice;
import utilities.APIInit;

public class GetUnitPrice {
    public static void main(String[] args) {
        Call<UnitPrice> api = APIInit.getAPI().getUnitPrice(APIInit.getHeaders());
        api.enqueue(new Callback<UnitPrice>() {
            @Override
            public void onResponse(Call<UnitPrice> call, Response<UnitPrice> response) {
                // 성공 시 200이 출력됩니다.
                System.out.println("statusCode : " + response.code());
                if (response.isSuccessful()) {
                    UnitPrice price = response.body();
                    System.out.println("SMS: " + price.getSMS());
                    System.out.println("LMS: " + price.getLMS());
                    System.out.println("MMS: " + price.getMMS());
                    System.out.println("ATA: " + price.getATA());
                    System.out.println("CTA: " + price.getCTA());
                    System.out.println("CTI: " + price.getCTI());
                    System.out.println("NSA: " + price.getNSA());
                } else {
                    try {
                        System.out.println(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<UnitPrice> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
