package com.baseProject.android.di.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.baseProject.android.R;
import com.squareup.picasso.Picasso;

public class DataBindingAdapter {
    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url) {
        Picasso.get().load(url).placeholder(R.drawable.placeholder_image).into(view);
    }

    @BindingAdapter(value = {"visibleInvisible"})
    public static void visibility(View view, boolean isVisible) {
        view.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
    }

    @BindingAdapter(value = {"visibleGone"})
    public static void visibleGone(View view, boolean isVisible) {
        view.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

}
