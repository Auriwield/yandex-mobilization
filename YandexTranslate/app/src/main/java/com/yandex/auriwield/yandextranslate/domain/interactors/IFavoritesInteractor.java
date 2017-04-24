package com.yandex.auriwield.yandextranslate.domain.interactors;

import com.yandex.auriwield.yandextranslate.domain.interactors.base.Interactor;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Translation;

/**
 * @author g.zaripov
 */
public interface IFavoritesInteractor extends Interactor {
    interface Callback {
        void onActionError();
        void onAdded(Translation translation, int position);
        void onRemoved(Translation tr, int position);
    }
}
