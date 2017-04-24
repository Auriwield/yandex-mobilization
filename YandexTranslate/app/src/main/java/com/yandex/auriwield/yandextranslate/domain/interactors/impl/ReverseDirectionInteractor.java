package com.yandex.auriwield.yandextranslate.domain.interactors.impl;

import com.yandex.auriwield.yandextranslate.domain.executor.Executor;
import com.yandex.auriwield.yandextranslate.domain.executor.MainThread;
import com.yandex.auriwield.yandextranslate.domain.interactors.IReverseDirectionInteractor;
import com.yandex.auriwield.yandextranslate.domain.interactors.base.AbstractInteractor;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Direction;
import com.yandex.auriwield.yandextranslate.domain.repository.TranslationRepository;

/**
 * @author g.zaripov
 */
public class ReverseDirectionInteractor extends AbstractInteractor implements IReverseDirectionInteractor {
    private TranslationRepository mTranslationRepository;
    private IReverseDirectionInteractor.Callback mCallback;

    public ReverseDirectionInteractor(Executor threadExecutor,
                                      MainThread mainThread,
                                      TranslationRepository translationRepository,
                                      IReverseDirectionInteractor.Callback callback) {
        super(threadExecutor, mainThread);
        mTranslationRepository = translationRepository;
        mCallback = callback;
    }

    @Override
    public void run() {
        Direction direction = mTranslationRepository.getLastDirection();
        final Direction reverse = direction.reverse();
        mTranslationRepository.saveDirection(reverse);
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onSwapped(reverse);
            }
        });
    }
}
