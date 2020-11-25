package model.response;

import java.util.ArrayList;

public class GetImageListModel {
    private String startKey = null;
    private String nextKey = null;
    private ArrayList<ImageListItem> fileList;

    public String getStartKey() {
        return startKey;
    }

    public String getNextKey() {
        return nextKey;
    }

    public ArrayList<ImageListItem> getImageList() {
        return fileList;
    }
}
