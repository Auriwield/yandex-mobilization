package com.yandex.auriwield.yandextranslate.domain.interactors;

import com.yandex.auriwield.yandextranslate.domain.interactors.base.Interactor;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Language;


import java.util.List;

/**
 * Created by GZaripov1 on 28.03.2017.
 */

public interface IGetSupportedLanguagesInteractor extends Interactor {
    interface Callback {
        void onReceiveSupportedLanguages(List<Language> languages);
        void onDownloadLanguagesError();
    }
}
