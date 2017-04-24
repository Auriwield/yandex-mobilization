package com.yandex.auriwield.yandextranslate.domain.interactors.impl;

import com.yandex.auriwield.yandextranslate.domain.executor.Executor;
import com.yandex.auriwield.yandextranslate.domain.executor.MainThread;
import com.yandex.auriwield.yandextranslate.domain.interactors.IGetSupportedLanguagesInteractor;
import com.yandex.auriwield.yandextranslate.domain.interactors.base.AbstractInteractor;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Language;
import com.yandex.auriwield.yandextranslate.domain.repository.TranslationRepository;


import java.util.List;

/**
 * Created by GZaripov1 on 28.03.2017.
 */

public class GetSupportedLanguagesInteractor extends AbstractInteractor
        implements IGetSupportedLanguagesInteractor {
    private TranslationRepository mTranslationRepository;
    private IGetSupportedLanguagesInteractor.Callback mCallback;

    public GetSupportedLanguagesInteractor(Executor threadExecutor,
                                           MainThread mainThread,
                                           TranslationRepository translationRepository,
                                           GetSupportedLanguagesInteractor.Callback callback) {
        super(threadExecutor, mainThread);
        mTranslationRepository = translationRepository;
        mCallback = callback;
    }

    @Override
    public void run() {
        final List<Language> supportedLanguages = mTranslationRepository.getSupportedLanguages();
        if (supportedLanguages != null) {
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    mCallback.onReceiveSupportedLanguages(supportedLanguages);
                }
            });
        } else {
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    mCallback.onDownloadLanguagesError();
                }
            });
        }
    }
}
