package com.yandex.auriwield.yandextranslate.domain.interactors.impl;

import com.yandex.auriwield.yandextranslate.domain.interactors.ITranslateInteractor;
import com.yandex.auriwield.yandextranslate.domain.model.dictionary.DictionaryEntry;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Direction;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Language;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Translation;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

/**
 * @author g.zaripov
 */
public class TranslateInteractorTest extends BaseInteractorTest {

    @Mock
    private ITranslateInteractor.Callback mCallback;

    @Mock
    private DictionaryEntry entry;

    private String word;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        word = "time";
    }

    @Test
    public void run() throws Exception {

        Language dummySourceLanguage = Language.EN;
        Language dummyTargetLanguage = Language.RU;

        Direction dummyDirection = new Direction(dummySourceLanguage, dummyTargetLanguage);

        Translation dummyTranslation = new Translation(word, "Время", dummyDirection, false);

        when(mTranslationRepository.getTranslation(word, dummyDirection)).thenReturn(dummyTranslation);
        when(mTranslationRepository.getMeaning(word, dummyDirection)).thenReturn(null);

        TranslateInteractor interactor = new TranslateInteractor(mExecutor, mMainThread,
                word, dummyDirection, mTranslationRepository, mCallback);

        interactor.run();

        Mockito.verify(mTranslationRepository).getTranslation(word, dummyDirection);
        Mockito.verify(mTranslationRepository).getMeaning(word, dummyDirection);
        Mockito.verify(mCallback).onTranslated(dummyTranslation);
        Mockito.verify(mCallback, never()).downloadTranslationError();
        Mockito.verifyNoMoreInteractions(mTranslationRepository);


        Mockito.reset(mTranslationRepository, mCallback);


        when(mTranslationRepository.getTranslation(word, dummyDirection)).thenReturn(null);
        when(mTranslationRepository.getMeaning(word, dummyDirection)).thenReturn(null);

        interactor.run();

        Mockito.verify(mTranslationRepository).getTranslation(word, dummyDirection);
        Mockito.verify(mTranslationRepository, never()).getMeaning(word, dummyDirection);
        Mockito.verify(mCallback, never()).onTranslated(entry);
        Mockito.verify(mCallback).downloadTranslationError();
        Mockito.verifyNoMoreInteractions(mTranslationRepository);
    }

}