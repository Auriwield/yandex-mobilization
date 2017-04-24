package com.yandex.auriwield.yandextranslate.storage.model.translate;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ManyToMany;
import com.raizlabs.android.dbflow.annotation.NotNull;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.yandex.auriwield.yandextranslate.storage.database.AppDatabase;

/**
 * @author g.zaripov
 */

@Table(database = AppDatabase.class)
@ManyToMany(referencedTable = LanguageModel.class)
public class DirectionModel extends BaseModel {

    @PrimaryKey(autoincrement = true)
    private long id;

    @NotNull
    @ForeignKey(tableClass = LanguageModel.class)
    private LanguageModel source;

    @NotNull
    @ForeignKey(tableClass = LanguageModel.class)
    private LanguageModel target;

    @Column
    private int timesUsed;

    public DirectionModel() {
    }

    public DirectionModel(LanguageModel source, LanguageModel target) {
        this.source = source;
        this.target = target;
    }

    @Override
    public boolean update() {
        FlowManager.getModelAdapter(DirectionModel.class).delete(this);
        timesUsed++;
        return FlowManager.getModelAdapter(DirectionModel.class).insert(this) > 0;
    }

    public LanguageModel getSource() {
        return source;
    }

    public void setSource(LanguageModel source) {
        this.source = source;
    }

    public LanguageModel getTarget() {
        return target;
    }

    public void setTarget(LanguageModel target) {
        this.target = target;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTimesUsed() {
        return timesUsed;
    }

    public void setTimesUsed(int timesUsed) {
        this.timesUsed = timesUsed;
    }
}
