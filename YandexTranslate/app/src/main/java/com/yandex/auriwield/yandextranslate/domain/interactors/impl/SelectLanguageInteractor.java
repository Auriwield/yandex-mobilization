package com.yandex.auriwield.yandextranslate.domain.interactors.impl;

import com.yandex.auriwield.yandextranslate.domain.executor.Executor;
import com.yandex.auriwield.yandextranslate.domain.executor.MainThread;
import com.yandex.auriwield.yandextranslate.domain.interactors.IGetDirectionInteractor;
import com.yandex.auriwield.yandextranslate.domain.interactors.ISelectLanguageInteractor;
import com.yandex.auriwield.yandextranslate.domain.interactors.base.AbstractInteractor;
import com.yandex.auriwield.yandextranslate.domain.repository.TranslationRepository;

/**
 * @author g.zaripov
 */
public class SelectLanguageInteractor extends AbstractInteractor implements ISelectLanguageInteractor {
    private TranslationRepository mTranslationRepository;
    private ISelectLanguageInteractor.Callback mCallback;

    public SelectLanguageInteractor(Executor threadExecutor,
                                    MainThread mainThread,
                                    TranslationRepository translationRepository,
                                    ISelectLanguageInteractor.Callback callback) {
        super(threadExecutor, mainThread);
        mTranslationRepository = translationRepository;
        mCallback = callback;
    }

    @Override
    public void run() {

    }
}
