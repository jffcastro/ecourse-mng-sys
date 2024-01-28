package application.base.usermanagement.domain.valueobjects;

import eapli.framework.domain.model.ValueObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class BirthDate implements ValueObject, Comparable<BirthDate> {

    private LocalDate date;

    public BirthDate(LocalDate date) {
        if (!isValidDate(date)){
            throw new IllegalArgumentException("Date cannot be null, the year cannot be lower than 1900 and the date cannot be in the future.");
        }
        this.date = date;
    }

    protected BirthDate(){}

    public static boolean isValidDate(LocalDate date) {
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Check if the date is not null and is after 1900-01-01
        if (date == null || date.isBefore(LocalDate.of(1900, 1, 1))) {
            return false;
        }

        // Check if the date is before or equal to the current date
        return !date.isAfter(currentDate);
    }


    public static BirthDate valueOf(String dateString) {
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return new BirthDate(date);
    }

    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BirthDate birthDate = (BirthDate) o;
        return Objects.equals(date, birthDate.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }

    @Override
    public int compareTo(BirthDate o) {
        return this.date.compareTo(o.date);
    }
}
