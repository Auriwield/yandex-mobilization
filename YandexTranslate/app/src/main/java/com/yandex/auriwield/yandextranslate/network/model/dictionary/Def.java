
package com.yandex.auriwield.yandextranslate.network.model.dictionary;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Def {

    @SerializedName("text")
    private String text;

    @SerializedName("pos")
    private String pos;

    @SerializedName("ts")
    private String ts;

    @SerializedName("tr")
    private List<Tr> tr = new ArrayList<Tr>();

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

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public List<Tr> getTr() {
        return tr;
    }

    public void setTr(List<Tr> tr) {
        this.tr = tr;
    }

}
