package com.yandex.auriwield.yandextranslate.domain.interactors;

import com.yandex.auriwield.yandextranslate.domain.interactors.base.Interactor;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Translation;

import java.util.List;

/**
 * @author g.zaripov
 */
public interface IGetAllStoredTranslationsInteractor extends Interactor {
    interface Callback {
        void onReceiveData(List<Translation> translationList);

        void onReceiveDataError();
    }
}
