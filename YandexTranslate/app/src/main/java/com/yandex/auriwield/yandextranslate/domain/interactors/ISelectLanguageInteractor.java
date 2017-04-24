package com.yandex.auriwield.yandextranslate.domain.interactors;

import com.yandex.auriwield.yandextranslate.domain.interactors.base.Interactor;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Language;

/**
 * @author g.zaripov
 */
public interface ISelectLanguageInteractor extends Interactor {
    interface Callback {
        void onReceiveLanguage(Language language);
    }
}
