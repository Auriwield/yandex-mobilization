
package com.yandex.auriwield.yandextranslate.domain.model.dictionary;


import java.util.ArrayList;
import java.util.List;


public class DictionaryEntry {
    private String text;
    private String transcription;
    private List<Definition> definitions = new ArrayList<>();

    public DictionaryEntry(String text, String transcription, List<Definition> definitions) {
        this.text = text;
        this.transcription = transcription;
        this.definitions = definitions;
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }

    public String getText() {
        return text;
    }


    public String getTranscription() {
        return transcription;
    }
}
