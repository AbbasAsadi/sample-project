package com.baseProject.android.util;

import com.orhanobut.hawk.Hawk;

public class PrefManager {
    private static final String PREF_NAME = "AppSharedPreferences";
    private static final String USER_ID = "userID";


    public static Integer getUserID() {
        return Hawk.get(USER_ID);
    }

    public static void setUserID(int userID) {
        Hawk.put(USER_ID, userID);
    }
}
