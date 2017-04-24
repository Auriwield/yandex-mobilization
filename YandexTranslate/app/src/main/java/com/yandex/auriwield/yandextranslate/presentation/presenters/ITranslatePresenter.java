package com.yandex.auriwield.yandextranslate.presentation.presenters;


import com.yandex.auriwield.yandextranslate.domain.model.dictionary.DictionaryEntry;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Direction;
import com.yandex.auriwield.yandextranslate.presentation.ui.BaseView;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Language;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Translation;

/**
 * @author g.zaripov
 */

public interface ITranslatePresenter extends BasePresenter {


    interface View extends BaseView {
        void showTranslation(Translation translation);

        void applyLanguage(String lang);

        void updateDirection(Direction direction);

        //void onReceiveSupportedLanguages(List<Language> languages);

        void showLanguagesActivity(Language.TYPE type);

        void showDictionaryEntry(DictionaryEntry entry);

        void updateFavoritesIcon(Translation translation);
        void clearView();

        void updateEditText(String text);
    }

    void onFavoriteIconClick();

    void translate(String string);

    String detectLanguage(String string);

    void reverseDirection();

    void getSupportedLanguages();

    void chooseLanguage(Language.TYPE type);

    void onClear();
}
