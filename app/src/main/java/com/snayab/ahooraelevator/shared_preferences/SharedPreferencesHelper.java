package com.snayab.ahooraelevator.shared_preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper implements ISharedPreferences {

    private static final String PREFERENCES_FILE_NAME = "main_preferences";

    private final SharedPreferences sharedPreferences;

    public SharedPreferencesHelper(Context context) {

        sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);

    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    @Override
    public void setUserPhone(String userPhone) {
        sharedPreferences.edit().putString(SharedPreferencesModels.PREFERENCES_USER_PHONE, userPhone).apply();
    }

    @Override
    public String getUserPhone() {
        return sharedPreferences.getString(SharedPreferencesModels.PREFERENCES_USER_PHONE, "");
    }

    @Override
    public void setUserApiToken(String apiToken) {
        sharedPreferences.edit().putString(SharedPreferencesModels.PREFERENCES_USER_API_TOKEN, apiToken).apply();
    }

    @Override
    public String getApiToken() {
        return sharedPreferences.getString(SharedPreferencesModels.PREFERENCES_USER_API_TOKEN, "");
    }

    @Override
    public void setHasUserSignedIn(boolean userSignedIn) {
        sharedPreferences.edit().putBoolean(SharedPreferencesModels.PREFERENCES_HAS_USER_SIGNED_IN, userSignedIn).apply();
    }

    @Override
    public Boolean getHasUserSignedIn() {
        return sharedPreferences.getBoolean(SharedPreferencesModels.PREFERENCES_HAS_USER_SIGNED_IN, false);
    }

    @Override
    public void setUserId(int userId) {
        sharedPreferences.edit().putInt(SharedPreferencesModels.PREFERENCES_USER_ID, userId).apply();
    }

    @Override
    public Integer getUserId() {
        return sharedPreferences.getInt(SharedPreferencesModels.PREFERENCES_USER_ID, 0);
    }

    @Override
    public void setUserName(String userName) {
        sharedPreferences.edit().putString(SharedPreferencesModels.PREFERENCES_USER_NAME, userName).apply();
    }

    @Override
    public String getUserName() {
        return sharedPreferences.getString(SharedPreferencesModels.PREFERENCES_USER_NAME, "");
    }

    @Override
    public void setUserPushPoleId(String userPushPoleId) {
        sharedPreferences.edit().putString(SharedPreferencesModels.PREFERENCES_USER_PUSH_POLE_ID, userPushPoleId).apply();
    }

    @Override
    public String getUserPushPoleId() {
        return sharedPreferences.getString(SharedPreferencesModels.PREFERENCES_USER_PUSH_POLE_ID, "");
    }

}
