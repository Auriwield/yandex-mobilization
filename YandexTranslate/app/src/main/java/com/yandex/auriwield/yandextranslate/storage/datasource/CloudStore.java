package com.yandex.auriwield.yandextranslate.storage.datasource;

import com.yandex.auriwield.yandextranslate.network.YandexApi;
import com.yandex.auriwield.yandextranslate.network.model.dictionary.Dic;
import com.yandex.auriwield.yandextranslate.network.model.translate.LanguageMap;
import com.yandex.auriwield.yandextranslate.network.model.translate.NetTranslation;
import com.yandex.auriwield.yandextranslate.storage.converters.DictionaryConverter;
import com.yandex.auriwield.yandextranslate.storage.converters.TranslationConverter;
import com.yandex.auriwield.yandextranslate.storage.model.dictionary.DictionaryEntryModel;
import com.yandex.auriwield.yandextranslate.storage.model.translate.LanguageModel;
import com.yandex.auriwield.yandextranslate.storage.model.translate.TranslationModel;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import timber.log.Timber;

import static com.yandex.auriwield.yandextranslate.App.UI_CODE;
/**
 * @author g.zaripov
 */

public class CloudStore implements Store {
    private TranslationConverter translationConverter;
    private DictionaryConverter dictionaryConverter;


    public CloudStore() {
        translationConverter = new TranslationConverter();
        dictionaryConverter = new DictionaryConverter();
    }

    @Override
    public List<LanguageModel> getSupportedLanguages() {
        LanguageMap map = null;
        try {
            map = YandexApi.getTranslateApi().getSupportedLanguages(UI_CODE).execute().body();
        } catch (IOException e) {
            Timber.e(e);
        }
        if (map != null) {
            return translationConverter.convertToStorageModel(map);
        }
        return null;
    }

    @Override
    public TranslationModel getTranslation(String someWord, String direction) {
        NetTranslation translation = null;
        DictionaryEntryModel wordModel = null;
        try {
            Call<NetTranslation> translate = YandexApi.getTranslateApi().translate(someWord, direction);
            Response<NetTranslation> execute = translate.execute();
            translation = execute.body();
        } catch (Exception e) {
            Timber.e(e);
        }
        if (translation != null) {
            return translationConverter.convertToStorageModel(someWord, translation);
        }
        return null;
    }

    @Override
    public DictionaryEntryModel getMeaning(String text, String direction) {
        Call<Dic> call = YandexApi.getDictionaryApi().lookup(text, direction, UI_CODE);
        Dic dic = null;
        try {
            dic = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (dic != null) {
            return dictionaryConverter.convertToStorageModel(dic);
        }

        return null;
    }
}
