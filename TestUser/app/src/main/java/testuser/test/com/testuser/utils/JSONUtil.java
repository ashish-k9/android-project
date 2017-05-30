package testuser.test.com.testuser.utils;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * This is used to read the JSONObject properties
 *
 * @author Ashish
 */
public class JSONUtil {

    /**
     * This method reads the value of specified key from the JSON object
     *
     * @param obj the specified JSON object
     * @param key the specified key
     * @return Provides the value of key, if the key is not available then it returns null
     */
    public static String readString(JSONObject obj, String key) {
        try {
            return obj.getString(key);
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
        return "";
    }

    public static long readlong(JSONObject obj, String key) {
        try {
            return obj.getLong(key);
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
        return 0;
    }

    /**
     * This reads the double value of specified key from JSON object
     *
     * @param obj the specified JSON object
     * @param key the specified key
     * @return Provides double value of key, if key is not available then it return 0
     */
    public static double readDouble(JSONObject obj, String key) {
        try {
            return obj.getDouble(key);
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
        return 0;
    }

    /**
     * This method reads the integer value of specified key from the JSON object
     *
     * @param obj the specified JSON object
     * @param key the specified key
     * @return Provides the integer value of the key, if the key is not available then it returns 0
     */
    public static int readInt(JSONObject obj, String key) {
        try {
            return obj.getInt(key);
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
        return 0;
    }

    public static boolean readBoolean(JSONObject obj, String key) {
        try {
            return obj.getBoolean(key);
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
        return false;
    }

    public static JSONObject getJsonObject(JSONObject object, String key) {
        try {
            return object.getJSONObject(key);
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
        return null;
    }

    public static JSONObject getJsonObjectFromArray(JSONArray array, int key) {
        try {
            return array.getJSONObject(key);
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
        return null;
    }

    public static JSONArray getJsonArray(JSONObject object, String key) {
        try {
            return object.getJSONArray(key);
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
        return new JSONArray();
    }

    public static String getStringFromJsonArray(JSONArray object, int key) {
        try {
            return object.getString(key);
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
        return "";
    }
}
