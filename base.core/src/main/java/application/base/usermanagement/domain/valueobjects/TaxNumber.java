package application.base.usermanagement.domain.valueobjects;

import eapli.framework.domain.model.ValueObject;

import java.util.Objects;

public class TaxNumber implements ValueObject {
    private static final int TAX_NUMBER_LENGTH = 9;
    private String value;

    public TaxNumber(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Tax number cannot be empty");
        }

        if (value.length() != TAX_NUMBER_LENGTH) {
            throw new IllegalArgumentException("Tax number must have " + TAX_NUMBER_LENGTH + " digits");
        }

        try {
            Long.parseLong(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Tax number must only contain digits");
        }

        this.value = value.trim();
    }

    protected TaxNumber(){

    }

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
        TaxNumber taxNumber = (TaxNumber) o;
        return Objects.equals(value, taxNumber.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
