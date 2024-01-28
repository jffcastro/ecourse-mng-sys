
package application.base.usermanagement.domain.valueobjects;

import eapli.framework.domain.model.ValueObject;

import java.util.Objects;
import java.util.regex.Pattern;

public class Email implements ValueObject {
    private String email;

    public Email(String email) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email address: " + email);
        }
        this.email = email;
    }

    protected Email(){}
    public static boolean isValidEmail(String email) {
        // Regular expression to match a valid email address
        Pattern emailPattern = Pattern.compile("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,}$");

        // Check if the email matches the regular expression
        return emailPattern.matcher(email).matches();
    }

    @Override
    public String toString() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email1 = (Email) o;
        return Objects.equals(email, email1.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
