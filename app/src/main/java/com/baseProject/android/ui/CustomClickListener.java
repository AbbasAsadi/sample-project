package com.baseProject.android.ui;

import android.util.Log;
import android.view.View;

import com.jakewharton.rxbinding4.view.RxView;

import java.util.concurrent.TimeUnit;


/**
 * @author Abbas Asadi
 */
public class CustomClickListener implements View.OnClickListener {
    private final ClickEvent onClickListener;

    public CustomClickListener(ClickEvent onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public void onClick(View v) {
        RxView.clicks(v).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(empty -> {
                    // action on click
                    Log.i("click event", "button clicked ...");
                    onClickListener.onClickedHappened();

                });
    }

    public interface ClickEvent {
        void onClickedHappened();
    }
}
