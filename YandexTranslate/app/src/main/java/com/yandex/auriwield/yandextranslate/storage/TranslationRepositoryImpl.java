package com.yandex.auriwield.yandextranslate.storage;

import android.content.Context;

import com.yandex.auriwield.yandextranslate.domain.model.dictionary.DictionaryEntry;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Direction;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Language;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Translation;
import com.yandex.auriwield.yandextranslate.domain.repository.TranslationRepository;
import com.yandex.auriwield.yandextranslate.storage.converters.DictionaryConverter;
import com.yandex.auriwield.yandextranslate.storage.converters.TranslationConverter;
import com.yandex.auriwield.yandextranslate.storage.datasource.CloudStore;
import com.yandex.auriwield.yandextranslate.storage.datasource.DiskStore;
import com.yandex.auriwield.yandextranslate.storage.model.dictionary.DictionaryEntryModel;
import com.yandex.auriwield.yandextranslate.storage.model.translate.DirectionModel;
import com.yandex.auriwield.yandextranslate.storage.model.translate.LanguageModel;
import com.yandex.auriwield.yandextranslate.storage.model.translate.TranslationModel;

import java.util.List;

import timber.log.Timber;

/**
 * Created by auriw on 26.03.2017.
 */

    //Db is on outer layer s
public class TranslationRepositoryImpl implements TranslationRepository {
    private DiskStore diskStore;
    private CloudStore cloudStore;
    private TranslationConverter translationConverter;
    private DictionaryConverter dictionaryConverter;


    public TranslationRepositoryImpl(Context ctx) {
        Timber.d(getClass().getSimpleName() + "repository constructor! ctx null? " + (ctx == null)); //TODO: delete
        diskStore = new DiskStore();
        cloudStore = new CloudStore();
        translationConverter = new TranslationConverter();
        dictionaryConverter = new DictionaryConverter();
    }

    @Override
    public List<Language> getSupportedLanguages() {
        List<LanguageModel> languages = diskStore.getSupportedLanguages();
        if (languages == null || languages.isEmpty()) {
            languages = cloudStore.getSupportedLanguages();
            diskStore.storeLanguages(languages);
        }
        return translationConverter.convertToDomainModel(languages);
    }

    @Override
    public Translation getTranslation(String someWord, Direction direction) {
        String direction1 = direction.toString();
        TranslationModel translation = diskStore.getTranslation(someWord, direction1);
        if (!checkTranslation(translation)) {
            translation = cloudStore.getTranslation(someWord, direction1);
            diskStore.storeTranslation(translation);
        }
        return translationConverter.convertToDomainModel(translation);
    }

    @Override
    public DictionaryEntry getMeaning(String someWord, Direction direction) {
        DictionaryEntryModel wordModel = diskStore.getMeaning(someWord, direction.toString());
        if (wordModel == null
                || wordModel.getDefinitionModels() == null
                || wordModel.getDefinitionModels().isEmpty()) {
            wordModel = cloudStore.getMeaning(someWord, direction.toString());
            //diskStore.storeMeaning(wordModel);
        }
        return dictionaryConverter.convertToDomainModel(wordModel);
    }

    @Override
    public Translation addToFavorites(Translation translation) {
        TranslationModel tr = diskStore.getTranslation(translation.getText(),
                translation.getDirection().toString());
        TranslationModel translationModel = diskStore.addToFavorites(tr);
        if (translationModel == null) return null;
        return translationConverter.convertToDomainModel(translationModel);
    }

    @Override
    public Translation removeFromFavorites(Translation translation) {
        TranslationModel t = diskStore.getTranslation(translation.getText(),
                translation.getDirection().toString());
        TranslationModel translationModel = diskStore.removeFromFavorites(t);
        if (t == null) return null;
        return translationConverter.convertToDomainModel(diskStore.removeFromFavorites(translationModel));
    }

    @Override
    public List<Translation> getFavoriteTranslations() {
        return translationConverter.convertToDomainModelList(diskStore.getFavoriteTranslations());
    }

    @Override
    public void clearHistory() {
        diskStore.clearHistory();
    }

    @Override
    public void clearFavorites() {
        diskStore.clearFavorites();
    }

    @Override
    public List<Translation> getArchivedTranslations() {
        return translationConverter.convertToDomainModelList(diskStore.getArchivedTranslations());
    }

    @Override
    public Direction getLastDirection() {
        DirectionModel d = diskStore.getDirection();

        return checkDirection(d) ?  translationConverter.convertToDomainModel(d) : null;
    }

    @Override
    public void saveDirection(Direction direction) {
        diskStore.saveDirection(translationConverter.convertToStorageModel(direction));
    }

    private boolean checkDirection(DirectionModel direction) {
        return direction != null && direction.getSource() != null && direction.getTarget() != null;
    }

    private boolean checkTranslation(TranslationModel translationModel) {
        return translationModel != null && translationModel.getWord() != null
                && checkDirection(translationModel.getDirectionModel());
    }

    @Override
    public Language getLanguageByCode(String code) {
        LanguageModel l = diskStore.getLanguageByCode(code);
        return l == null ? null : translationConverter.convertToDomainModel(l);
    }
}
