package backend.functionality;

import backend.Config;
import backend.Utils;
import backend.constants.MeetingType;
import backend.constants.Positions;
import backend.Validators;
import backend.database.CRUD;
import backend.users.User;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Meeting {
    private static int count;
    public static ArrayList<Meeting> database = new ArrayList<>();
    final int id;
    final User creator;
    private String topic;
    private MeetingType type;
    private String info;
    private LocalDateTime date;
    private LocalDateTime created = LocalDateTime.now();

    public Meeting(User creator, String topic,MeetingType type, String info, LocalDateTime date, LocalDateTime created) throws Exception {
        this.creator = setCreator(creator);
        setTopic(topic);
        setType(type);
        setInfo(info);
        setDate(date);
        this.created =created;

        this.id = count++;
        database.add(this);
    }
    public Meeting(User creator, String topic,MeetingType type, String info, LocalDateTime date) throws Exception {
        this.creator = setCreator(creator);
        setTopic(topic);
        setType(type);
        setInfo(info);
        setDate(date);

        this.id = count++;
        database.add(this);
        appendToCSV(this);
    }
    private User setCreator(User creator) throws Exception {
        Validators.positionValidator(creator.getPosition(), new Positions[]{Positions.ADMIN, Positions.TEACHER});
        return creator;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) throws Exception {
        this.topic = Validators.lengthValidator(topic, 3);
    }

    public MeetingType getType() {
        return type;
    }

    public void setType(MeetingType type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) throws Exception {
        this.info = Validators.lengthValidator(info,8);
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) throws Exception {
        this.date = Validators.deadlineValidator(date);
    }
    public LocalDateTime getCreated() {
        return created;
    }

    public static void appendToCSV(Meeting meeting) throws IOException {
        CRUD.add(createCSVLine(meeting), Config.meetingsCSVPath, true);
    }
    public static String createCSVLine(Meeting meeting){
        return meeting.id+ ","+
                meeting.creator.id+","+
                meeting.topic+","+
                meeting.type+","+
                meeting.info+","+
                Utils.dateToString(meeting.date)+","+
                Utils.dateToString(meeting.created)+"\n";
    }
    public void deleteMeeting(Meeting meeting){
        database.remove(meeting);
        meeting=null;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "id=" + id +
                ", creator_id=" + creator.id+ '\'' +
                ", topic='" + topic + '\'' +
                ", type='" + type + '\'' +
                ", info='" + info + '\'' +
                ", date=" + date + '\'' +
                ", created=" + created +
                '}';
    }
}
