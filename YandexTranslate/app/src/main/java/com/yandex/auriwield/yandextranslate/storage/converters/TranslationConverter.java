package com.yandex.auriwield.yandextranslate.storage.converters;

import com.yandex.auriwield.yandextranslate.domain.model.translate.Direction;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Language;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Translation;
import com.yandex.auriwield.yandextranslate.network.model.translate.LanguageMap;
import com.yandex.auriwield.yandextranslate.network.model.translate.NetTranslation;
import com.yandex.auriwield.yandextranslate.storage.datasource.DiskStore;
import com.yandex.auriwield.yandextranslate.storage.model.translate.DirectionModel;
import com.yandex.auriwield.yandextranslate.storage.model.translate.LanguageModel;
import com.yandex.auriwield.yandextranslate.storage.model.translate.TranslationModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author g.zaripov
 */

public class TranslationConverter {

    public TranslationModel convertToStorageModel(String word, NetTranslation translation) {
        String language = translation.getLanguage();
        String[] langs = language.split(Direction.SEPARATOR);

        DiskStore store = new DiskStore();

        LanguageModel source = store.getLanguageByCode(langs[0]);
        LanguageModel target = store.getLanguageByCode(langs[1]);

        return new TranslationModel(
                word,
                translation.getText()[0],
                new DirectionModel(source, target)
        );
    }

    public List<LanguageModel> convertToStorageModel(LanguageMap map) {
        Map<String, String> mapLangs = map.getMapLangs();
        List<LanguageModel> list = new ArrayList<>(mapLangs.size());
        for (Map.Entry<String, String> e : mapLangs.entrySet()) {
            list.add(new LanguageModel(e.getKey(), e.getValue()));
        }
        return list;
    }


    public Translation convertToDomainModel(TranslationModel model) {
        Direction direction = convertToDomainModel(model.getDirectionModel());
        return new Translation(model.getWord(), model.getTranslation(), direction, model.isFavorite());
    }

    public Direction convertToDomainModel(DirectionModel model) {
        return new Direction(
                convertToDomainModel(model.getSource()),
                convertToDomainModel(model.getTarget())
        );
    }

    public List<Translation> convertToDomainModelList(List<TranslationModel> modelList) {
        List<Translation> translations = new ArrayList<>(modelList.size());
        for (TranslationModel translationModel : modelList) {
            translations.add(convertToDomainModel(translationModel));
        }
        return translations;
    }

    public Language convertToDomainModel(LanguageModel model) {
        return new Language(model.getCode(), model.getName());
    }

    public List<Language> convertToDomainModel(List<LanguageModel> modelList) {
        List<Language> list = new ArrayList<>(modelList.size());
        for (LanguageModel languageModel : modelList) {
            list.add(convertToDomainModel(languageModel));
        }
        return list;
    }

    public LanguageModel convertToStorageModel(Language language) {
        return new LanguageModel(language.getCode(), language.getName());
    }

    public DirectionModel convertToStorageModel(Direction direction) {
        LanguageModel source = convertToStorageModel(direction.getSource());
        LanguageModel target = convertToStorageModel(direction.getTarget());
        return new DirectionModel(source, target);
    }

}
