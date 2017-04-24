
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
public class DefinitionPartModel extends BaseModel{

    @PrimaryKey(autoincrement = true)
    private long id;

    @Column
    private String text;

    @Column
    private String pos;

    @Column
    private String gen;

    @ForeignKey(tableClass = DefinitionModel.class)
    private long def;

    public DefinitionPartModel() {
    }

    List<SynonymModel> synonyms = new ArrayList<>();

    public List<SynonymModel> getSynonyms() {
        return synonyms;
    }

    List<MeanModel> means = new ArrayList<>();

    public List<MeanModel> getMeans() {
        return means;
    }

    List<ExampleModel> examples = new ArrayList<>();


    public List<ExampleModel> getExamples() {
        return examples;
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

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDef() {
        return def;
    }

    public void setDef(long def) {
        this.def = def;
    }

    public void setSynonyms(List<SynonymModel> synonyms) {
        this.synonyms = synonyms;
    }

    public void setMeans(List<MeanModel> means) {
        this.means = means;
    }

    public void setExamples(List<ExampleModel> examples) {
        this.examples = examples;
    }

}
