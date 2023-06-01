package frontend.controllers.homepage;

import backend.constants.Positions;
import backend.users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class projectController implements Initializable {

    @FXML
    private AnchorPane newassignment; //main pane

    @FXML
    private VBox vbox; //box containing projects

    @FXML
    private DatePicker txtdeadline;

    @FXML
    private Label lblfile;

    @FXML
    private TextField txtteacher;
    @FXML
    private TextField txttopic;

    @FXML
    private TextArea txtdescription;

    @FXML
    private Button btnaddnewproject;


    public static String title, topic, teacher, description, deadline;
    private Stage stage;
    public static boolean fileexists;
    private FileChooser filechooser = new FileChooser();
    public static File uploadedfile;

    //****************************************************************************************************************//
    //Main PANE INITIALISATION
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {//initialising the new project pane
        if(User.currentUser.getPosition() == Positions.STUDENT){btnaddnewproject.setVisible(false);}
        newassignment.setVisible(false);
        lblfile.setVisible(false);
    }

    public void addassignments(ActionEvent event) throws IOException {//showing the add project pane
        newassignment.setVisible(true);
        System.out.println("HELLO");
    }

    @FXML
    void cancelassignment(ActionEvent event) { //clearing fields at cancel
        newassignment.setVisible(false);
        txttopic.clear();
        txtdescription.clear();
    }

    @FXML
    void saveassignment(ActionEvent event) throws IOException {//creating new project based on inputted info
        topic = txttopic.getText();
        description = txtdescription.getText();

        txtdeadline.setDisable(false);
        newassignment.setVisible(false);
        AnchorPane root;
        root = FXMLLoader.load(getClass().getResource("/frontend/fxml/homepage/projectexample.fxml"));
        vbox.getChildren().add(root);

        //USE TO LOAD ALL DATA BASE PROJECTS FIRST , DO IT IN THE INITIALISATION OR SMTHIN IDK
        /*for (int i = 0; i <5; i++){
            root = FXMLLoader.load(getClass().getResource("/frontend/fxml/homepage/assignmentexample.fxml"));
            vbox.getChildren().add(root);
        }*/
    }

    @FXML
    void uploadattachement(ActionEvent event) { //file chooser upload attachement button
        filechooser.setTitle("Choose Attachment File");
        File file = filechooser.showOpenDialog(stage);
        uploadedfile = file;
        if (file != null) {lblfile.setText("File Uploaded!"); lblfile.setVisible(true); fileexists = true;}
        else {lblfile.setText("No File Uploaded!"); lblfile.setVisible(true); fileexists = false;}
    }

    @FXML
    public void choosedate(ActionEvent event) { // function to choose deadline

        LocalDate date = txtdeadline.getValue();
        deadline = date.toString();

    }
}
