package com.yandex.auriwield.yandextranslate.storage.model.translate;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ManyToMany;
import com.raizlabs.android.dbflow.annotation.NotNull;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.yandex.auriwield.yandextranslate.storage.database.AppDatabase;

/**
 * Created by GZaripov1 on 29.03.2017.
 */

@Table(database = AppDatabase.class)
public class LanguageModel extends BaseModel {

    @NotNull
    @PrimaryKey
    private String code;

    @NotNull
    @Column
    private String name;

    public LanguageModel() {
    }

    public LanguageModel(String code,String name) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
