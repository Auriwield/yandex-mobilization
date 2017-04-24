package com.yandex.auriwield.yandextranslate.presentation.presenters.impl;

import android.view.View;

import com.yandex.auriwield.yandextranslate.R;
import com.yandex.auriwield.yandextranslate.domain.executor.Executor;
import com.yandex.auriwield.yandextranslate.domain.executor.MainThread;
import com.yandex.auriwield.yandextranslate.domain.interactors.IClearHistoryInteractor;
import com.yandex.auriwield.yandextranslate.domain.interactors.IFavoritesInteractor;
import com.yandex.auriwield.yandextranslate.domain.interactors.IGetAllStoredTranslationsInteractor;
import com.yandex.auriwield.yandextranslate.domain.interactors.impl.ClearHistoryInteractor;
import com.yandex.auriwield.yandextranslate.domain.interactors.impl.FavoritesInteractor;
import com.yandex.auriwield.yandextranslate.domain.interactors.impl.GetAllStoredTranslationsInteractor;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Translation;
import com.yandex.auriwield.yandextranslate.domain.repository.TranslationRepository;
import com.yandex.auriwield.yandextranslate.presentation.presenters.AbstractPresenter;
import com.yandex.auriwield.yandextranslate.presentation.presenters.IHistoryPresenter;

import java.util.List;

/**
 * @author g.zaripov
 */
public class HistoryPresenter extends AbstractPresenter
        implements IHistoryPresenter, IGetAllStoredTranslationsInteractor.Callback,
        IFavoritesInteractor.Callback, IClearHistoryInteractor.Callback {

    private IHistoryPresenter.View mView;
    private TranslationRepository mTranslateRepository;

    public HistoryPresenter(Executor executor, MainThread mainThread,
                            IHistoryPresenter.View view, TranslationRepository translateRepository) {
        super(executor, mainThread);
        mView = view;
        mTranslateRepository = translateRepository;
    }

    @Override
    public void resume() {
      getAllTranslations();
    }

    @Override
    public void getAllTranslations() {
        IGetAllStoredTranslationsInteractor interactor =
                new GetAllStoredTranslationsInteractor(mExecutor, mMainThread, mTranslateRepository, this);
        interactor.execute();
    }

    @Override
    public void onClick(android.view.View v) {
        ClearHistoryInteractor interactor = new ClearHistoryInteractor(mExecutor, mMainThread,
                mTranslateRepository, this);
        interactor.run();
    }

    @Override
    public void onFavoriteIconClick(Translation translation, int position) {
        IFavoritesInteractor interactor = new FavoritesInteractor(mExecutor, mMainThread,
                mTranslateRepository, this, translation, position);
        interactor.execute();
    }

    @Override
    public void onItemClick(Translation translation, int position) {

    }

    @Override
    public void onReceiveData(List<Translation> translationList) {
        mView.updateData(translationList);
    }

    @Override
    public void onReceiveDataError() {

    }

    @Override
    public void onActionError() {

    }

    @Override
    public void onRemoved(Translation tr, int position) {
        mView.updateItem(tr, position);
    }

    @Override
    public void onAdded(Translation translation, int position) {
        mView.updateItem(translation, position);
    }

    @Override
    public void onCleared() {
        mView.clear();
    }

    @Override
    public void onError() {
        mView.showError(R.string.clear_history_error);
    }
}
