package fittrack.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Objects;

public class Date implements Comparable<Date> {
    private static final Locale LOCALE = Locale.ENGLISH;
    private final LocalDate localDate;

    public Date(String date) throws DateTimeParseException {
        this.localDate = LocalDate.parse(date);
    }

    public Date(int year, int month, int day) {
        this.localDate = LocalDate.of(year, month, day);
    }

    public Date(LocalDate localDate) {
        this.localDate = localDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Date date = (Date) o;
        return Objects.equals(localDate, date.localDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(localDate);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE.withLocale(LOCALE);
        return localDate.format(formatter);
    }

    @Override
    public int compareTo(Date o) {
        return this.localDate.compareTo(o.localDate);
    }

    public static Date today() {
        return new Date(LocalDate.now());
    }
}
