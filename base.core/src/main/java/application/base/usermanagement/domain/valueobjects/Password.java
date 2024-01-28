package application.base.usermanagement.domain.valueobjects;

import eapli.framework.domain.model.ValueObject;

import java.util.Objects;
import java.util.regex.Pattern;

public class Password implements ValueObject {
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    private String value;

    public Password(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        if (!Pattern.matches(PASSWORD_REGEX, value)) {
            throw new IllegalArgumentException("Password must contain at least one digit, one lowercase letter, one uppercase letter, one special character, no whitespace, and be at least 8 characters long");
        }

        this.value = value.trim();
    }

    protected Password(){
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "*****";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password = (Password) o;
        return Objects.equals(value, password.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
