package application.base.usermanagement.domain.valueobjects;

import eapli.framework.domain.model.ValueObject;

import java.util.Objects;

public class FullName implements ValueObject {
    private  String firstName;
    private  String lastName;

    public FullName(String firstName, String lastName) {
        if (firstName == null || firstName.trim().isEmpty() ||
                lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name and last name cannot be empty");
        }

        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
    }

    protected FullName(){}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FullName fullName = (FullName) o;
        return Objects.equals(firstName, fullName.firstName) &&
                Objects.equals(lastName, fullName.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
