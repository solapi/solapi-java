package solapi.app;

import com.google.gson.Gson;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import model.response.GetImageListModel;
import model.response.ImageListItem;
import utilities.APIInit;

public class GetImageList {
    public static void main(String[] args) {
        Gson gson = new Gson();
        Call<GetImageListModel> api = APIInit.getImageAPI().getImageList(APIInit.getHeaders());
        api.enqueue(new Callback<GetImageListModel>() {
            @Override
            public void onResponse(Call<GetImageListModel> call, Response<GetImageListModel> response) {
                System.out.println(response.code());
                GetImageListModel body = response.body();
                System.out.println("startKey: " + body.getStartKey());
                System.out.println("nextKey: " + body.getNextKey());
                for (ImageListItem item: body.getImageList()) {
                    System.out.println("type : " + item.getType());
                    System.out.println("imageId : " + item.getImageId());
                    System.out.println("accountId : " + item.getAccountId());
                    System.out.println("fileName : " + item.getFileName());
                    System.out.println("originalFileName : " + item.getOriginalName());
                    System.out.println("fileSize : " + item.getFileSize());
                    System.out.println("dateCreated : " + item.getDateCreated());
                    System.out.println("dateUpdated : " + item.getDateUpdated());
                    System.out.println();
                }
            }

            @Override
            public void onFailure(Call<GetImageListModel> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
