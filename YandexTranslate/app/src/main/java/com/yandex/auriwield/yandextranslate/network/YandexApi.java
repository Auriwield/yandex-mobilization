package com.yandex.auriwield.yandextranslate.network;

import com.yandex.auriwield.yandextranslate.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.yandex.auriwield.yandextranslate.BuildConfig.YANDEX_DICT_KEY;
import static com.yandex.auriwield.yandextranslate.BuildConfig.YANDEX_TRANSL_KEY;

/**
 * @author g.zaripov
 */

public class YandexApi {

    private static final YandexTranslateApi translateApi = provideYandexTranslateApi();
    private static final YandexDictionaryApi dictionaryApi = provideYandexDictionaryApi();

    public static YandexTranslateApi getTranslateApi() {
        return translateApi;
    }

    public static YandexDictionaryApi getDictionaryApi() {
        return dictionaryApi;
    }

    private static YandexTranslateApi provideYandexTranslateApi() {

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("key", YANDEX_TRANSL_KEY)
                        .build();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(interceptor);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(YandexTranslateApi.URL)
                .client(httpClient.build())
                .build();

        return retrofit.create(YandexTranslateApi.class);
    }

    private static YandexDictionaryApi provideYandexDictionaryApi() {

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("key", YANDEX_DICT_KEY)
                        .build();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(interceptor);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(YandexDictionaryApi.URL)
                .client(httpClient.build())
                .build();

        return retrofit.create(YandexDictionaryApi.class);
    }

}