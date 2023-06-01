package backend;

import java.time.format.DateTimeFormatter;

public class Config {
    public static String usersCSVPath ="src/backend/database/users.csv";
    public static String assignmentsCSVPath ="src/backend/database/assignments.csv";
    public static String submissionsCSVPath ="src/backend/database/submissions.csv";
    public static String feedbackCSVPath ="src/backend/database/feedback.csv";

    public static String meetingsCSVPath ="src/backend/database/meetings.csv";
    public static String testCSVPath ="src/backend/database/test.csv";

    public static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
}
