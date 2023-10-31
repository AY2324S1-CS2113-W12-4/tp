package fittrack.data;

public class Gender {
    private char gender;

    public Gender(char gender) {
        this.gender = gender;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return gender == 'M' ? "Male" : "Female";
    }
}
