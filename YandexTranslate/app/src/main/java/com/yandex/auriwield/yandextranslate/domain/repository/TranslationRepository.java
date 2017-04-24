package com.yandex.auriwield.yandextranslate.domain.repository;



import com.yandex.auriwield.yandextranslate.domain.model.dictionary.DictionaryEntry;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Direction;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Language;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Translation;

import java.util.List;

/**
 * Created by auriw on 26.03.2017.
 */
//
public interface TranslationRepository {
    List<Language> getSupportedLanguages();

    //void saveSupportedLanguages(List<LanguageModel> languages);

    Translation getTranslation(String someWord, Direction direction);

    List<Translation> getArchivedTranslations();

    DictionaryEntry getMeaning(String someWord, Direction direction);

    Direction getLastDirection();

    void saveDirection(Direction direction);

    Language getLanguageByCode(String code);

    Translation addToFavorites(Translation translation);

    Translation removeFromFavorites(Translation translation);

    List<Translation> getFavoriteTranslations();


    void clearHistory();

    void clearFavorites();
}
