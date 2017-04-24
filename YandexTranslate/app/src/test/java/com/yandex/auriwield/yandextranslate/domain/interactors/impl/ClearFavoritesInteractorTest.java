package com.yandex.auriwield.yandextranslate.domain.interactors.impl;

import com.yandex.auriwield.yandextranslate.domain.interactors.IClearFavoritesInteractor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

/**
 * @author g.zaripov
 */
public class ClearFavoritesInteractorTest extends BaseInteractorTest {
    @Mock
    private IClearFavoritesInteractor.Callback mCallback;

    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void run() throws Exception {
        ClearFavoritesInteractor interactor = new ClearFavoritesInteractor(mExecutor, mMainThread,
                mTranslationRepository, mCallback);
        interactor.run();

        verify(mTranslationRepository).clearFavorites();
        verify(mCallback).onCleared();
        verifyNoMoreInteractions(mTranslationRepository);
        verifyNoMoreInteractions(mCallback);

        reset(mCallback, mTranslationRepository);

        doThrow(new RuntimeException()).when(mTranslationRepository).clearFavorites();

        interactor.run();
        verify(mTranslationRepository).clearFavorites();
        verify(mCallback).onError();
        verifyNoMoreInteractions(mTranslationRepository);
        verifyNoMoreInteractions(mCallback);
    }

}