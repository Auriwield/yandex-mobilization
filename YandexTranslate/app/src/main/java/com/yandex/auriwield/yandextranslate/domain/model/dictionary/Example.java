
package com.yandex.auriwield.yandextranslate.domain.model.dictionary;


import java.util.ArrayList;
import java.util.List;

public class Example {

    private String text;
    private List<String> translation = new ArrayList<>();

    public Example(String text, List<String> translation) {
        this.text = text;
        this.translation = translation;
    }

    public String getText() {
        return text;
    }

    public List<String> getTranslation() {
        return translation;
    }

}
