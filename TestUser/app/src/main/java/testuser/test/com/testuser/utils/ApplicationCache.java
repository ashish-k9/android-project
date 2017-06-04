package testuser.test.com.testuser.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashSet;


/**
 * This class maintains the cache using preferences having key, value pair
 *
 * @author Ashish
 */
public class ApplicationCache {

    private static final String PREF_NAME = "TestUSerCAche";

    /**
     * Context representation
     */
    private Activity context;
    private Context mcontext;

    /**
     * Data Storage using preferences
     */
    private SharedPreferences preferences;

    /**
     * Preferences editor holding the key value pair
     */
    private Editor editor;


    /**
     * Creation of Cache with specified context
     *
     * @param context the specified context
     */
    public ApplicationCache(Activity context) {
        this.context = context;
        init();
    }

    public ApplicationCache(Context context) {
        this.mcontext = context;
        init();
    }

    /**
     * Initialization of the required storage preferences
     */
    private void init() {
        if (context != null) {
            preferences = context.getSharedPreferences(PREF_NAME, 0);
            editor = preferences.edit();
        }
        if (mcontext != null) {
            preferences = mcontext.getSharedPreferences(PREF_NAME, 0);
            editor = preferences.edit();
        }
    }


    /**
     * Adds the specified key value pair into the preferences
     *
     * @param key   the specified key
     * @param value the specified integer value for the key
     */
    public void addInt(String key, int value) {
        editor.putInt(key, value);
    }

    /**
     * Stores the specified key value pair into the preferences
     *
     * @param key   the specified key
     * @param value the specified value for the key
     */
    public void addString(String key, String value) {
        editor.putString(key, value);
    }

    /**
     * Stores the set of values for the specified key
     *
     * @param key    the specified key
     * @param values the set of values
     */
    @SuppressLint("NewApi")
    public void addStringSet(String key, HashSet<String> values) {
        editor.putStringSet(key, values);
    }

    /**
     * Stores the boolean value for the specified key
     *
     * @param key   the specified key
     * @param value the specified boolean value
     */
    public void addBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
    }

    /**
     * Retrieves the integer value for the specified key
     *
     * @param key      the specified key
     * @param defValue the default value
     * @return Provides the stored value of key, if not available then provides the default value
     */
    public int getInt(String key, int defValue) {
        return preferences.getInt(key, defValue);
    }

    /**
     * Retrieves the string value for the specified key
     *
     * @param key      the specified key
     * @param defValue the default value
     * @return Provides the stored value of key, if not available then provides the default value
     */
    public String getString(String key, String defValue) {
        return preferences.getString(key, defValue);
    }

    /**
     * Retrieves the set of values for the specified key
     *
     * @param key      the specified key
     * @param defValue the default set of values
     * @return Provides the stored value of key, if not available then provides the default set
     */
    @SuppressLint("NewApi")
    public HashSet<String> getStringSet(String key, HashSet<String> defValue) {
        return (HashSet<String>) preferences.getStringSet(key, defValue);
    }

    /**
     * Retrieves the boolean value for the specified key
     *
     * @param key      the specified key
     * @param defValue the default value
     * @return Provides the stored value of key, if not available then provides the default value
     */
    public boolean getBoolean(String key, boolean defValue) {
        return preferences.getBoolean(key, defValue);
    }

    /**
     * Stores the specified long value of the key
     *
     * @param key   the specified key
     * @param value the long value to store
     */
    public void addLong(String key, long value) {
        editor.putLong(key, value);

    }

    public void addFloat(String key, float value) {
        editor.putFloat(key, value);

    }

    public float getFloat(String key, float defValue) {
        return preferences.getFloat(key, defValue);
    }

    /**
     * Retrieves the long value for the specified key
     *
     * @param key      the specified key
     * @param defValue the default value
     * @return Provides the stored value of key, if not available then provides the default value
     */
    public long getLong(String key, long defValue) {
        return preferences.getLong(key, defValue);
    }

    /**
     * Makes the data storage persistent
     */
    public void commit() {
        editor.commit();
    }

    /**
     * Deletes the stored data
     */
    public void removeUserData() {
        editor.clear();
        editor.commit();
    }


    public void clearCacheData() {
        if (editor != null)
            editor.clear().commit();

    }

}
