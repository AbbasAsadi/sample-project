package com.baseProject.android.di.module;

import static com.baseProject.android.data.remote.api.ServerAddresses.BASE_URL;

import android.app.Application;

import com.baseProject.android.data.remote.api.ApiService;
import com.baseProject.android.data.remote.interceptor.ApiResponseInterceptor;
import com.baseProject.android.data.remote.interceptor.RequestInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Abbas Asadi
 */
@Module
public class ApiModule {


    private static final String TAG = "ApiModule";

    /**
     * The method returns the Gson object
     */

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .setLenient()
                .disableHtmlEscaping()
//                .setDateFormat(UtcISODate.DATE_FORMAT)
                //.registerTypeAdapter(UtcISODate.class , new GsonUTCDateAdapter())
                //.registerTypeAdapter(Date.class, new GsonUTCDateAdapter())
//                .registerTypeAdapter(Widget.class , new WidgetDeserializer())
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    /* *//**
     * The method returns the Cache object
     *//*

    @Provides
    @Singleton
    Cache provideCache(Application application) {
        long cacheSize = 10 * 1024 * 1024; // 10 MB
        File httpCacheDirectory = new File(application.getCacheDir(), "http-cache");
        return new Cache(httpCacheDirectory, cacheSize);
    }*/

    /**
     * The method returns the HttpLoggingInterceptor object
     */

    @Provides
    @Singleton
    LoggingInterceptor provideHttpLoggingInterceptor() {
        return new LoggingInterceptor.Builder()
                .setLevel(Level.BASIC)
                .log(Platform.INFO)
                .request("Request")
                .response("Response")
                .addHeader("version", "BuildConfig.VERSION_NAME")
                .build();
     /*   HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> Log.i(TAG, "provideHttpLoggingInterceptor: " + message));
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;*/
    }

    /**
     * The method returns the Okhttp object
     */

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(LoggingInterceptor loggingInterceptor, /*Cache cache,*/ Application application) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//        httpClient.cache(cache);
        httpClient.addInterceptor(loggingInterceptor);
        httpClient.addInterceptor(new RequestInterceptor());
        httpClient.addNetworkInterceptor(new ApiResponseInterceptor(application));
        httpClient.connectTimeout(30, TimeUnit.SECONDS);
        httpClient.readTimeout(30, TimeUnit.SECONDS);
        httpClient.writeTimeout(30, TimeUnit.SECONDS);
        httpClient.protocols(Collections.singletonList(Protocol.HTTP_1_1));

        return httpClient.build();
    }


    /**
     * The method returns the Retrofit object
     */

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build();
    }

    /**
     * The method provide api service
     */

    @Provides
    @Singleton
    ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

}
