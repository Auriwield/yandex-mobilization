
package com.yandex.auriwield.yandextranslate.storage.model.dictionary;


import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.yandex.auriwield.yandextranslate.storage.database.AppDatabase;

import java.util.ArrayList;
import java.util.List;
//TODO: remove example tr model by string
@Table(database = AppDatabase.class)
public class ExampleModel extends BaseModel {

    @PrimaryKey(autoincrement = true)
    private long id;

    @Column
    private String text;

    @ForeignKey(tableClass = DefinitionPartModel.class)
    private long dic;

    public ExampleModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    List<ExampleTranslationModel> trs = new ArrayList<>();

    @OneToMany(methods = {OneToMany.Method.ALL}, variableName = "trs")
    public List<ExampleTranslationModel> getExamples() {
        if (trs == null || trs.isEmpty()) {
            trs = SQLite.select()
                    .from(ExampleTranslationModel.class)
                    .where(ExampleTranslationModel_Table.example_id.eq(id))
                    .queryList();
        }
        return trs;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTrs(List<ExampleTranslationModel> trs) {
        this.trs = trs;
    }

    public long getDic() {
        return dic;
    }

    public void setDic(long dic) {
        this.dic = dic;
    }
}
