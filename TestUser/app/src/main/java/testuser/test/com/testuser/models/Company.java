package testuser.test.com.testuser.models;

import org.json.JSONObject;

import testuser.test.com.testuser.utils.JSONUtil;

/**
 * Created by  on 26/05/17.
 * Copyright © 2017 TestUser. All rights reserved.
 */

public class Company {
    String name, catchPhrase, bs;

    public static Company getCompanyFromJson(JSONObject jsonObject) {
        if (jsonObject != null) {

            Company company = new Company();
            company.setBs(JSONUtil.readString(jsonObject, "bs"));
            company.setCatchPhrase(JSONUtil.readString(jsonObject, "catchPhrase"));
            company.setName(JSONUtil.readString(jsonObject, "name"));
            return company;
        }
        return null;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

}
