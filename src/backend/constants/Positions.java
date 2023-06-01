package backend.constants;

public enum Positions{
    ADMIN("Admin"),
    TEACHER("Teacher"),
    STUDENT("Student");

    private final String value;
    Positions(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
    public static Positions fromValue(String value) {
        for (Positions position : Positions.values()) {
            if (position.getValue().equalsIgnoreCase(value)) {
                return position;
            }
        }
        throw new IllegalArgumentException("Invalid Position value: " + value);
    }
}
