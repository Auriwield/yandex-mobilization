package com.yandex.auriwield.yandextranslate.presentation.presenters.impl;

import com.yandex.auriwield.yandextranslate.domain.executor.Executor;
import com.yandex.auriwield.yandextranslate.domain.executor.MainThread;
import com.yandex.auriwield.yandextranslate.domain.interactors.IGetDirectionInteractor;
import com.yandex.auriwield.yandextranslate.domain.interactors.IGetSupportedLanguagesInteractor;
import com.yandex.auriwield.yandextranslate.domain.interactors.IUpdateDirectionInteractor;
import com.yandex.auriwield.yandextranslate.domain.interactors.impl.GetDirectionInteractor;
import com.yandex.auriwield.yandextranslate.domain.interactors.impl.GetSupportedLanguagesInteractor;
import com.yandex.auriwield.yandextranslate.domain.interactors.impl.UpdateDirectionInteractor;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Direction;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Language;
import com.yandex.auriwield.yandextranslate.domain.repository.TranslationRepository;
import com.yandex.auriwield.yandextranslate.presentation.presenters.AbstractPresenter;
import com.yandex.auriwield.yandextranslate.presentation.presenters.IChooseLanguagePresenter;

import java.util.List;

/**
 * @author g.zaripov
 */
public class ChooseLanguagePresenter extends AbstractPresenter implements IChooseLanguagePresenter,
        IGetSupportedLanguagesInteractor.Callback,
        IUpdateDirectionInteractor.Callback,
        IGetDirectionInteractor.Callback
{

    private IChooseLanguagePresenter.View mView;
    private TranslationRepository mTranslateRepository;
    private Language.TYPE type;

    public ChooseLanguagePresenter(Executor executor, MainThread mainThread,
                                   IChooseLanguagePresenter.View view, TranslationRepository ranslateRepository) {
        super(executor, mainThread);
        mView = view;
        mTranslateRepository = ranslateRepository;
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
    public void onReceiveSupportedLanguages(List<Language> languages) {
        mView.onReceiveSupportedLanguages(languages);
    }

    @Override
    public void updateDirection(Language.TYPE type, Language language) {
        IUpdateDirectionInteractor interactor = new UpdateDirectionInteractor(
                mExecutor,
                mMainThread,
                type,
                language,
                mTranslateRepository,
                this
        );
        interactor.execute();
    }

    @Override
    public void selectLanguage(Language.TYPE type) {
        IGetDirectionInteractor interactor = new GetDirectionInteractor(
                mExecutor,
                mMainThread,
                mTranslateRepository,
                this
        );

        this.type = type;
        interactor.execute();
    }

    @Override
    public void onSaved() {
        mView.finishActivity();
    }

    @Override
    public void onReceiveDirection(Direction direction) {
        if (type == null || direction == null) return;
        mView.selectLanguage(type == Language.TYPE.SOURCE ? direction.getSource() : direction.getTarget());
    }

    @Override
    public void onReceiveDirectionError() {

    }

    @Override
    public void onSaveDirectionError() {

    }

    @Override
    public void onDownloadLanguagesError() {
        //TODO: implement me
    }

    @Override
    public void resume() {
        getSupportedLanguages();
    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {

    }
}
