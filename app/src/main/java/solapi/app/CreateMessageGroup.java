package solapi.app;

import retrofit2.Call;
import model.response.GroupModel;
import utilities.APIInit;

public class CreateMessageGroup {
    public static void main(String[] args) {
        Call<GroupModel> api = APIInit.getAPI().createGroup(APIInit.getHeaders());
        GetMessageGroupInfo.getGroupInfo(api);
    }
}
