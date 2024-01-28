package application.base.usermanagement.domain.valueobjects;

import eapli.framework.domain.model.ValueObject;

import java.util.Objects;

public class Acronym implements ValueObject, Comparable<Acronym> {

    private String acronym;

    public Acronym(String acronym) {
    /*  if (!hasThreeAlphabeticChars(acronym)){
            throw new IllegalArgumentException("Acronym needs to be composed of three alphabetic characters.");
        }*/

        if (hasThreeAlphabeticChars(acronym)) {
            this.acronym = acronym;
        }
    }

    protected Acronym() {
    }

    public static boolean hasThreeAlphabeticChars(String input) {
        // Define a regular expression that matches strings containing exactly 3 alphabetic characters
        String regex = "^[a-zA-Z]{3}$";

        // Check if the input matches the regular expression
        return input.matches(regex);
    }

    @Override
    public String toString() {
        return "acronym: '" + acronym + '\'' ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Acronym acronym1 = (Acronym) o;
        return Objects.equals(acronym, acronym1.acronym);
    }

    @Override
    public int hashCode() {
        return Objects.hash(acronym);
    }

    @Override
    public int compareTo(Acronym o) {
        return this.acronym.compareToIgnoreCase(o.acronym);
    }
}
