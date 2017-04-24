
package com.yandex.auriwield.yandextranslate.network.model.dictionary;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Tr {

    @SerializedName("text")
    private String text;

    @SerializedName("pos")
    private String pos;

    @SerializedName("gen")
    private String gen;

    @SerializedName("syn")
    private List<Syn> syn = new ArrayList<>();

    @SerializedName("mean")
    private List<NetMean> netMean = new ArrayList<>();

    @SerializedName("ex")
    private List<Ex> ex = new ArrayList<>();

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

    public List<Syn> getSyn() {
        return syn;
    }

    public void setSyn(List<Syn> syn) {
        this.syn = syn;
    }

    public List<NetMean> getNetMean() {
        return netMean;
    }

    public void setNetMean(List<NetMean> netMean) {
        this.netMean = netMean;
    }

    public List<Ex> getEx() {
        return ex;
    }

    public void setEx(List<Ex> ex) {
        this.ex = ex;
    }

}
