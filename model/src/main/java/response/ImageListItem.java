package model.response;

public class ImageListItem {
    String type;
    String originalName;
    int width = 0;
    int height = 0;
    int fileSize = 0;
    String fileId;
    String accountId;
    String name;
    String dateCreated;
    String dateUpdated;

    public String getType() {
        return type;
    }

    public String getOriginalName() {
        return originalName;
    }

    public String getFileName() {
        return name;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getImageId() {
        return fileId;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }
}
