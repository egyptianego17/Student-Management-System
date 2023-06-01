package backend.users;

import backend.Config;
import backend.Utils;
import backend.constants.Positions;
import backend.Validators;
import backend.database.CRUD;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public abstract class User {
    private static int count;

    public static ArrayList<User> database = new ArrayList<>();
    public static User currentUser;

    public final int id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    Positions position;
    private boolean approved = true;

    public User(String first_name, String last_name, String email, String password, Positions position, boolean csv) throws Exception {

        this.setFirst_name(first_name);
        this.setLast_name(last_name);
        this.setEmail(email);
        if (csv){
            this.password = password;
        }
        else {
            this.setPassword(password);
        }
        this.position = position;

        this.id = count++;
        database.add(this);
        if (!csv){
            User.appendToCSV(this);
        }
    }
    public String getFirst_name(){
        return first_name;
    }

    public void setFirst_name(String first_name) throws Exception {
        this.first_name = Validators.lengthValidator(first_name,3);;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) throws Exception {
        this.last_name = Validators.lengthValidator(last_name, 3);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception {
        if (fetchByEmail(email) != null){
            throw new Exception("Email is already in use");
        }
        this.email = Validators.emailValidator(email);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws Exception {
        this.password = Utils.hash(Validators.passwordValidator(password));
    }

    public boolean checkPassword(String password){
        return Utils.checkHash(password, this.password);
    }

    public Positions getPosition() {
        return position;
    }

    public boolean getApproved() {
        return approved;
    }

    public void setApproved(boolean approved, User setter) throws Exception {
        Validators.positionValidator(setter.position, new Positions[]{Positions.ADMIN});
        this.approved = approved;
    }

    // Start of static methods
    public static void deleteUser(User user){
        database.remove(user);
        user = null;
    }
    public static User fetchByEmail(String email){
        for(User user: database){
            if (Objects.equals(user.email, email)) {
                return user;
            }
        }
        return null;
    }

    public static User fetchByID(int id){
        for(User user: database){
            if (Objects.equals(user.id, id)) {
                return user;
            }
        }
        return null;
    }
    public static User initializeChild(String first_name, String last_name, String email, String password, Positions position, boolean csv) throws Exception {
        switch (position) {
            case ADMIN -> {
                return new Admin(first_name, last_name, email, password, position, csv);
            }
            case TEACHER -> {
                return new Teacher(first_name, last_name, email, password, position, csv);
            }
            case STUDENT -> {
                return new Student(first_name, last_name, email, password, position, csv);
            }
        }
        return null;
    }
    public static void appendToCSV(User user) throws IOException {
        CRUD.add(createCSVLine(user), Config.usersCSVPath, true);
    }
    public static String createCSVLine(User user){
        return user.id+ ","+
                user.getFirst_name()+","+
                user.getLast_name()+","+
                user.getEmail()+","+
                user.getPassword()+","+
                user.getPosition()+"\n";
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", position=" + position +
                '}';
    }
}

