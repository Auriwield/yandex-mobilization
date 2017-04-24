package com.yandex.auriwield.yandextranslate.network.model.translate;

import com.google.gson.annotations.SerializedName;

/**
 * Created by auriw on 30.03.2017.
 */

public class NetTranslation {
    @SerializedName("code")
    private String code;
    @SerializedName("lang")
    private String language;
    @SerializedName("text")
    private String[] text;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String[] getText() {
        return text;
    }

    public void setText(String[] text) {
        this.text = text;
    }
}
