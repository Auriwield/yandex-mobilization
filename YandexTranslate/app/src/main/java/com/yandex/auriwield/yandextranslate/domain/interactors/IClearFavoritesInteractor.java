package com.yandex.auriwield.yandextranslate.domain.interactors;

import com.yandex.auriwield.yandextranslate.domain.interactors.base.Interactor;

/**
 * @author g.zaripov
 */
public interface IClearFavoritesInteractor extends Interactor {
    interface Callback {
        void onCleared();
        void onError();
    }
}
