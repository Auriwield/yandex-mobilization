package com.yandex.auriwield.yandextranslate.domain.interactors;

import com.yandex.auriwield.yandextranslate.domain.model.translate.Direction;

/**
 * @author g.zaripov
 */
public interface ISwapDirectionInteractor {
    interface Callback {
        void onSwapped(Direction direction);
    }
}
