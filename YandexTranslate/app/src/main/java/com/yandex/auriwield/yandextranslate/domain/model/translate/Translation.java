package com.yandex.auriwield.yandextranslate.domain.model.translate;

/**
 * Created by auriw on 30.03.2017.
 */

public class Translation {
    private final String text;

    private final String translation;

    private final Direction direction;

    private final boolean isFavorite;

    public Translation(String text, String translation, Direction direction, boolean isFavorite) {
        this.text = text;
        this.translation = translation;
        this.direction = direction;
        this.isFavorite = isFavorite;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public String getText() {
        return text;
    }

    public String getTranslation() {
        return translation;
    }

    public Direction getDirection() {
        return direction;
    }


}
