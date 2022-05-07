package com.baseProject.android.data.publicModel.exception;


import com.baseProject.android.R;

/**
 * @author Abbas Asadi
 */
public class UnknownException extends CustomRuntimeException {

    private final Throwable throwable;

    public UnknownException(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public int getLabel() {
        return R.string.exception_unknown;
    }

    @Override
    public int getIcon() {
        return R.drawable.ic_no_internet;
    }

    public Throwable getThrowable() {
        return throwable;
    }

}
