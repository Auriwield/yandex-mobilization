package com.yandex.auriwield.yandextranslate.storage.model.translate;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ManyToMany;
import com.raizlabs.android.dbflow.annotation.NotNull;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.yandex.auriwield.yandextranslate.storage.database.AppDatabase;

/**
 * @author g.zaripov
 */

@ManyToMany(referencedTable = DirectionModel.class)
@Table(database = AppDatabase.class)
public class TranslationModel extends BaseModel {

    @NotNull
    @PrimaryKey
    private String word;

    @NotNull
    @PrimaryKey
    private String translation;

    @NotNull
    @ForeignKey(tableClass = DirectionModel.class)
    private DirectionModel directionModel;

    @Column
    private boolean isFavorite;

    @Column
    private boolean isArchived;

    public TranslationModel() {
    }

    public TranslationModel(String word, String translation, DirectionModel directionModel) {
        this.word = word;
        this.translation = translation;
        this.directionModel = directionModel;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public DirectionModel getDirectionModel() {
        return directionModel;
    }

    public void setDirectionModel(DirectionModel directionModel) {
        this.directionModel = directionModel;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }



    @Override
    public String toString() {
        return "TranslationModel{" +
                "word='" + word + '\'' +
                ", translation='" + translation + '\'' +
                ", directionModel=" + directionModel +
                '}';
    }

    public boolean isArchived() {
        return isArchived;
    }

    public TranslationModel setArchived(boolean archived) {
        isArchived = archived;
        return this;
    }
}
