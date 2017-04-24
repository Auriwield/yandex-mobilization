package com.yandex.auriwield.yandextranslate.network.model.translate;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * Created by GZaripov1 on 29.03.2017.
 */

public class LanguageMap {

    @SerializedName("langs")
    private Map<String, String> mapLangs;

    public Map<String, String> getMapLangs() {
        return mapLangs;
    }

    public void setMapLangs(Map<String, String> mapLangs) {
        this.mapLangs = mapLangs;
    }
}
