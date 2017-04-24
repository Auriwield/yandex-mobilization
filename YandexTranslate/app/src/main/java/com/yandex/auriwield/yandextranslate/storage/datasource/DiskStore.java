package com.yandex.auriwield.yandextranslate.storage.datasource;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.yandex.auriwield.yandextranslate.storage.model.dictionary.DefinitionModel;
import com.yandex.auriwield.yandextranslate.storage.model.dictionary.DefinitionPartModel;
import com.yandex.auriwield.yandextranslate.storage.model.dictionary.DictionaryEntryModel;
import com.yandex.auriwield.yandextranslate.storage.model.dictionary.ExampleModel;
import com.yandex.auriwield.yandextranslate.storage.model.dictionary.ExampleTranslationModel;
import com.yandex.auriwield.yandextranslate.storage.model.dictionary.MeanModel;
import com.yandex.auriwield.yandextranslate.storage.model.dictionary.SynonymModel;
import com.yandex.auriwield.yandextranslate.storage.model.translate.DirectionModel;
import com.yandex.auriwield.yandextranslate.storage.model.translate.DirectionModel_Table;
import com.yandex.auriwield.yandextranslate.storage.model.translate.LanguageModel;
import com.yandex.auriwield.yandextranslate.storage.model.translate.LanguageModel_Table;
import com.yandex.auriwield.yandextranslate.storage.model.translate.TranslationModel;
import com.yandex.auriwield.yandextranslate.storage.model.translate.TranslationModel_Table;

import java.util.List;
import java.util.Locale;

public class DiskStore implements Store {

    private static final String FAVORITES_COLUMN_NAME = "isFavorite";
    private static final String HISTORY_COLUMN_NAME = "isArchived";

    @Override
    public List<LanguageModel> getSupportedLanguages() {
        return SQLite.select().from(LanguageModel.class).queryList();
    }


    public LanguageModel getLanguageByCode(String code) {
        return SQLite.select().from(LanguageModel.class).where(LanguageModel_Table.code.eq(code)).querySingle();
    }

    @Override
    public TranslationModel getTranslation(String someWord, String dit) {
        return SQLite.select().from(TranslationModel.class)
                .where(TranslationModel_Table.word.eq(someWord)).querySingle();
    }

    public List<TranslationModel> getArchivedTranslations() {
        return SQLite.select().from(TranslationModel.class).where(TranslationModel_Table.isArchived.eq(true)).queryList();
    }

    public TranslationModel addToFavorites(TranslationModel model) {
        model.setFavorite(true);
        boolean save = FlowManager.getModelAdapter(TranslationModel.class).save(model);
        return save ? model : null;
    }

    public void storeTranslation(TranslationModel translation) {
        translation.setArchived(true);
        saveDirection(translation.getDirectionModel());
        translation.save();
    }

    public void storeMeaning(final DictionaryEntryModel model) {
        new Runnable() {
            @Override
            public void run() {
                storeMeaningAsync(model);
            }
        }.run();
    }

    private void storeMeaningAsync(DictionaryEntryModel model) {
        for (DefinitionModel def : model.getDefinitionModels()) {
            for (DefinitionPartModel defPart : def.getDefinitionPartModels()) {
                for (MeanModel meanModel : defPart.getMeans()) {
                    meanModel.save();
                }
                for (ExampleModel exampleModel : defPart.getExamples()) {
                    for (ExampleTranslationModel tr : exampleModel.getExamples()) {
                        tr.save();
                    }
                    exampleModel.save();
                }
                for (SynonymModel synonymModel : defPart.getSynonyms()) {
                    synonymModel.save();
                }
                defPart.save();
            }
            def.save();
        }
        model.save();
    }

    public void storeLanguages(List<LanguageModel> languageModels) {
        FlowManager.getModelAdapter(LanguageModel.class).saveAll(languageModels);
    }

    public DirectionModel getDirection() {
        return SQLite.select().from(DirectionModel.class).orderBy(DirectionModel_Table.id, false).limit(1).querySingle();
    }

    public void saveDirection(DirectionModel model) {
        model.getSource().save();
        model.getTarget().save();
        model.save();
    }

    @Override
    public DictionaryEntryModel getMeaning(String text, String direction) {
        return null;
    }

    public List<TranslationModel> getFavoriteTranslations() {
        return SQLite.select().from(TranslationModel.class)
                .where(TranslationModel_Table.isFavorite.eq(true)).queryList();
    }

    public TranslationModel removeFromFavorites(TranslationModel t) {
        t.setFavorite(false);
        t.update();
        return t;
    }

    public void clearHistory() {
        String tableName = FlowManager.getTableName(TranslationModel.class);
        FlowManager.getWritableDatabaseForTable(TranslationModel.class).execSQL(
                String.format(Locale.ENGLISH, "Update %s set isArchived = '%d' " +
                        "where isArchived = '%d'",tableName, 0,1 ));
    }

    public void clearFavorites() {
        String tableName = FlowManager.getTableName(TranslationModel.class);
        FlowManager.getWritableDatabaseForTable(TranslationModel.class).execSQL(
                String.format(Locale.ENGLISH, "Update %s set isFavorite = '%d' " +
                        "where isFavorite = '%d'",tableName, 0,1 ));
    }
}
