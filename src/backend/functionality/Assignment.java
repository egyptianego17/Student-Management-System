package backend.functionality;

import backend.Config;
import backend.Utils;
import backend.constants.Positions;
import backend.Validators;
import backend.database.CRUD;
import backend.users.User;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Assignment{
    private static int count;
    public static ArrayList<Assignment> database = new ArrayList<>();
    final int id;
    public final User creator;
    private String title;
    private String body;
    private String attachment=null;
    private LocalDateTime deadline;
    private LocalDateTime created = LocalDateTime.now();

    public Assignment(User creator, String title, String body, String attachment, LocalDateTime deadline, LocalDateTime created) throws Exception {
        this.creator = this.setCreator(creator);
        setTitle(title);
        setBody(body);
        if (!Objects.equals(attachment, "null")){
            setAttachment(attachment);
        }
        setDeadline(deadline);
        this.created =created;

        this.id = count++;
        database.add(this);
    }
    public Assignment(User creator, String title, String body, String attachment, LocalDateTime deadline) throws Exception {
        this.creator = this.setCreator(creator);
        setTitle(title);
        setBody(body);
        setAttachment(attachment);
        setDeadline(deadline);

        this.id = count++;
        database.add(this);
        appendToCSV(this);
    }

    public Assignment(User creator, String title, String body, LocalDateTime deadline) throws Exception {
        this.creator = this.setCreator(creator);
        setTitle(title);
        setBody(body);
        setDeadline(deadline);

        this.id = count++;
        database.add(this);
        appendToCSV(this);
    }
    private User setCreator(User creator) throws Exception {
        Validators.positionValidator(creator.getPosition(), new Positions[]{Positions.ADMIN, Positions.TEACHER});
        return creator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws Exception {
        this.title = Validators.lengthValidator(title, 3);
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) throws Exception {
        this.body = Validators.lengthValidator(body,20);
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) throws Exception {
        this.attachment = Validators.attachmentValidator(attachment);
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) throws Exception {
        this.deadline = Validators.deadlineValidator(deadline);
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public static void appendToCSV(Assignment assignment) throws IOException {
        CRUD.add(createCSVLine(assignment), Config.assignmentsCSVPath, true);
    }

    public static String createCSVLine(Assignment assignment){
        return assignment.id+ ","+
                assignment.creator.id+","+
                assignment.title+","+
                assignment.body+","+
                assignment.attachment+","+
                Utils.dateToString(assignment.deadline)+","+
                Utils.dateToString(assignment.created)+"\n";
    }

    public static Assignment fetchByID(int id){
        for(Assignment assignment: database){
            if (Objects.equals(assignment.id, id)) {
                return assignment;
            }
        }
        return null;
    }

    public void deleteAssignment(Assignment assignment){
        database.remove(assignment);
        assignment=null;
    }


    @Override
    public String toString() {
        return "Assignment{" +
                "id=" + id +
                ", creator_id=" + creator.id+ '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", attachment='" + attachment + '\'' +
                ", deadline=" + deadline + '\'' +
                ", created=" + created +
                '}';
    }
}
