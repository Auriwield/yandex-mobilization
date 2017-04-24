
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

@Table(database = AppDatabase.class)
public class DefinitionModel extends BaseModel{

    @PrimaryKey(autoincrement = true)
    private long id;

    @Column
    private String text;
    @Column
    private String pos;
    @Column
    private String ts;

    @ForeignKey(tableClass = DictionaryEntryModel.class)
    private long dic;

    public DefinitionModel() {
    }

    List<DefinitionPartModel> dictionaryTranslations = new ArrayList<>();

    @OneToMany(methods = {OneToMany.Method.ALL}, variableName = "dictionaryTranslations")
    public List<DefinitionPartModel> getDefinitionPartModels() {
        if (dictionaryTranslations == null || dictionaryTranslations.isEmpty()) {
            dictionaryTranslations = SQLite.select()
                    .from(DefinitionPartModel.class)
                    .where(DefinitionPartModel_Table.def_id.eq(id))
                    .queryList();
        }
        return dictionaryTranslations;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDic() {
        return dic;
    }

    public void setDic(long dic) {
        this.dic = dic;
    }

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

    public void setDictionaryTranslations(List<DefinitionPartModel> dictionaryTranslations) {
        this.dictionaryTranslations = dictionaryTranslations;
    }

}
