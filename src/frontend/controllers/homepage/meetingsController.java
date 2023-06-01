package frontend.controllers.homepage;

import backend.constants.Positions;
import backend.users.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.time.LocalDate;
import java.util.ResourceBundle;

public class meetingsController implements Initializable {

    @FXML
    private VBox vbox;

    @FXML
    private AnchorPane newassignment;

    @FXML
    private DatePicker txtdeadline;

    @FXML
    private TextArea txtdescription;

    @FXML
    private TextField txtteacher; //DATA BASE USER VALUE
    @FXML
    private TextField txttopic;

    @FXML
    private Label lblfile;

    @FXML
    private Button btnaddnewmeeting;

    @FXML
    private ComboBox<String> meetingtype;

    private ObservableList<String> meettypes = FXCollections.//LIST FOR COMBOBOX MEETING TYPE
            observableArrayList("Online","Offline"); // change with the database

    public static String title, topic, teacher, description, deadline, type;
    private Stage stage;
    public static File uploadedfile;

    //****************************************************************************************************************//
    //MAIN PAIN AND INITIALISATION
    public ObservableList<String> getmeettypes() {return meettypes;}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { //initialising the meetings pane
        if(User.currentUser.getPosition() == Positions.STUDENT){btnaddnewmeeting.setVisible(false);}
        newassignment.setVisible(false);
        lblfile.setVisible(false);

        meetingtype.setValue("Online");
        meetingtype.setItems(getmeettypes());
    }

    public void addassignments(ActionEvent event) throws IOException { //showing the add meetings pane
        newassignment.setVisible(true);
        System.out.println("HELLO");
    }

    //****************************************************************************************************************//
    //ADD ASSIGNMENTS PANE

    @FXML
    void cancelmeeting(ActionEvent event) { //removing the pane and inputted data when canceling
        newassignment.setVisible(false);
        txttopic.clear();
        txtdescription.clear();
    }

    @FXML
    void savemeeting(ActionEvent event) throws IOException { //creating a new meeting based on info and calling the pane
        topic = txttopic.getText();
        description = txtdescription.getText();

        txtdeadline.setDisable(false);
        newassignment.setVisible(false);
        type = meetingtype.getValue();

        AnchorPane root;
        root = FXMLLoader.load(getClass().getResource("/frontend/fxml/homepage/meetingexample.fxml"));
        vbox.getChildren().add(root);

        /*for (int i = 0; i <5; i++){
            root = FXMLLoader.load(getClass().getResource("/frontend/fxml/homepage/meetingexample.fxml"));
            vbox.getChildren().add(root);
        }*/

        System.out.println(type);

    }

    @FXML
    public void choosedate(ActionEvent event) { //function to choose the date

        LocalDate date = txtdeadline.getValue();
        deadline = date.toString();
    }
}
