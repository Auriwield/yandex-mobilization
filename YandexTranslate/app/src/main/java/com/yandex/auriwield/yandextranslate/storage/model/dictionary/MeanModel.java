
package com.yandex.auriwield.yandextranslate.storage.model.dictionary;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.yandex.auriwield.yandextranslate.storage.database.AppDatabase;

@Table(database = AppDatabase.class)
public class MeanModel extends BaseModel {

    @PrimaryKey(autoincrement = true)
    private long id;

    @Column
    private String text;

    @ForeignKey(tableClass = DefinitionPartModel.class)
    private long dic;

    public MeanModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getDic() {
        return dic;
    }

    public void setDic(long dic) {
        this.dic = dic;
    }
}
