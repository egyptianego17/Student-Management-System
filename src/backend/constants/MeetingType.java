package backend.constants;

public enum MeetingType {
    ONLINE("Online"),
    OFFLINE("Teacher");
    private final String value;

    MeetingType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static MeetingType fromValue(String value) {
        for (MeetingType type : MeetingType.values()) {
            if (type.getValue().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid meeting value: " + value);
    }

}
