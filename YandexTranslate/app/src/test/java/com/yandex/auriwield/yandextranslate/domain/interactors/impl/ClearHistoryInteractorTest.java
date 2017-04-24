package com.yandex.auriwield.yandextranslate.domain.interactors.impl;

import com.yandex.auriwield.yandextranslate.domain.interactors.IClearHistoryInteractor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * @author g.zaripov
 */
public class ClearHistoryInteractorTest extends BaseInteractorTest {

    @Mock
    IClearHistoryInteractor.Callback mCallback;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void run() throws Exception {
        ClearHistoryInteractor interactor = new ClearHistoryInteractor(mExecutor, mMainThread,
                mTranslationRepository, mCallback);
        interactor.run();

        verify(mTranslationRepository).clearHistory();
        verify(mCallback).onCleared();
        verifyNoMoreInteractions(mTranslationRepository);
        verifyNoMoreInteractions(mCallback);

        reset(mCallback, mTranslationRepository);

        doThrow(new RuntimeException()).when(mTranslationRepository).clearHistory();

        interactor.run();
        verify(mTranslationRepository).clearHistory();
        verify(mCallback).onError();
        verifyNoMoreInteractions(mTranslationRepository);
        verifyNoMoreInteractions(mCallback);
    }


}