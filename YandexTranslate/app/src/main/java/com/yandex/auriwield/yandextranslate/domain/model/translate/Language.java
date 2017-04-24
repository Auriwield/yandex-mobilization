package com.yandex.auriwield.yandextranslate.domain.model.translate;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by auriw on 30.03.2017.
 */

public class Language implements Parcelable {

    public enum TYPE {
        SOURCE,
        TARGET
    }

    public static final Language EN = new Language("en", "English");
    public static final Language RU = new Language("ru", "Russian");

    private final String code;
    private final String name;

    public static final Parcelable.Creator<Language> CREATOR = new Parcelable.Creator<Language>() {

        @Override
        public Language createFromParcel(Parcel source) {
            return new Language(source);
        }

        @Override
        public Language[] newArray(int size) {
            return new Language[size];
        }

    };

    private Language(Parcel parcel) {
        code = parcel.readString();
        name = parcel.readString();
    }

    public Language(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(code);
        dest.writeString(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Language language = (Language) o;

        return code != null ? code.equals(language.code) : language.code == null;

    }

    @Override
    public int hashCode() {
        return code != null ? code.hashCode() : 0;
    }
}
