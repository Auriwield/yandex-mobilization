
package com.yandex.auriwield.yandextranslate.domain.model.dictionary;

public class Synonym {

    private String text;
    private String pos;
    private String gen;

    public Synonym(String text, String pos, String gen) {
        this.text = text;
        this.pos = pos;
        this.gen = gen;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

}
