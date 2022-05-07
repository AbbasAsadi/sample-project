package com.baseProject.android.data.publicModel.exception;


import com.baseProject.android.R;

/**
 * @author Abbas Asadi
 */
public class InternetConnectionException extends CustomRuntimeException {

    @Override
    public int getLabel() {
        return R.string.no_internet_connection;
    }

    @Override
    public int getIcon() {
        return R.drawable.ic_no_internet;
    }
}
