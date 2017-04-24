
package com.yandex.auriwield.yandextranslate.network.model.dictionary;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Ex {

    @SerializedName("text")
    private String text;

    @SerializedName("tr")
    private List<ExTr> tr = new ArrayList<ExTr>();

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<ExTr> getTr() {
        return tr;
    }

    public void setTr(List<ExTr> tr) {
        this.tr = tr;
    }

}
