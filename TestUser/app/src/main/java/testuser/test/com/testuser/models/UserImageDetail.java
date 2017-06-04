package testuser.test.com.testuser.models;

import org.json.JSONArray;
import org.json.JSONObject;

import testuser.test.com.testuser.utils.JSONUtil;

/**
 * Created by  on 26/05/17.
 * Copyright © 2017 TestUser. All rights reserved.
 * <p>
 * [
 * {
 * "albumId": 1,
 * "id": 1,
 * "title": "accusamus beatae ad facilis cum similique qui sunt",
 * "url": "http://placehold.it/600/92c952",
 * "thumbnailUrl": "http://placehold.it/150/92c952"
 * }
 * ]
 */


public class UserImageDetail {
    int albumId, id;
    String title, url, thumbnailUrl;

    public static UserImageDetail getUSerImageFromJsonList(JSONObject jsonObject) {

        if (jsonObject != null) {
            JSONArray jsonArray = JSONUtil.getJsonArray(jsonObject, "results");
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject object = JSONUtil.getJsonObjectFromArray(jsonArray, i);

                UserImageDetail userImageDetail = UserImageDetail.getUserFromJsonitem(object);

                if (userImageDetail != null)
                    return userImageDetail;


            }
        }

        return null;
    }

    private static UserImageDetail getUserFromJsonitem(JSONObject jsonObject) {
        if (jsonObject != null) {

            UserImageDetail userImageDetail = new UserImageDetail();
            userImageDetail.setAlbumId(JSONUtil.readInt(jsonObject, "albumId"));
            userImageDetail.setId(JSONUtil.readInt(jsonObject, "id"));
            userImageDetail.setThumbnailUrl(JSONUtil.readString(jsonObject, "thumbnailUrl"));
            userImageDetail.setTitle(JSONUtil.readString(jsonObject, "title"));
            userImageDetail.setUrl(JSONUtil.readString(jsonObject, "url"));
            return userImageDetail;
        }

        return null;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
