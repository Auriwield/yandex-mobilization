
package com.yandex.auriwield.yandextranslate.network.model.dictionary;

import com.google.gson.annotations.SerializedName;

public class ExTr {

    @SerializedName("text")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
