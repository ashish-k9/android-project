package testuser.test.com.testuser.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import testuser.test.com.testuser.utils.JSONUtil;

/**
 * Created by  on 26/05/17.
 * Copyright © 2017 TestUser. All rights reserved.
 */

public class UserModel {
    int id;
    String name;
    String username;
    String phone;
    UserImageDetail userImageDetail;

    public UserImageDetail getUserImageDetail() {
        return userImageDetail;
    }

    public void setUserImageDetail(UserImageDetail userImageDetail) {
        this.userImageDetail = userImageDetail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    String website;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    String email;

    Address address;
    Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public static UserModel getUserModelFromJSon(JSONObject jsonObject){
        if(jsonObject != null){
            UserModel userModel = new UserModel();
            userModel.setEmail(JSONUtil.readString(jsonObject,"email"));
            userModel.setId(JSONUtil.readInt(jsonObject,"id"));
            userModel.setName(JSONUtil.readString(jsonObject,"name"));
            userModel.setUsername(JSONUtil.readString(jsonObject,"username"));
            userModel.setPhone(JSONUtil.readString(jsonObject,"phone"));
            userModel.setWebsite(JSONUtil.readString(jsonObject,"website"));
            userModel.setAddress(Address.getAddressFromJson(JSONUtil.getJsonObject(jsonObject,"address")));
            userModel.setCompany(Company.getCompanyFromJson(JSONUtil.getJsonObject(jsonObject,"company")));
            return  userModel;
        }

        return  null;
    }

    public static List<UserModel> getUserListFromJson(JSONObject jsonObject){
        if(jsonObject != null){
            JSONArray jsonArray = JSONUtil.getJsonArray(jsonObject,"results");
        List<UserModel> list = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject  object = JSONUtil.getJsonObjectFromArray(jsonArray,i);
                UserModel userModel = UserModel.getUserModelFromJSon(object);
                if (userModel != null)
                list.add(userModel);
            }

            return list;
        }

        return null;
    }







}


