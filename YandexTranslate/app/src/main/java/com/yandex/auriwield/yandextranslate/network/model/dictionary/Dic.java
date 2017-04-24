
package com.yandex.auriwield.yandextranslate.network.model.dictionary;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Dic {

    @SerializedName("def")
    private List<Def> def = new ArrayList<>();

    public List<Def> getDef() {
        return def;
    }

    public void setDef(List<Def> def) {
        this.def = def;
    }

}
