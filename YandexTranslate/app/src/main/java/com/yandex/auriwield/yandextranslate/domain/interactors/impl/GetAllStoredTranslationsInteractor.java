package com.yandex.auriwield.yandextranslate.domain.interactors.impl;

import com.yandex.auriwield.yandextranslate.domain.executor.Executor;
import com.yandex.auriwield.yandextranslate.domain.executor.MainThread;
import com.yandex.auriwield.yandextranslate.domain.interactors.base.AbstractInteractor;
import com.yandex.auriwield.yandextranslate.domain.interactors.IGetAllStoredTranslationsInteractor;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Translation;
import com.yandex.auriwield.yandextranslate.domain.repository.TranslationRepository;

import java.util.List;

/**
 * @author g.zaripov
 */
public class GetAllStoredTranslationsInteractor extends AbstractInteractor implements IGetAllStoredTranslationsInteractor {
    private TranslationRepository mTranslationRepository;
    private GetAllStoredTranslationsInteractor.Callback mCallback;

    public GetAllStoredTranslationsInteractor(Executor threadExecutor,
                                              MainThread mainThread,
                                              TranslationRepository translationRepository,
                                              GetAllStoredTranslationsInteractor.Callback callback) {
        super(threadExecutor, mainThread);
        mTranslationRepository = translationRepository;
        mCallback = callback;
    }

    @Override
    public void run() {
        try {
            final List<Translation> allTranslations = mTranslationRepository.getArchivedTranslations();

            if (allTranslations != null) {
                mMainThread.post(new Runnable() {
                    @Override
                    public void run() {
                        mCallback.onReceiveData(allTranslations);
                    }
                });
            } else {
                mMainThread.post(new Runnable() {
                    @Override
                    public void run() {
                        mCallback.onReceiveDataError();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
