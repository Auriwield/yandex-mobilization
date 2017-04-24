package com.yandex.auriwield.yandextranslate.storage.datasource;

import com.yandex.auriwield.yandextranslate.storage.model.dictionary.DictionaryEntryModel;
import com.yandex.auriwield.yandextranslate.storage.model.translate.LanguageModel;
import com.yandex.auriwield.yandextranslate.storage.model.translate.TranslationModel;

import java.util.List;

/**
 * Created by auriw on 28.03.2017.
 */

public interface Store {

    List<LanguageModel> getSupportedLanguages();

    TranslationModel getTranslation(String someWord, String direction);

    DictionaryEntryModel getMeaning(String text, String direction);


}
