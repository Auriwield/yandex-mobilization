package com.yandex.auriwield.yandextranslate.domain.interactors.impl;

import com.yandex.auriwield.yandextranslate.domain.interactors.IFavoritesInteractor;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Direction;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Language;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Translation;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * @author g.zaripov
 */
public class FavoritesInteractorTest extends BaseInteractorTest {

    @Mock
    private IFavoritesInteractor.Callback mCallback;

    private int position = 0;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void run() throws Exception {
        Language dummySourceLanguage = Language.EN;
        Language dummyTargetLanguage = Language.RU;

        Direction dummyDirection = new Direction(dummySourceLanguage, dummyTargetLanguage);

        Translation dummyTranslationTrue = new Translation("time", "Время", dummyDirection, true);
        Translation dummyTranslationFalse = new Translation("time", "Время", dummyDirection, true);

        when(mTranslationRepository.removeFromFavorites(dummyTranslationTrue)).thenReturn(dummyTranslationFalse);

        FavoritesInteractor iFavoritesInteractor = new FavoritesInteractor(mExecutor, mMainThread,
                mTranslationRepository, mCallback, dummyTranslationTrue, position);

        iFavoritesInteractor.run();

        verify(mCallback).onRemoved(dummyTranslationFalse, position);
        verify(mTranslationRepository).removeFromFavorites(dummyTranslationTrue);
        verifyNoMoreInteractions(mCallback);
        verifyNoMoreInteractions(mTranslationRepository);


    }

}