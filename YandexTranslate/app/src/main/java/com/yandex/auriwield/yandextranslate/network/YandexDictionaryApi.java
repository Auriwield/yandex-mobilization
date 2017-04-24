package com.yandex.auriwield.yandextranslate.network;

import com.yandex.auriwield.yandextranslate.network.model.dictionary.Dic;
import com.yandex.auriwield.yandextranslate.network.model.request.DictionaryLookupBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author g.zaripov
 */
public interface YandexDictionaryApi {
    String URL = "https://dictionary.yandex.net";

    @POST("/api/v1/dicservice.json/getLangs")
    Call<List<String>> getSupportedLanguages();

    //@POST("/api/v1/dicservice.json/lookup")
    //Call<Dic> lookup(@Body DictionaryLookupBody body);

    @POST("/api/v1/dicservice.json/lookup")
    Call<Dic> lookup(@Query("text") String text, @Query("lang") String direction, @Query("ui") String ui);

}
