package com.yandex.auriwield.yandextranslate.presentation.presenters;

import com.yandex.auriwield.yandextranslate.domain.model.translate.Language;
import com.yandex.auriwield.yandextranslate.presentation.ui.BaseView;

import java.util.List;

/**
 * @author g.zaripov
 */
public interface IChooseLanguagePresenter extends BasePresenter {

    interface View extends BaseView {
        void onReceiveSupportedLanguages(List<Language> languages);

        void selectLanguage(Language language);

        void finishActivity();
    }

    void getSupportedLanguages();

    void selectLanguage(Language.TYPE type);

    void updateDirection(Language.TYPE type, Language language);
}