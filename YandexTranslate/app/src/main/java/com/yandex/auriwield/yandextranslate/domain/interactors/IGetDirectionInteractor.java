package com.yandex.auriwield.yandextranslate.domain.interactors;

import com.yandex.auriwield.yandextranslate.domain.interactors.base.Interactor;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Direction;

/**
 * @author g.zaripov
 */
public interface IGetDirectionInteractor extends Interactor {
    interface Callback {
        void onReceiveDirection(Direction direction);

        void onReceiveDirectionError();
    }
}
