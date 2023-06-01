package backend.users;

import backend.constants.MeetingType;
import backend.constants.Positions;
import backend.functionality.Assignment;
import backend.functionality.Feedback;
import backend.functionality.Meeting;
import backend.functionality.Submission;

import java.time.LocalDateTime;

public class Admin extends User {
    public Admin(String first_name, String last_name, String email, String password, Positions position, boolean csv) throws Exception {
        super(first_name, last_name, email, password, position, csv);
    }
    public Assignment createAssignment(String title, String body, String attachment, LocalDateTime deadline) throws Exception {
        return new Assignment(this, title, body, attachment, deadline);
    }
    public Assignment createAssignment(String title, String body, LocalDateTime deadline) throws Exception {
        return new Assignment(this, title, body, deadline);
    }
    public Meeting createMeeting(User creator, String topic, MeetingType type, String info, LocalDateTime date) throws Exception {
        return new Meeting(this, topic, type, info, date);
    }
    public Feedback createFeedback(User creator, Submission submission, double score, String notes) throws Exception {
        return new Feedback(this, submission, score, notes);
    }
}
