package com.yandex.auriwield.yandextranslate.storage.datasource;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.yandex.auriwield.yandextranslate.BuildConfig;
import com.yandex.auriwield.yandextranslate.storage.model.dictionary.DictionaryEntryModel;
import com.yandex.auriwield.yandextranslate.storage.model.translate.TranslationModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

/**
 * @author g.zaripov
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class CloudStoreTest {

    CloudStore cloudStore;

    @Before
    public void setUp() throws Exception {
        cloudStore = new CloudStore();
        FlowManager.init(new FlowConfig.Builder(RuntimeEnvironment.application.getApplicationContext()).build());
    }

    @Test
    public void getSupportedLanguages() throws Exception {

    }

    @Test
    public void getTranslation() throws Exception {
        String dummyText = "time";
        String dummyDir = "en-ru";
        TranslationModel translation = cloudStore.getTranslation(dummyText, dummyDir);
        assertNotNull(translation);
        assertNotNull(translation.getTranslation());
        assertNotNull(translation.getDirectionModel());
        assertNotNull(translation.getWord());
    }

    @Test
    public void getMeaning() throws Exception {
        String dummyText = "time";
        String dummyDir = "en-ru";

        DictionaryEntryModel meaning = cloudStore.getMeaning(dummyText, dummyDir);

        assertNotNull(meaning);
    }

}