package com.snayab.ahooraelevator.shared_preferences;

public interface ISharedPreferences {

    void setUserPhone(String userPhone);

    String getUserPhone();

    void setUserApiToken(String apiToken);

    String getApiToken();

    void setHasUserSignedIn(boolean userSignedIn);

    Boolean getHasUserSignedIn();

    void setUserId(int userId);

    Integer getUserId();

    void setUserName(String userName);

    String getUserName();

    void setUserPushPoleId(String userPushPoleId);

    String getUserPushPoleId();


}
