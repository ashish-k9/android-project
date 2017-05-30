package testuser.test.com.testuser.service;

import android.app.Activity;
import android.app.ProgressDialog;
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

    private static final String TAG =  "Service call" ;
    private ProgressDialog pDialog;
    Activity  activity;
    int RESULTKEY,POSITION;
    ResponseHandler responseHandler;
    public ServiceAsyncTask(Activity activity,int resultKey, int position,ResponseHandler responseHandler){
    this.activity = activity;
    this.POSITION = position;
    this.RESULTKEY = resultKey;
    this.responseHandler = responseHandler;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Showing progress dialog
        pDialog = new ProgressDialog(activity);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

    }
    String url = "";
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
                jsonObj.put("results",jsonArray);

                return  jsonObj;

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
        // Dismiss the progress dialog
        if (pDialog.isShowing())
            pDialog.dismiss();
        if(result!= null){
            responseHandler.onSucess(RESULTKEY,result,url);
        }else{
            responseHandler.onFailure(RESULTKEY,POSITION);
        }

    }

}
