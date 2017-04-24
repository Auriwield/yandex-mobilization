package com.yandex.auriwield.yandextranslate.network.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * @author g.zaripov
 */
public class DictionaryLookupBody {
    @SerializedName("key")
    private String key;
    @SerializedName("text")
    private String text;
    @SerializedName("lang")
    private String direction;
    @SerializedName("ui")
    private String ui;

    public String getText() {
        return text;
    }

    public String getDirection() {
        return direction;
    }

    public String getUi() {
        return ui;
    }

    public String getKey() {
        return key;
    }

    public DictionaryLookupBody setText(String text) {
        this.text = text;
        return this;
    }

    public DictionaryLookupBody setDirection(String direction) {
        this.direction = direction;
        return this;
    }

    public DictionaryLookupBody setUi(String ui) {
        this.ui = ui;
        return this;
    }

    public DictionaryLookupBody setKey(String key) {
        this.key = key;
        return this;
    }
}
