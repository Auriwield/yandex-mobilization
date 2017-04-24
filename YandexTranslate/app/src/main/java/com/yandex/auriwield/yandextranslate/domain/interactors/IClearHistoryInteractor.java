package com.yandex.auriwield.yandextranslate.domain.interactors;

import com.yandex.auriwield.yandextranslate.domain.interactors.base.Interactor;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Translation;

/**
 * @author g.zaripov
 */
public interface IClearHistoryInteractor extends Interactor {
    interface Callback {
        void onCleared();
        void onError();
    }
}
