package com.yandex.auriwield.yandextranslate.domain.interactors;

import com.yandex.auriwield.yandextranslate.domain.interactors.base.Interactor;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Direction;

/**
 * @author g.zaripov
 */
public interface IReverseDirectionInteractor extends Interactor {
    interface Callback {
        void onSwapped(Direction direction);
    }
}
