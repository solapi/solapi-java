package solapi.app;

import java.io.IOException;
import java.util.Map;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import model.response.Balance;
import utilities.APIInit;

public class GetBalance {
    public static void main(String[] args) {
        try {
            Call<Balance> api = APIInit.getAPI().getBalance(APIInit.getHeaders());
            Response<Balance> response = api.execute();

            // 성공 시 200이 출력됩니다.
            System.out.println("statusCode : " + response.code());
            if (response.isSuccessful()) {
                Balance price = response.body();
                System.out.println("잔액: " + price.getBalance());
                System.out.println("포인트:" + price.getPoint());
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
