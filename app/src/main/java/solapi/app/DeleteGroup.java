package solapi.app;

import retrofit2.Call;
import model.response.GroupModel;
import utilities.APIInit;

public class DeleteGroup {
    public static void main(String[] args) {
        Call<GroupModel> api = APIInit.getAPI().deleteGroupInfo(APIInit.getHeaders(), "[Group ID를 입력하세요]");
        GetMessageGroupInfo.getGroupInfo(api);
    }
}
