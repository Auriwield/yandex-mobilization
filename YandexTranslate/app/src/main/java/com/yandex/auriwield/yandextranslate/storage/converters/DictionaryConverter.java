package com.yandex.auriwield.yandextranslate.storage.converters;

import com.yandex.auriwield.yandextranslate.domain.model.dictionary.Definition;
import com.yandex.auriwield.yandextranslate.domain.model.dictionary.DefinitionPart;
import com.yandex.auriwield.yandextranslate.domain.model.dictionary.Example;
import com.yandex.auriwield.yandextranslate.domain.model.dictionary.Synonym;
import com.yandex.auriwield.yandextranslate.domain.model.dictionary.DictionaryEntry;
import com.yandex.auriwield.yandextranslate.network.model.dictionary.Def;
import com.yandex.auriwield.yandextranslate.network.model.dictionary.Dic;
import com.yandex.auriwield.yandextranslate.network.model.dictionary.Ex;
import com.yandex.auriwield.yandextranslate.network.model.dictionary.ExTr;
import com.yandex.auriwield.yandextranslate.network.model.dictionary.NetMean;
import com.yandex.auriwield.yandextranslate.network.model.dictionary.Syn;
import com.yandex.auriwield.yandextranslate.network.model.dictionary.Tr;
import com.yandex.auriwield.yandextranslate.storage.model.dictionary.DefinitionModel;
import com.yandex.auriwield.yandextranslate.storage.model.dictionary.DefinitionPartModel;
import com.yandex.auriwield.yandextranslate.storage.model.dictionary.DictionaryEntryModel;
import com.yandex.auriwield.yandextranslate.storage.model.dictionary.ExampleTranslationModel;
import com.yandex.auriwield.yandextranslate.storage.model.dictionary.ExampleModel;
import com.yandex.auriwield.yandextranslate.storage.model.dictionary.MeanModel;
import com.yandex.auriwield.yandextranslate.storage.model.dictionary.SynonymModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author g.zaripov
 */
public class DictionaryConverter {
    public DictionaryEntryModel convertToStorageModel(Dic dic) {
        DictionaryEntryModel dm = new DictionaryEntryModel();
        List<DefinitionModel> defModels = new ArrayList<>(dic.getDef().size());
        for (Def def : dic.getDef()) {
            defModels.add(convertToStorageModel(def));
        }
        dm.setDefinitionModels(defModels);
        return dm;
    }

    public DefinitionModel convertToStorageModel(Def def) {
        DefinitionModel dm = new DefinitionModel();
        dm.setText(def.getText());
        dm.setPos(def.getPos());
        dm.setTs(def.getTs());

        List<DefinitionPartModel> dtModels = new ArrayList<>(def.getTr().size());

        for (Tr tr : def.getTr()) {
            dtModels.add(convertToStorageModel(tr));
        }

        dm.setDictionaryTranslations(dtModels);
        return dm;
    }

    public DefinitionPartModel convertToStorageModel(Tr tr) {
        DefinitionPartModel model = new DefinitionPartModel();
        model.setText(tr.getText());
        model.setPos(tr.getPos());
        model.setGen(tr.getGen());
        List<SynonymModel> synonyms = new ArrayList<>(tr.getSyn().size());
        List<MeanModel> means = new ArrayList<>(tr.getNetMean().size());
        List<ExampleModel> examples = new ArrayList<>(tr.getEx().size());

        for (Syn syn : tr.getSyn())
            synonyms.add(convertToStorageModel(syn));

        for (NetMean netMean : tr.getNetMean())
            means.add(convertToStorageModel(netMean));

        for (Ex ex : tr.getEx())
            examples.add(convertToStorageModel(ex));

        model.setSynonyms(synonyms);
        model.setMeans(means);
        model.setExamples(examples);

        return model;
    }

    public SynonymModel convertToStorageModel(Syn syn) {
        SynonymModel model = new SynonymModel();
        model.setText(syn.getText());
        model.setPos(syn.getPos());
        model.setGen(syn.getGen());
        return model;
    }

    public MeanModel convertToStorageModel(NetMean netMean) {
        MeanModel model = new MeanModel();
        model.setText(netMean.getText());
        return model;
    }

    public ExampleModel convertToStorageModel(Ex ex) {
        List<ExampleTranslationModel> trs = new ArrayList<>();
        for (ExTr exTr : ex.getTr()) {
            trs.add(convertToStorageModel(exTr));
        }

        ExampleModel model = new ExampleModel();
        model.setText(ex.getText());

        model.setTrs(trs);
        return model;
    }

    private ExampleTranslationModel convertToStorageModel(ExTr exTr) {
        ExampleTranslationModel model = new ExampleTranslationModel();
        model.setTranslation(exTr.getText());
        return model;
    }

    public DictionaryEntry convertToDomainModel(DictionaryEntryModel model) {
        List<Definition> definitions = new ArrayList<>(model.getDefinitionModels().size());

        for (DefinitionModel defModel : model.getDefinitionModels())
            definitions.add(convertToDomainModel(defModel));

        if (!model.getDefinitionModels().isEmpty()) {
            DefinitionModel def = model.getDefinitionModels().get(0);
            if (def.getText() == null) def.setText("");
            if (def.getTs() == null) def.setTs("");
            return new DictionaryEntry(def.getText(), def.getTs(), definitions);
        }
        return null;
    }

    public Definition convertToDomainModel(DefinitionModel model) {
        List<DefinitionPart> defParts = new ArrayList<>(model.getDefinitionPartModels().size());

        for (DefinitionPartModel defModel : model.getDefinitionPartModels())
            defParts.add(convertToDomainModel(defModel));

        return new Definition(model.getPos(), defParts);
    }

    private DefinitionPart convertToDomainModel(DefinitionPartModel model) {
        List<Synonym> synonyms = new ArrayList<>(model.getSynonyms().size());
        List<String> means = new ArrayList<>(model.getMeans().size());
        List<Example> examples = new ArrayList<>(model.getExamples().size());

        for (SynonymModel synonymModel : model.getSynonyms())
            synonyms.add(convertToDomainModel(synonymModel));

        for (MeanModel meanModel : model.getMeans())
            means.add(meanModel.getText());

        for (ExampleModel exampleModel : model.getExamples())
            examples.add(convertToDomainModel(exampleModel));

        return new DefinitionPart(
                model.getText(),
                model.getPos(),
                model.getGen(),
                synonyms,
                means,
                examples
        );
    }

    private Synonym convertToDomainModel(SynonymModel synonymModel) {
        return new Synonym(
                synonymModel.getText(),
                synonymModel.getPos(),
                synonymModel.getGen()
        );
    }


    private Example convertToDomainModel(ExampleModel exampleModel) {
        List<String> translations = new ArrayList<>(exampleModel.getExamples().size());
        for (ExampleTranslationModel exampleTranslationModel : exampleModel.getExamples())
            translations.add(exampleTranslationModel.getTranslation());

        return new Example(
                exampleModel.getText(),
                translations
        );
    }

}
