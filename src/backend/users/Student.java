package backend.users;

import backend.constants.Positions;
import backend.functionality.Assignment;
import backend.functionality.Submission;

public class Student extends User {
    public Student(String first_name, String last_name, String email, String password, Positions position, boolean csv) throws Exception {
        super(first_name, last_name, email, password, position, csv);
    }
    public Submission createSubmission(User creator, Assignment assignment, String body, String attachment) throws Exception {
        return new Submission(creator, assignment, body, attachment);
    }
    public Submission createSubmission(User creator, Assignment assignment, String body) throws Exception {
        return new Submission(creator, assignment, body);
    }
}
