package com.aayyad.uniticker;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
public class Entity {
    @Getter
    private String key;
    @Getter
    private String user;
    @Getter
    private String operation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return key.equals(entity.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
