package com.yandex.auriwield.yandextranslate.presentation.ui.adapter.translate.dictionary;

import com.yandex.auriwield.yandextranslate.domain.model.dictionary.Definition;
import com.yandex.auriwield.yandextranslate.domain.model.dictionary.DefinitionPart;
import com.yandex.auriwield.yandextranslate.domain.model.dictionary.DictionaryEntry;
import com.yandex.auriwield.yandextranslate.domain.model.dictionary.Example;
import com.yandex.auriwield.yandextranslate.presentation.ui.adapter.base.DataBindAdapter;
import com.yandex.auriwield.yandextranslate.presentation.ui.adapter.base.DataBinder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author g.zaripov
 */
public class DictionaryAdapter extends DataBindAdapter {
    private static final String EXCEPTION_MSG = "binder does not exist in adapter";

    private List<Definition> definitions = new ArrayList<>();

    private static final int SPEECH_PART_TYPE = 0;
    private static final int TRANSLATION_TYPE = 1;
    private static final int MEAN_TYPE = 2;
    private static final int EXAMPLE_TYPE = 3;
    private static final int TRANSCRIPTION_TYPE = 4;

    private List<Integer> viewTypesOrder = new ArrayList<>();
    private List<DataBinder> dataBinders = new ArrayList<>();
    private String text = "";
    private String transcription = "";

    public DictionaryAdapter() {
        dataBinders.add(new SpeechPartBinder(this));
        dataBinders.add(new TranslationBinder(this));
        dataBinders.add(new MeanBinder(this));
        dataBinders.add(new ExampleBinder(this));
        dataBinders.add(new TranscriptionBinder(this));
    }

    @Override
    public int getItemViewType(int position) {
        return viewTypesOrder.get(position);
    }

    @Override
    public int getItemCount() {
        return viewTypesOrder.size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends DataBinder> T getDataBinder(int viewType) {
        return (T) dataBinders.get(viewType);
    }

    @Override
    public int getPosition(DataBinder binder, int binderPosition) {
        int viewType = dataBinders.indexOf(binder);
        if (viewType < 0) {
            throw new IllegalStateException(EXCEPTION_MSG);
        }

        int position = binderPosition;
        for (int i = 0; i < viewType; i++) {
            position += dataBinders.get(i).getItemCount();
        }

        return position;
    }

    @Override
    public int getBinderPosition(int position) {
        int dataBinderPosition = -1;
        int itemViewType = getItemViewType(position);
        for (int i = 0; i <= position; i++) {
            Integer type = viewTypesOrder.get(i);
            if (type == itemViewType)
                dataBinderPosition++;
        }
        return dataBinderPosition;
    }

    public void updateData(DictionaryEntry entry) {
        definitions = entry.getDefinitions();
        text = entry.getText();
        transcription = entry.getTranscription();
        viewTypesOrder.clear();
        updateDataBinders();
        notifyDataSetChanged();
    }

    private void updateDataBinders() {
        List<String> speechPartBinderData = new ArrayList<>();
        List<DefinitionPart> translateBinderData = new ArrayList<>();
        List<DefinitionPart> meanBinderData = new ArrayList<>();
        List<Example> exampleBinderData = new ArrayList<>();

        viewTypesOrder.add(TRANSCRIPTION_TYPE);

        for (Definition def : definitions) {
            speechPartBinderData.add(def.getSpeechPart());
            viewTypesOrder.add(SPEECH_PART_TYPE);

            List<DefinitionPart> defParts = def.getDefinitionParts();

            for (int i1 = 0; i1 < defParts.size(); i1++) {
                DefinitionPart defPart = defParts.get(i1);
                defPart.setNumber(i1+1);
                translateBinderData.add(defPart);
                viewTypesOrder.add(TRANSLATION_TYPE);

                if (!defPart.getMeans().isEmpty()) {
                    meanBinderData.add(defPart);
                    viewTypesOrder.add(MEAN_TYPE);
                }

                List<Example> examples = defPart.getExamples();
                if (!examples.isEmpty()) {
                    for (Example example : examples) {
                        if (example.getText() != null && !example.getTranslation().isEmpty()) {
                            exampleBinderData.add(example);
                            viewTypesOrder.add(EXAMPLE_TYPE);
                        }
                    }
                }
            }
        }

        TranscriptionBinder transcriptionBinder = getDataBinder(TRANSCRIPTION_TYPE);
        SpeechPartBinder speechPartBinder = getDataBinder(SPEECH_PART_TYPE);
        TranslationBinder translationBinder = getDataBinder(TRANSLATION_TYPE);
        MeanBinder meanBinder = getDataBinder(MEAN_TYPE);
        ExampleBinder exampleBinder = getDataBinder(EXAMPLE_TYPE);

        transcriptionBinder.setDataSet(text, transcription);
        speechPartBinder.setDataSet(speechPartBinderData);
        translationBinder.setDataSet(translateBinderData);
        meanBinder.setDataSet(meanBinderData);
        exampleBinder.setDataSet(exampleBinderData);
    }

    public void clear() {
        for (DataBinder dataBinder : dataBinders) {
            dataBinder.clear();
            viewTypesOrder.clear();
            notifyDataSetChanged();
        }
    }
}
