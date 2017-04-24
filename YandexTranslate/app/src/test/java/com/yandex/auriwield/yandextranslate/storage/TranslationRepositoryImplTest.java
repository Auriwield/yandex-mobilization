package com.yandex.auriwield.yandextranslate.storage;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.yandex.auriwield.yandextranslate.BuildConfig;
import com.yandex.auriwield.yandextranslate.domain.model.dictionary.DictionaryEntry;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Direction;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Language;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;

/**
 * @author g.zaripov
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class TranslationRepositoryImplTest {
    private TranslationRepositoryImpl translationRepository;

    @Before
    public void setUp() throws Exception {
        translationRepository = new TranslationRepositoryImpl(null);
        FlowManager.init(new FlowConfig.Builder(RuntimeEnvironment.application.getApplicationContext()).build());
    }

    @Test
    public void getMeaning() throws Exception {

        String dummyText = "time";
        Language dummySourceLanguage = Language.EN;
        Language dummyTargetLanguage = Language.RU;
        Direction direction = new Direction(dummySourceLanguage, dummyTargetLanguage);

        DictionaryEntry meaning = translationRepository.getMeaning(dummyText, direction);
        assertNotNull(meaning);
    }

}