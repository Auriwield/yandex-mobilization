package com.yandex.auriwield.yandextranslate.domain.interactors.impl;

import com.yandex.auriwield.yandextranslate.TestMainThread;
import com.yandex.auriwield.yandextranslate.domain.executor.Executor;
import com.yandex.auriwield.yandextranslate.domain.executor.MainThread;
import com.yandex.auriwield.yandextranslate.domain.repository.TranslationRepository;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * @author g.zaripov
 */
public abstract class BaseInteractorTest {
    protected MainThread mMainThread;
    @Mock
    protected Executor mExecutor;
    @Mock
    protected TranslationRepository mTranslationRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mMainThread = new TestMainThread();
    }
}
