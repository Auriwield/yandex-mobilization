
package com.yandex.auriwield.yandextranslate.storage.model.dictionary;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.yandex.auriwield.yandextranslate.storage.database.AppDatabase;

@Table(database = AppDatabase.class)
public class ExampleTranslationModel extends BaseModel {

    @PrimaryKey(autoincrement = true)
    private long id;

    @Column
    private String translation;

    @ForeignKey(tableClass = ExampleModel.class)
    private long example;

    public ExampleTranslationModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public long getExample() {
        return example;
    }

    public ExampleTranslationModel setExample(long example) {
        this.example = example;
        return this;
    }
}
