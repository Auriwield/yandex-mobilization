package com.yandex.auriwield.yandextranslate.domain.interactors.impl;

import com.yandex.auriwield.yandextranslate.domain.executor.Executor;
import com.yandex.auriwield.yandextranslate.domain.executor.MainThread;
import com.yandex.auriwield.yandextranslate.domain.interactors.IUpdateDirectionInteractor;
import com.yandex.auriwield.yandextranslate.domain.interactors.base.AbstractInteractor;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Direction;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Language;
import com.yandex.auriwield.yandextranslate.domain.repository.TranslationRepository;

/**
 * @author g.zaripov
 */
public class UpdateDirectionInteractor extends AbstractInteractor implements IUpdateDirectionInteractor {
    private TranslationRepository mTranslationRepository;
    private UpdateDirectionInteractor.Callback mCallback;
    private Language language;
    private Language.TYPE type;


    public UpdateDirectionInteractor(Executor threadExecutor, MainThread mainThread, Language.TYPE type,
                                     Language language, TranslationRepository translationRepository,
                                     UpdateDirectionInteractor.Callback callback) {
        super(threadExecutor, mainThread);
        this.language =  language;
        this.type = type;
        mTranslationRepository = translationRepository;
        mCallback = callback;
    }

    @Override
    public void run() {

        Direction lastDirection = mTranslationRepository.getLastDirection();

        Direction d;

        if (type == Language.TYPE.SOURCE) {
            d = new Direction(language, lastDirection.getTarget());
        } else {
            d = new Direction(lastDirection.getSource(), language);
        }
        mTranslationRepository.saveDirection(d);
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onSaved();
            }
        });
    }
}
