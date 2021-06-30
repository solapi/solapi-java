package solapi.app.message.list;

import com.google.gson.JsonObject;
import java.io.IOException;
import com.google.gson.Gson;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import model.response.AddMessageModel;
import model.response.GetMessageListModel;
import model.response.LogModel;
import utilities.APIInit;

public class SearchGroupId {
    public static void main(String[] args) {
        try {
            Call<GetMessageListModel> page1 = APIInit.getAPI().getMessageByGID(APIInit.getHeaders(), "G4V20210628182500XSCSGEC1ODVW3W8");
            Response<GetMessageListModel> response = page1.execute();

            String nextKey = null;
            if (response.isSuccessful()) {
                System.out.println(response.code());
                GetMessageListModel body = response.body();
                System.out.println("offset : " + body.getOffset());
                System.out.println("limit : " + body.getLimit());
                System.out.println("nextKey: " + body.getNextKey());
                nextKey = body.getNextKey();
                Pagination.printMessage(body.getMessageList());
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

    public static void printMessage(JsonObject obj) {
        Gson gson = new Gson();
        for (String key : obj.keySet()) {
            AddMessageModel messageRes = gson.fromJson(obj.get(key), AddMessageModel.class);
            System.out.println("_id : " + messageRes.getMessageId());
            System.out.println("kakaoOptions : " + messageRes.getMessageId());
            System.out.println("type : " + messageRes.getType());
            System.out.println("country : " + messageRes.getCountry());
            System.out.println("subject : " + messageRes.getSubject());
            System.out.println("imageId : " + messageRes.getImageId());
            System.out.println("dateProcessed : " + messageRes.getDateProcessed());
            System.out.println("dateReported : " + messageRes.getDateReported());
            System.out.println("dateReceived : " + messageRes.getDateReceived());
            System.out.println("statusCode : " + messageRes.getStatusCode());
            System.out.println("networkCode : " + messageRes.getNetworkCode());
            for (LogModel log : messageRes.getLog()) {
                System.out.println("log CreateAt: " + log.getCreateAt());
                System.out.println("log message: " + log.getMessage());
            }
            System.out.println("replacement : " + messageRes.getReplacement());
            System.out.println("autoTypeDetect : " + messageRes.getAutoTypeDetect());
            System.out.println("messageId : " + messageRes.getMessageId());
            System.out.println("groupId : " + messageRes.getGroupId());
            System.out.println("accountId : " + messageRes.getAccountId());
            System.out.println("text : " + messageRes.getText());
            System.out.println("from : " + messageRes.getFrom());
            System.out.println("to : " + messageRes.getTo());
            System.out.println("customFields : " + messageRes.getCustomFields());
            System.out.println("dateCreated : " + messageRes.getDateCreated());
            System.out.println("dateUpdated : " + messageRes.getDateUpdated());
            System.out.println("reason : " + messageRes.getReason());
            System.out.println("networkName : " + messageRes.getNetworkName() + "\n");
        }
    }
}
