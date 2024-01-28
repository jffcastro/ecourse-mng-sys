package application.base.usermanagement.domain.valueobjects;

import eapli.framework.domain.model.ValueObject;

import java.util.Objects;

public class ShortName implements ValueObject {
    private String value;

    public ShortName(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Short name cannot be empty");
        }

        this.value = value.trim();
    }

    protected ShortName(){}

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShortName shortName = (ShortName) o;
        return Objects.equals(value, shortName.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
