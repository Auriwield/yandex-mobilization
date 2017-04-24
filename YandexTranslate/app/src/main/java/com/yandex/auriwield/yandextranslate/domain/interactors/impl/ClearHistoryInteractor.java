package com.yandex.auriwield.yandextranslate.domain.interactors.impl;

import com.yandex.auriwield.yandextranslate.domain.executor.Executor;
import com.yandex.auriwield.yandextranslate.domain.executor.MainThread;
import com.yandex.auriwield.yandextranslate.domain.interactors.IClearHistoryInteractor;
import com.yandex.auriwield.yandextranslate.domain.interactors.IFavoritesInteractor;
import com.yandex.auriwield.yandextranslate.domain.interactors.base.AbstractInteractor;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Translation;
import com.yandex.auriwield.yandextranslate.domain.repository.TranslationRepository;

import timber.log.Timber;

/**
 * @author g.zaripov
 */
public class ClearHistoryInteractor extends AbstractInteractor
        implements IClearHistoryInteractor {

    private TranslationRepository mTranslationRepository;
    private IClearHistoryInteractor.Callback mCallback;

    public ClearHistoryInteractor(Executor threadExecutor,
                                  MainThread mainThread,
                                  TranslationRepository translationRepository,
                                  IClearHistoryInteractor.Callback callback) {
        super(threadExecutor, mainThread);
        mTranslationRepository = translationRepository;
        mCallback = callback;
    }

    @Override
    public void run() {
        try {
            mTranslationRepository.clearHistory();
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    mCallback.onCleared();
                }
            });
        } catch (Exception e) {
            Timber.e(e);
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    mCallback.onError();
                }
            });
        }
    }

}
