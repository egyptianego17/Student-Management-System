package backend.functionality;

import backend.Config;
import backend.Utils;
import backend.Validators;
import backend.constants.Positions;
import backend.database.CRUD;
import backend.users.User;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Submission {
    private static int count;
    public static ArrayList<Submission> database = new ArrayList<>();
    final int id;
    final User creator;
    final Assignment assignment;
    private String body;
    private String attachment=null;
    private LocalDateTime created = LocalDateTime.now();

    public Submission(User creator, Assignment assignment, String body, String attachment, LocalDateTime created) throws Exception {
        this.creator = setCreator(creator);
        this.assignment = assignment;
        setBody(body);
        if (!Objects.equals(attachment, "null")){
            setAttachment(attachment);
        }
        this.created = created;

        this.id = count++;
        database.add(this);
    }
    public Submission(User creator, Assignment assignment, String body, String attachment) throws Exception {
        this.creator = setCreator(creator);
        this.assignment = assignment;
        setBody(body);
        setAttachment(attachment);

        this.id = count++;
        database.add(this);
        appendToCSV(this);

    }
    public Submission(User creator, Assignment assignment, String body) throws Exception {
        this.creator = setCreator(creator);
        this.assignment = assignment;
        setBody(body);

        this.id = count++;
        database.add(this);
        appendToCSV(this);
    }
    private User setCreator(User creator) throws Exception {
        Validators.positionValidator(creator.getPosition(), new Positions[]{Positions.ADMIN, Positions.TEACHER});
        return creator;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) throws Exception {
        this.body = Validators.lengthValidator(body, 3);
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) throws Exception {
        this.attachment = Validators.attachmentValidator(attachment);
    }
    public LocalDateTime getCreated() {
        return created;
    }

    public static Submission fetchByID(int id){
        for(Submission submission: database){
            if (Objects.equals(submission.id, id)) {
                return submission;
            }
        }
        return null;
    }

    public static void appendToCSV(Submission submission) throws IOException {
        CRUD.add(createCSVLine(submission), Config.submissionsCSVPath, true);
    }

    public static String createCSVLine(Submission submission){
        return submission.id+ ","+
                submission.creator.id+","+
                submission.assignment.id+","+
                submission.body+","+
                submission.attachment+","+
                Utils.dateToString(submission.created)+"\n";
    }

    public void deleteAssignment(Submission meeting){
        database.remove(meeting);
        meeting=null;
    }

    @Override
    public String toString() {
        return "Submission{" +
                "id=" + id +
                ", creator_id=" + creator.id+'\'' +
                ", assignment_id=" + assignment.id+'\'' +
                ", body='" + body + '\'' +
                ", attachment=" + attachment+ '\'' +
                ", created=" + created +'\'' +
                '}';
    }
}
