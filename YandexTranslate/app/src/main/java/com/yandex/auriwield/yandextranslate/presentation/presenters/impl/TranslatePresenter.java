package com.yandex.auriwield.yandextranslate.presentation.presenters.impl;

import com.yandex.auriwield.yandextranslate.R;
import com.yandex.auriwield.yandextranslate.domain.executor.Executor;
import com.yandex.auriwield.yandextranslate.domain.executor.MainThread;
import com.yandex.auriwield.yandextranslate.domain.interactors.IFavoritesInteractor;
import com.yandex.auriwield.yandextranslate.domain.interactors.IGetDirectionInteractor;
import com.yandex.auriwield.yandextranslate.domain.interactors.IGetSupportedLanguagesInteractor;
import com.yandex.auriwield.yandextranslate.domain.interactors.IReverseDirectionInteractor;
import com.yandex.auriwield.yandextranslate.domain.interactors.ITranslateInteractor;
import com.yandex.auriwield.yandextranslate.domain.interactors.impl.FavoritesInteractor;
import com.yandex.auriwield.yandextranslate.domain.interactors.impl.GetDirectionInteractor;
import com.yandex.auriwield.yandextranslate.domain.interactors.impl.GetSupportedLanguagesInteractor;
import com.yandex.auriwield.yandextranslate.domain.interactors.impl.ReverseDirectionInteractor;
import com.yandex.auriwield.yandextranslate.domain.interactors.impl.TranslateInteractor;
import com.yandex.auriwield.yandextranslate.domain.model.dictionary.DictionaryEntry;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Direction;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Language;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Translation;
import com.yandex.auriwield.yandextranslate.domain.repository.TranslationRepository;
import com.yandex.auriwield.yandextranslate.presentation.presenters.AbstractPresenter;
import com.yandex.auriwield.yandextranslate.presentation.presenters.ITranslatePresenter;

import java.util.List;

/**
 * @author g.zaripov
 */

public class TranslatePresenter extends AbstractPresenter implements ITranslatePresenter,
        ITranslateInteractor.Callback,
        IGetSupportedLanguagesInteractor.Callback,
        IGetDirectionInteractor.Callback,
        IReverseDirectionInteractor.Callback, IFavoritesInteractor.Callback {

    private ITranslatePresenter.View mView;
    private TranslationRepository mTranslateRepository;

    private Direction direction;
    private Translation translation;

    private int type;
    private DictionaryEntry dictionaryEntry;

    public TranslatePresenter(Executor executor, MainThread mainThread,
                              View view, TranslationRepository translateRepository) {
        super(executor, mainThread);
        mView = view;
        mTranslateRepository = translateRepository;
    }

    @Override
    public void resume() {
        getSupportedLanguages();
    }

    @Override
    public void onFavoriteIconClick() {
        IFavoritesInteractor interactor = new FavoritesInteractor(mExecutor, mMainThread,
                mTranslateRepository, this, translation, -1);
        interactor.execute();
    }

    @Override
    public void translate(String word) {
        if (translation != null && translation.getText().equals(word.trim())) return;
        ITranslateInteractor ITranslateInteractor = new TranslateInteractor(
                mExecutor,
                mMainThread,
                word,
                direction,
                mTranslateRepository,
                this
        );
        ITranslateInteractor.execute();
    }

    @Override
    public void getSupportedLanguages() {
        IGetSupportedLanguagesInteractor interactor = new GetSupportedLanguagesInteractor(
                mExecutor,
                mMainThread,
                mTranslateRepository,
                this
        );
        interactor.execute();
    }

    @Override
    public String detectLanguage(String string) {
        return null;
    }

    @Override
    public void onTranslated(Translation translation) {
        this.translation = translation;
        mView.showTranslation(translation);
        mView.updateFavoritesIcon(translation);
    }

    @Override
    public void onTranslated(DictionaryEntry dictionaryEntry) {
        this.dictionaryEntry = dictionaryEntry;
        mView.showDictionaryEntry(dictionaryEntry);
    }

    @Override
    public void onReceiveSupportedLanguages(List<Language> languages) {
        IGetDirectionInteractor i = new GetDirectionInteractor(
                mExecutor,
                mMainThread,
                mTranslateRepository,
                this
        );

        i.execute();
    }

    @Override
    public void onSwapped(Direction direction) {
        this.direction = direction;
        if (translation != null) {
            translate(translation.getTranslation());
            mView.updateEditText(translation.getTranslation());
        }
        mView.updateDirection(direction);
    }

    @Override
    public void userVisible() {
        super.userVisible();
        if (translation != null)
            translate(translation.getText());
    }

    @Override
    public void chooseLanguage(Language.TYPE type) {
        mView.showLanguagesActivity(type);
    }

    @Override
    public void onClear() {
        mView.clearView();
        translation = null;
        dictionaryEntry = null;
    }

    @Override
    public void reverseDirection() {
        IReverseDirectionInteractor interactor = new ReverseDirectionInteractor(
                mExecutor,
                mMainThread,
                mTranslateRepository,
                this
        );
        interactor.execute();
    }

    @Override
    public void onDownloadLanguagesError() {
        //TODO: handle error
    }

    @Override
    public void downloadTranslationError() {
        //TODO: handle error
    }

    @Override
    public void onReceiveDirection(Direction direction) {
        this.direction = direction;
        mView.updateDirection(direction);
    }

    @Override
    public void onReceiveDirectionError() {
        mView.showError(R.string.receive_direction_error);
    }

    @Override
    public void onActionError() {

    }

    @Override
    public void onAdded(Translation translation, int position) {
        this.translation = translation;
        mView.updateFavoritesIcon(translation);
    }

    @Override
    public void onRemoved(Translation translation, int position) {
        this.translation = translation;
        mView.updateFavoritesIcon(translation);
    }
}
