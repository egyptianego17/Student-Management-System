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

public class Feedback {
    private static int count;
    public static ArrayList<Feedback> database = new ArrayList<>();
    final int id;
    final User creator;
    final Submission submission;
    private double score;
    private String notes;
    private LocalDateTime created = LocalDateTime.now();

    public Feedback(User creator, Submission submission, double score, String notes, LocalDateTime created) throws Exception {
        this.creator = setCreator(creator);
        this.submission = submission;
        setScore(score);
        setNotes(notes);
        this.created =created;

        this.id = count++;
        database.add(this);

    }
    public Feedback(User creator, Submission submission, double score, String notes) throws Exception {
        this.creator = setCreator(creator);
        this.submission = submission;
        setScore(score);
        setNotes(notes);

        this.id = count++;
        database.add(this);
        appendToCSV(this);
    }
    private User setCreator(User creator) throws Exception {
        Validators.positionValidator(creator.getPosition(), new Positions[]{Positions.ADMIN, Positions.TEACHER});
        return creator;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) throws Exception {
        this.score = Validators.rangeValidator(score,0,10);
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) throws Exception {
        this.notes = Validators.lengthValidator(notes, 3);
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public static void appendToCSV(Feedback feedback) throws IOException {
        CRUD.add(createCSVLine(feedback), Config.feedbackCSVPath, true);
    }
    public static String createCSVLine(Feedback feedback){
        return feedback.id+ ","+
                feedback.creator.id+","+
                feedback.submission.id+","+
                feedback.score+","+
                feedback.notes+","+
                Utils.dateToString(feedback.created)+"\n";
    }

    public void deleteAssignment(Feedback meeting){
        database.remove(meeting);
        meeting=null;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", creator_id=" + creator.id+'\'' +
                ", assignment_id=" + submission.id+'\'' +
                ", score=" + score+ '\'' +
                ", notes='" + notes + '\'' +
                ", created=" + created +'\'' +
                '}';
    }
}
