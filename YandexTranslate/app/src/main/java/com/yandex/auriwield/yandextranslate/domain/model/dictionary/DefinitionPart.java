
package com.yandex.auriwield.yandextranslate.domain.model.dictionary;

import java.util.List;

public class DefinitionPart {

    private String text;
    private String pos;
    private String gen;
    private int number;

    private List<Synonym> synonyms;
    private List<String> means;
    private List<Example> examples;

    public DefinitionPart(String text, String pos, String gen,
                          List<Synonym> synonyms,
                          List<String> means,
                          List<Example> examples) {
        this.text = text;
        this.pos = pos;
        this.gen = gen;
        this.synonyms = synonyms;
        this.means = means;
        this.examples = examples;
    }

    public String getText() {
        return text;
    }


    public String   getPos() {
        return pos;
    }


    public String getGen() {
        return gen;
    }

    public List<Synonym> getSynonyms() {
        return synonyms;
    }

    public List<String> getMeans() {
        return means;
    }

    public List<Example> getExamples() {
        return examples;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
