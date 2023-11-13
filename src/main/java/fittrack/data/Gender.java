package fittrack.data;

import fittrack.parser.IllegalValueException;

// @@author marklin2234
public enum Gender {
    MALE ("Male"),
    FEMALE ("Female");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return gender;
    }

    public static Gender parseGender(String s) throws IllegalValueException {
        assert s != null;
        String gender = s.strip();

        switch (gender.toUpperCase().charAt(0)) {
        case 'M':
            return MALE;
        case 'F':
            return FEMALE;
        default:
            throw new IllegalValueException("Gender must be either M or F.");
        }
    }
}
// @@author
