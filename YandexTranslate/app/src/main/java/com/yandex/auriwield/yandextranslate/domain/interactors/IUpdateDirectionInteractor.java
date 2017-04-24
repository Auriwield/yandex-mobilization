package com.yandex.auriwield.yandextranslate.domain.interactors;

import com.yandex.auriwield.yandextranslate.domain.interactors.base.Interactor;

/**
 * @author g.zaripov
 */
public interface IUpdateDirectionInteractor extends Interactor {
    interface Callback {
        void onSaveDirectionError();
        void onSaved();
    }
}
