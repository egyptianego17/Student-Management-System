package backend.tests;

import backend.Config;
import backend.Utils;
import backend.constants.MeetingType;
import backend.constants.Positions;
import backend.database.CRUD;
import backend.functionality.Feedback;
import backend.functionality.Meeting;
import backend.functionality.Submission;
import backend.users.Admin;
import backend.users.Student;
import backend.users.User;
import backend.functionality.Assignment;

import java.time.LocalDateTime;
import java.util.Objects;

public class Test{
    public static String email = "omar.omran@ieee-zsb.org";
    public static String password = "Test1234";

    public static void main(String[] args) throws Exception {

        User user = addUser();
        emailExists();
        Assignment assignment = addAssignment(user);
        addMeeting(user);
        Submission submission = addSubmission(user, assignment);
        Feedback feedback = addFeedback(user, submission);
//        testCSVRead();
//        testClearCSV();
        Utils.print(user);
        Utils.print(assignment);
        Utils.print(submission);
        Utils.print(feedback);
    }

    public static User addUser() throws Exception {
        Admin admin = new Admin(
                "John",
                "Doe",
                email,
                password,
                Positions.TEACHER,
                false);
        System.out.println("Add User Passed!");
        return admin;
    }
    public static void emailExists() {
        try {
            Student user = new Student(
                    "Omar",
                    "Omran",
                    email,
                    password,
                    Positions.STUDENT,
                    false);
        } catch (Exception e) {
            if (Objects.equals(e.getMessage(), "Email is already in use")) {
                System.out.println("Existence Passed!");
                return;
            }
        }
        System.out.println("Email Existence Failed!");
    }
    public static Assignment addAssignment(User user) throws Exception {
        Assignment assignment = new Assignment(
                user,
                "First ever assignment",
                "This is an actual assignment stay tudent lol",
                LocalDateTime.of(2024,02,12,4,40)
        );
        System.out.println("Add Assignment Passed!");
        return assignment;
    }


    public static Meeting addMeeting(User user) throws Exception {
        Meeting meeting = new Meeting(
                user,
                "First ever meeting",
                MeetingType.ONLINE,
                "www.testing.com",
                LocalDateTime.of(2024,02,12,4,40)
        );
        System.out.println("Add Meeting Passed!");
        return meeting;
    }
    public static Submission addSubmission(User user, Assignment assignment) throws Exception {
        Submission submission = new Submission(
                user,
                assignment,
                "this is my first submission"
        );
        System.out.println("Add Submission Passed!");
        return submission;
    }
    public static Feedback addFeedback(User user, Submission submission) throws Exception {
        Feedback feedback = new Feedback(
                user,
                submission,
                8,
                "this is my first submission"
        );
        System.out.println("Add Feedback Passed!");
        return feedback;
    }
    public static void testCSVRead(){
        try {
            CRUD.readUserFromCSV(Config.usersCSVPath);
        } catch (Exception e) {
            if (Objects.equals(e.getMessage(), "Email is already in use")) {
                System.out.println("CSV Write Pass!");
                System.out.println("CSV Read Pass!");
                return;
            }
        }
        System.out.println("CSV Write Failed!");
        System.out.println("CSV Read Failed!");
    }
    public static void testClearCSV() throws Exception {
        CRUD.clearCSV(Config.usersCSVPath);
        CRUD.readUserFromCSV(Config.usersCSVPath);
    }
}