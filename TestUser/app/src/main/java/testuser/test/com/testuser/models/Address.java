package testuser.test.com.testuser.models;

import org.json.JSONObject;

import testuser.test.com.testuser.utils.JSONUtil;

/**
 * Created by Ashish on 26/05/17.
 * Copyright © 2017 TestUser. All rights reserved.
 */

public class Address {
    String street, suite, city, zipcode, phone, website;
    long lat, lang;

    public static Address getAddressFromJson(JSONObject jsonObject) {
        if (jsonObject != null) {
            Address address = new Address();
            address.setCity(JSONUtil.readString(jsonObject, "city"));
            address.setStreet(JSONUtil.readString(jsonObject, "street"));
            address.setSuite(JSONUtil.readString(jsonObject, "suite"));
            address.setZipcode(JSONUtil.readString(jsonObject, "zipcode"));
            JSONObject object = JSONUtil.getJsonObject(jsonObject, "geo");
            address.setLat(JSONUtil.readlong(object, "lat"));
            address.setLang(JSONUtil.readlong(object, "lng"));
            return address;

        }
        return null;
    }

    public long getLat() {
        return lat;
    }

    public void setLat(long lat) {
        this.lat = lat;
    }

    public long getLang() {
        return lang;
    }

    public void setLang(long lang) {
        this.lang = lang;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
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
}
