package com.baseProject.android.data.publicModel.exception;

/**
 * @author Abbas Asadi
 */
public abstract class CustomRuntimeException extends Throwable {

    public abstract int getLabel();

    public abstract int getIcon();

}
