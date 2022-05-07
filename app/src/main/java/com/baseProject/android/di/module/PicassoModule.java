package com.baseProject.android.di.module;

import android.app.Application;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;


/**
 * @author Abbas Asadi
 */
@Module
public class PicassoModule {


    @Provides
    @Singleton
    public Picasso providePicasso(Application context, OkHttp3Downloader downloader) {
        Picasso picasso = new Picasso.Builder(context)
                .downloader(downloader)
                /*.indicatorsEnabled(true)
                .loggingEnabled(true)*/

                .build();
        Picasso.setSingletonInstance(picasso);

        return picasso;
    }

    @Provides
    @Singleton
    public OkHttp3Downloader provideOkHttp3Downloader(OkHttpClient client) {
        return new OkHttp3Downloader(client);
    }
}
