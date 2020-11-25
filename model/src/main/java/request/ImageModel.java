package model.request;

public class ImageModel {
    public ImageModel(String base64encoded) {
        file = base64encoded;
    }
    String file;
    String type = "MMS";
}
