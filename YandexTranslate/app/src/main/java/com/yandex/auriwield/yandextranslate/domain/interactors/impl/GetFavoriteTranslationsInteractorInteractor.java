package com.yandex.auriwield.yandextranslate.domain.interactors.impl;

import com.yandex.auriwield.yandextranslate.domain.executor.Executor;
import com.yandex.auriwield.yandextranslate.domain.executor.MainThread;
import com.yandex.auriwield.yandextranslate.domain.interactors.base.AbstractInteractor;
import com.yandex.auriwield.yandextranslate.domain.interactors.IGetFavoriteTranslationsInteractor;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Translation;
import com.yandex.auriwield.yandextranslate.domain.repository.TranslationRepository;

import java.util.List;

/**
 * @author g.zaripov
 */
public class GetFavoriteTranslationsInteractorInteractor extends AbstractInteractor implements IGetFavoriteTranslationsInteractor {
    private TranslationRepository mTranslationRepository;
    private GetFavoriteTranslationsInteractorInteractor.Callback mCallback;

    public GetFavoriteTranslationsInteractorInteractor(Executor threadExecutor,
                                                       MainThread mainThread,
                                                       TranslationRepository translationRepository,
                                                       GetFavoriteTranslationsInteractorInteractor.Callback callback) {
        super(threadExecutor, mainThread);
        mTranslationRepository = translationRepository;
        mCallback = callback;
    }

    @Override
    public void run() {
        try {
            final List<Translation> allTranslations = mTranslationRepository.getFavoriteTranslations();

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
