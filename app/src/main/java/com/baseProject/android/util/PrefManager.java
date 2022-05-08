package com.baseProject.android.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    private static final String PREF_NAME = "AppSharedPreferences";
    private static final String USER_ID = "userID";

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    // shared pref mode
    int PRIVATE_MODE = 0;

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public int getUserID() {
        return pref.getInt(USER_ID, -1);
    }

    public void setUserID(int userID) {
        editor.putInt(USER_ID, userID);
        editor.commit();
    }
}
