package backend.database;

import backend.Utils;
import backend.constants.MeetingType;
import backend.constants.Positions;
import backend.functionality.Assignment;
import backend.functionality.Feedback;
import backend.functionality.Meeting;
import backend.functionality.Submission;
import backend.users.User;

import java.io.*;

public class CRUD {

    public static void add(String toWrite, String destination, boolean append) throws IOException {
        FileWriter csvWriter = new FileWriter(destination, append);
        csvWriter.write(toWrite);
        csvWriter.flush();
        csvWriter.close();
    }

    public static void readUserFromCSV(String source) throws Exception {
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(source));
            String line;
            while ((line = csvReader.readLine()) != null) {
                String[] user = line.split(",");
                User.initializeChild(user[1],user[2],user[3],user[4], Positions.fromValue(user[5]), true);
            }
            csvReader.close();
        } catch (Exception e) {
            if (!(e instanceof FileNotFoundException)){
                throw new RuntimeException(e);
            }
        }
    }

    public static void readMeetingFromCSV(String source) throws Exception {
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(source));
            String line;
            while ((line = csvReader.readLine()) != null) {
                String[] meeting = line.split(",");
                new Meeting(User.fetchByID(Integer.parseInt(meeting[1])), meeting[2], MeetingType.valueOf(meeting[3]), meeting[4], Utils.stringToDate(meeting[5]),  Utils.stringToDate(meeting[6]));
            }
            csvReader.close();
        } catch (Exception e) {
            if (!(e instanceof FileNotFoundException)){
                throw new RuntimeException(e);
            }
        }
    }

    public static void readAssignmentFromCSV(String source) throws Exception {
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(source));
            String line;
            while ((line = csvReader.readLine()) != null) {
                String[] assignment = line.split(",");
                new Assignment(User.fetchByID(Integer.parseInt(assignment[1])),assignment[2],assignment[3],assignment[4], Utils.stringToDate(assignment[5]), Utils.stringToDate(assignment[6]));
            }
            csvReader.close();
        } catch (Exception e) {
            if (!(e instanceof FileNotFoundException)){
                throw new RuntimeException(e);
            }
        }
    }
    public static void readSubmissionFromCSV(String source) throws Exception {
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(source));
            String line;
            while ((line = csvReader.readLine()) != null) {
                String[] submission = line.split(",");
                new Submission(User.fetchByID(Integer.parseInt(submission[1])), Assignment.fetchByID(Integer.parseInt(submission[2])), submission[3], submission[4], Utils.stringToDate(submission[5]));
            }
            csvReader.close();
        } catch (Exception e) {
            if (!(e instanceof FileNotFoundException)){
                throw new RuntimeException(e);
            }
        }
    }

    public static void readFeedbackFromCSV(String source) throws Exception {
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(source));
            String line;
            while ((line = csvReader.readLine()) != null) {
                String[] feedback = line.split(",");
                new Feedback(User.fetchByID(Integer.parseInt(feedback[1])), Submission.fetchByID(Integer.parseInt(feedback[2])), Double.parseDouble(feedback[3]), feedback[4], Utils.stringToDate(feedback[5]));
            }
            csvReader.close();
        } catch (Exception e) {
            if (!(e instanceof FileNotFoundException)){
                throw new RuntimeException(e);
            }
        }
    }


    public static void clearCSV(String source) throws IOException {
        add("", source, false);
    }
}
