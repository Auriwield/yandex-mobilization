package com.yandex.auriwield.yandextranslate.domain.model.translate;

/**
 * Created by auriw on 30.03.2017.
 */

public class Direction {
    public static final String SEPARATOR = "-";

    private final Language source;
    private final Language target;

    public Direction(Language source, Language target) {
        this.source = source;
        this.target = target;
    }

    public Language getSource() {
        return source;
    }

    public Language getTarget() {
        return target;
    }

    public Direction reverse() {
        return new Direction(target, source);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Direction direction = (Direction) o;

        if (source != null ? !source.equals(direction.source) : direction.source != null)
            return false;
        return target != null ? target.equals(direction.target) : direction.target == null;

    }

    @Override
    public int hashCode() {
        int result = source != null ? source.hashCode() : 0;
        result = 31 * result + (target != null ? target.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        String prefix = source != null ? source.getCode() + SEPARATOR : "";
        return prefix + target.getCode();
    }
}
