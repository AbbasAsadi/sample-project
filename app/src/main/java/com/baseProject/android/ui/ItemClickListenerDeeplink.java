package com.baseProject.android.ui;

/**
 * @author Abbas Asadi
 */
public interface ItemClickListenerDeeplink {
    void onItemSelected(String url);

    void onItemSelectedInternal(String section, String slug);

    void onItemSelectedInternal(String section, Long id);
}
