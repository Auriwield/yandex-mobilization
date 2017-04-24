package com.yandex.auriwield.yandextranslate.domain.interactors.impl;

import com.yandex.auriwield.yandextranslate.domain.executor.Executor;
import com.yandex.auriwield.yandextranslate.domain.executor.MainThread;
import com.yandex.auriwield.yandextranslate.domain.interactors.IFavoritesInteractor;
import com.yandex.auriwield.yandextranslate.domain.interactors.base.AbstractInteractor;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Translation;
import com.yandex.auriwield.yandextranslate.domain.repository.TranslationRepository;

import timber.log.Timber;

/**
 * @author g.zaripov
 */
public class FavoritesInteractor extends AbstractInteractor
        implements IFavoritesInteractor {

    private TranslationRepository mTranslationRepository;
    private IFavoritesInteractor.Callback mCallback;
    private Translation translation;
    private final int position;

    public FavoritesInteractor(Executor threadExecutor,
                               MainThread mainThread,
                               TranslationRepository translationRepository,
                               Callback callback,
                               Translation translation,
                               int position) {
        super(threadExecutor, mainThread);
        mTranslationRepository = translationRepository;
        mCallback = callback;
        this.translation = translation;
        this.position = position;
    }

    @Override
    public void run() {
        try {
            if (translation.isFavorite()) {
                final Translation tr = mTranslationRepository.removeFromFavorites(this.translation);
                mMainThread.post(new Runnable() {
                    @Override
                    public void run() {
                        if (tr != null)
                            mCallback.onRemoved(tr, position);
                        else
                            mCallback.onActionError();
                    }
                });
            } else {
                final Translation tr = mTranslationRepository.addToFavorites(this.translation);
                mMainThread.post(new Runnable() {
                    @Override
                    public void run() {
                        if (tr != null)
                            mCallback.onAdded(tr, position);
                        else
                            mCallback.onActionError();
                    }
                });
            }
        } catch (Exception e) {
            Timber.e(e);
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    mCallback.onActionError();
                }
            });
        }
    }

}
