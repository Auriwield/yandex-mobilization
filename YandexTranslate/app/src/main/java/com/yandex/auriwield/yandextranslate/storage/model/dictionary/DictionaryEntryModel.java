
package com.yandex.auriwield.yandextranslate.storage.model.dictionary;

import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.yandex.auriwield.yandextranslate.storage.database.AppDatabase;

import java.util.ArrayList;
import java.util.List;

@Table(database = AppDatabase.class)
public class DictionaryEntryModel extends BaseModel {

    @PrimaryKey(autoincrement = true)
    private long id;

    public DictionaryEntryModel() {
    }

    List<DefinitionModel> definitionModels = new ArrayList<>();

    @OneToMany(methods = {OneToMany.Method.ALL}, variableName = "definitionModels")
    public List<DefinitionModel> getDefinitionModels() {
        if (definitionModels == null || definitionModels.isEmpty()) {
            definitionModels = SQLite.select()
                    .from(DefinitionModel.class)
                    .where(DefinitionModel_Table.dic_id.eq(id))
                    .queryList();
        }
        return definitionModels;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDefinitionModels(List<DefinitionModel> definitionModels) {
        this.definitionModels = definitionModels;
    }
}
