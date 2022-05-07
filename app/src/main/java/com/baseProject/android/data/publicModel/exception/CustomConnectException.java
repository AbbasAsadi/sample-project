package com.baseProject.android.data.publicModel.exception;


import com.baseProject.android.R;

/**
 * @author Abbas Asadi
 */
public class CustomConnectException extends CustomRuntimeException {
    @Override
    public int getLabel() {
        return R.string.exception_socket_time_out;
    }

    @Override
    public int getIcon() {
        return R.drawable.ic_no_internet;
    }
}
