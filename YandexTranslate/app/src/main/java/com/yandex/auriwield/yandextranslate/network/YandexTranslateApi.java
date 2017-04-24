package com.yandex.auriwield.yandextranslate.network;


import com.yandex.auriwield.yandextranslate.network.model.translate.LanguageMap;
import com.yandex.auriwield.yandextranslate.network.model.translate.NetTranslation;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author g.zaripov
 */

public interface YandexTranslateApi {
    String URL = "https://translate.yandex.net";

    @POST("/api/v1.5/tr.json/getLangs")
    Call<LanguageMap> getSupportedLanguages(@Query("ui") String ui);

    @POST("/api/v1.5/tr.json/detect")
    void detectlanguage(@Query("text") String text);

    @POST("/api/v1.5/tr.json/translate")
    Call<NetTranslation> translate(@Query("text") String text, @Query("lang") String direction);
}
