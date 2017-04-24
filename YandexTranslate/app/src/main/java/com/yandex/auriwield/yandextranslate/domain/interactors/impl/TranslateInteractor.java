package com.yandex.auriwield.yandextranslate.domain.interactors.impl;

import com.yandex.auriwield.yandextranslate.domain.executor.Executor;
import com.yandex.auriwield.yandextranslate.domain.executor.MainThread;
import com.yandex.auriwield.yandextranslate.domain.interactors.ITranslateInteractor;
import com.yandex.auriwield.yandextranslate.domain.interactors.base.AbstractInteractor;
import com.yandex.auriwield.yandextranslate.domain.model.dictionary.DictionaryEntry;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Direction;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Translation;
import com.yandex.auriwield.yandextranslate.domain.repository.TranslationRepository;

import timber.log.Timber;


/**
 * @author g.zaripov
 */

public class TranslateInteractor extends AbstractInteractor implements ITranslateInteractor {
    private String word;
    private Direction direction;
    private TranslationRepository mTranslationRepository;
    private ITranslateInteractor.Callback mCallback;


    public TranslateInteractor(Executor threadExecutor, MainThread mainThread, String word,
                               Direction direction, TranslationRepository translationRepository,
                               Callback callback) {
        super(threadExecutor, mainThread);
        this.word = word;
        this.direction = direction;
        mTranslationRepository = translationRepository;
        mCallback = callback;
    }

    @Override
    public void run() {
        word = word.trim();
        if (word.isEmpty()) return;
        try {
            //Direction direction = mTranslationRepository.getLastDirection();
            final Translation translation = mTranslationRepository.getTranslation(word, direction);
            if (translation != null) {
                direction = translation.getDirection();
                final DictionaryEntry dicDictionaryEntry = mTranslationRepository.getMeaning(word, direction);
                System.out.println();
                mMainThread.post(new Runnable() {
                    @Override
                    public void run() {
                            mCallback.onTranslated(translation);
                        if(dicDictionaryEntry != null)
                            mCallback.onTranslated(dicDictionaryEntry);
                    }
                });
            } else {
                mMainThread.post(new Runnable() {
                    @Override
                    public void run() {
                        mCallback.downloadTranslationError();
                    }
                });
            }
        } catch (Exception e) {
            Timber.e(e);
        }
    }

}
