package testuser.test.com.testuser.utils;

import org.json.JSONObject;

/**
 * Created by Ashish on 26/05/17.
 * Copyright © 2017 TestUser. All rights reserved.
 */

public interface ResponseHandler {
    int RESULT_KEY_FOR_GET_USER_PICTURE = 101;
    int RESULT_KEY_FOR_GET_USER_DETAILS = 102;
    String BASE_URL = "https://jsonplaceholder.typicode.com/";

    void onSuccess(int resultKey, JSONObject jsonObject, String url);

    void onFailure(int resultKey, int position);
}
