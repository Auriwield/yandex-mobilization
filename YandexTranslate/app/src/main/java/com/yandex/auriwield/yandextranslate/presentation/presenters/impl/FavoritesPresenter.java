package com.yandex.auriwield.yandextranslate.presentation.presenters.impl;

import com.yandex.auriwield.yandextranslate.R;
import com.yandex.auriwield.yandextranslate.domain.executor.Executor;
import com.yandex.auriwield.yandextranslate.domain.executor.MainThread;
import com.yandex.auriwield.yandextranslate.domain.interactors.IClearFavoritesInteractor;
import com.yandex.auriwield.yandextranslate.domain.interactors.IFavoritesInteractor;
import com.yandex.auriwield.yandextranslate.domain.interactors.IGetFavoriteTranslationsInteractor;
import com.yandex.auriwield.yandextranslate.domain.interactors.impl.ClearFavoritesInteractor;
import com.yandex.auriwield.yandextranslate.domain.interactors.impl.FavoritesInteractor;
import com.yandex.auriwield.yandextranslate.domain.interactors.impl.GetFavoriteTranslationsInteractorInteractor;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Translation;
import com.yandex.auriwield.yandextranslate.domain.repository.TranslationRepository;
import com.yandex.auriwield.yandextranslate.presentation.presenters.AbstractPresenter;
import com.yandex.auriwield.yandextranslate.presentation.presenters.IFavoritesPresenter;

import java.util.List;

/**
 * @author g.zaripov
 */
public class FavoritesPresenter extends AbstractPresenter implements IFavoritesPresenter,
        IGetFavoriteTranslationsInteractor.Callback, IFavoritesInteractor.Callback, IClearFavoritesInteractor.Callback {

    private IFavoritesPresenter.View mView;
    private TranslationRepository mTranslateRepository;

    public FavoritesPresenter(Executor executor, MainThread mainThread,
                              IFavoritesPresenter.View view, TranslationRepository translateRepository) {
        super(executor, mainThread);
        mView = view;
        mTranslateRepository = translateRepository;
    }

    @Override
    public void resume() {
        getFavoriteTranslations();
    }

    @Override
    public void getFavoriteTranslations() {
        IGetFavoriteTranslationsInteractor interactor =
                new GetFavoriteTranslationsInteractorInteractor(mExecutor, mMainThread, mTranslateRepository, this);
        interactor.execute();
    }

    @Override
    public void onReceiveData(List<Translation> translationList) {
        mView.onDataUpdate(translationList);
    }

    @Override
    public void onReceiveDataError() {
        mView.showError(R.string.favorites_receive_data_error);
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
    public void onActionError() {
        mView.showError(R.string.delete_from_favorite_error);
    }

    @Override
    public void onRemoved(Translation tr, int position) {
        mView.removeItem(position);
        mView.checkList();
    }

    @Override
    public void onClick(android.view.View v) {
        ClearFavoritesInteractor interactor = new ClearFavoritesInteractor(mExecutor, mMainThread,
                mTranslateRepository, this);
        interactor.run();

    }

    @Override
    public void onAdded(Translation translation, int position) {

    }

    @Override
    public void onCleared() {
        mView.clearData();
    }

    @Override
    public void onError() {
        mView.showError(R.string.favorites_clear_error);
    }
}
