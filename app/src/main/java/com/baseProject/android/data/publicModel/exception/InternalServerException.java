package com.baseProject.android.data.publicModel.exception;


import com.baseProject.android.R;

public class InternalServerException extends CustomRuntimeException {

    @Override
    public int getLabel() {
        return R.string.internal_server_error;
    }

    @Override
    public int getIcon() {
        return R.drawable.ic_no_internet;
    }
}
