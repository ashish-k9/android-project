package testuser.test.com.testuser.service;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import testuser.test.com.testuser.utils.ResponseHandler;

/**
 * Created by  on 26/05/17.
 * Copyright © 2017 TestUser. All rights reserved.
 */


public class ServiceAsyncTask extends AsyncTask<String, Void, JSONObject> {

    private static final String TAG = "Service call";
    int RESULTKEY, POSITION;
    ResponseHandler responseHandler;
    String url = "";

    public ServiceAsyncTask(int resultKey, int position, ResponseHandler responseHandler) {
        this.POSITION = position;
        this.RESULTKEY = resultKey;
        this.responseHandler = responseHandler;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected JSONObject doInBackground(String... params) {
        HttpHandler sh = new HttpHandler();
        url = params[0];
        // Making a request to url and getting response
        String jsonStr = sh.makeServiceCall(url);

        Log.e(TAG, "Response from url: " + jsonStr);

        if (jsonStr != null) {
            try {
                JSONArray jsonArray = new JSONArray(jsonStr);
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("results", jsonArray);

                return jsonObj;

            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());


            }
        } else {
            Log.e(TAG, "Couldn't get json from server.");


        }

        return null;
    }

    @Override
    protected void onPostExecute(JSONObject result) {
        super.onPostExecute(result);
        if (result != null) {
            responseHandler.onSuccess(RESULTKEY, result, url);
        } else {
            responseHandler.onFailure(RESULTKEY, POSITION);
        }

    }

}
