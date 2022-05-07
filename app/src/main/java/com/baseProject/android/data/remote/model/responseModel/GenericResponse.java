
package com.baseProject.android.data.remote.model.responseModel;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class GenericResponse<T> {

    @Expose
    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
