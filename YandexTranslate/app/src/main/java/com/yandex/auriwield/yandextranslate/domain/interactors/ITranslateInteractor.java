package com.yandex.auriwield.yandextranslate.domain.interactors;

import com.yandex.auriwield.yandextranslate.domain.interactors.base.Interactor;
import com.yandex.auriwield.yandextranslate.domain.model.dictionary.DictionaryEntry;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Translation;

/**
 * Created by auriw on 28.03.2017.
 */

public interface ITranslateInteractor extends Interactor {
    interface Callback {
        void onTranslated(Translation translation);
        void onTranslated(DictionaryEntry dictionaryEntry);
        void downloadTranslationError();
    }
}
