package testuser.test.com.testuser;


import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import testuser.test.com.testuser.models.Address;
import testuser.test.com.testuser.models.Company;
import testuser.test.com.testuser.models.UserImageDetail;
import testuser.test.com.testuser.models.UserModel;
import testuser.test.com.testuser.service.ServiceAsyncTask;
import testuser.test.com.testuser.utils.ApplicationCache;
import testuser.test.com.testuser.utils.ResponseHandler;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements ResponseHandler {




    public MainActivityFragment() {
    }

    EditText search_editext;

    TextView userNameTv,userEmailTv,userPhoneTv,userAddressTv;
    UserModel userModel;
    ImageView user_image_id;
    boolean isSearchClicked;
    ApplicationCache applicationCache;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        applicationCache = new ApplicationCache(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment_main, container, false);
          search_editext = (EditText) view.findViewById(R.id.search_editext);
        userNameTv = (TextView) view.findViewById(R.id.userNameTv);
        userEmailTv  = (TextView) view.findViewById(R.id.userEmailTv);
        userPhoneTv = (TextView) view.findViewById(R.id.userPhoneTv);
        userAddressTv = (TextView) view.findViewById(R.id.userAddressTv);
        user_image_id = (ImageView) view.findViewById(R.id.user_image_id);

        view.findViewById(R.id.search_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(getActivity());
                getUSerDetailsFromServer(search_editext.getText().toString());
            }
        });

        search_editext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.search_id || id == EditorInfo.IME_NULL) {
                    hideSoftKeyboard(getActivity());
                    getUSerDetailsFromServer(search_editext.getText().toString());
                    return true;
                }
                return false;
            }
        });

        view.findViewById(R.id.locatemap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userModel!=null)
                loadMapwithAddress(userModel.getAddress());
            }
        });

        view.findViewById(R.id.callBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callUser(userModel);
            }
        });



        return view;
    }




    private void loadMapwithAddress(Address address) {
        if (address!=null){
            double latitude = address.getLat();
            double longitude = address.getLang();
            String label = address.getSuite()+","+address.getStreet();
            String uriBegin = "geo:" + latitude + "," + longitude;
            String query = latitude + "," + longitude + "(" + label + ")";
            String encodedQuery = Uri.encode(query);
            String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
            Uri uri = Uri.parse(uriString);
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }

    }

    private void callUser(UserModel  userModel){
        if (userModel!=null) {
            Intent intent = new Intent(Intent.ACTION_DIAL);

            intent.setData(Uri.parse("tel:" + userModel.getPhone()));
            startActivity(intent);
        }else{

        }
    }

    private void getUSerDetailsFromServer(String searchId) {

        if (searchId != null && !searchId.trim().isEmpty() && !isSearchClicked) {
            String url = BASE_URL + "users/?id="+searchId.trim();
            if(!checkDataAndLoad(url,RESULT_KEY_FOR_GET_USER_DETAILS)) {
                isSearchClicked= !isSearchClicked;
                new ServiceAsyncTask(getActivity(), RESULT_KEY_FOR_GET_USER_DETAILS, 0, this).execute(url);

            }
        }else{

        }
    }

    private boolean checkDataAndLoad(String url,int resultKey) {
        String data = applicationCache.getString(url,"");
        if (data!=null && !data.isEmpty()){
            try {
                JSONObject  object = new JSONObject(data);
                switch (resultKey){
                    case RESULT_KEY_FOR_GET_USER_DETAILS:
                        parsejsonForUSer(object);
                        break;
                    case RESULT_KEY_FOR_GET_USER_PICTURE:
                        parsejsonForUSerImage(object);
                        break;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return true;
        }
        return false;

    }

    private void getUSerphotoFromServer(UserModel userModel) {
String url = BASE_URL + "photos/?id="+userModel.getId();
        if (userModel != null && !checkDataAndLoad(url,RESULT_KEY_FOR_GET_USER_PICTURE)) {
            new ServiceAsyncTask(getActivity(), ResponseHandler.RESULT_KEY_FOR_GET_USER_PICTURE, 0, this).execute(url);
        }else{

        }
    }

    @Override
    public void onSucess(int resultKey, JSONObject jsonObject, String url) {
        applicationCache.addString(url,jsonObject.toString());
        switch (resultKey){
            case RESULT_KEY_FOR_GET_USER_DETAILS:
                isSearchClicked= !isSearchClicked;
                parsejsonForUSer(jsonObject);
                break;
            case RESULT_KEY_FOR_GET_USER_PICTURE:
                   parsejsonForUSerImage(jsonObject);
                break;
        }
        applicationCache.commit();

    }

    private void parsejsonForUSerImage(JSONObject jsonObject) {
        if(userModel!=null){
            userModel.setUserImageDetail(UserImageDetail.getUSerImageFromJsonList(jsonObject));
            updateImage(userModel);
        }
    }

    private void parsejsonForUSer(JSONObject jsonObject) {
        List<UserModel> userModels = UserModel.getUserListFromJson(jsonObject);
        if(userModels!=null && userModels.size() >0){
            updateUserDetails(userModels.get(0));
        }else{
            Toast.makeText(getActivity(),"Something wrong",Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUserDetails(UserModel userModel) {
        if(userModel!=null){
            this.userModel = userModel;
            userNameTv.setText("Name       : "+userModel.getName());
            userEmailTv.setText("Email      : "+userModel.getEmail());
            Company company = userModel.getCompany();
            if(company!=null)
            userPhoneTv.setText("Company     : "+company.getName());
            userAddressTv.setText("Website   : "+userModel.getWebsite());
            getUSerphotoFromServer(userModel);
//            Address address = userModel.getAddress();
//            if (address!= null)
//            userAddressTv.setText("Address : "+address.getSuite()+","+address.getStreet()+",\n"+address.getCity()+" - "+address.getZipcode());


        }

    }

    private void updateImage(UserModel userModel){
        if(userModel!= null) {
            UserImageDetail userImageDetail = userModel.getUserImageDetail();
            if (userImageDetail != null) {
Log.e("url",userImageDetail.getUrl());
                Glide.with(getActivity())
                        .load(userImageDetail.getUrl())
                        .into(user_image_id);
            }
        }
    }

    @Override
    public void onFailure(int resultKey, int position) {
        isSearchClicked= !isSearchClicked;
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
}
