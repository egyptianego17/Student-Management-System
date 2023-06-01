package frontend.controllers.homepage;

import backend.constants.Positions;
import backend.functionality.Assignment;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class assignmentsController implements Initializable {

    @FXML
    private VBox vbox;

    @FXML
    private AnchorPane newassignment;

    @FXML
    private DatePicker txtdeadline;

    @FXML
    private TextArea txtdescription;

    @FXML
    private TextField txtteacher;//TO BE SET TO THE BACK END NAME VALUE FOR TEACHER OR ADMIN
    @FXML
    private TextField txttopic;

    @FXML
    private Label lblfile;

    @FXML
    private Button btnaddassignments;


    private Stage stage;
    public static boolean fileexists;
    private FileChooser filechooser = new FileChooser();//INITIALISING FILE CHOOSER POPUP
    public static String title, topic, teacher, description;
    public static LocalDateTime deadline;//WILL BE REMOVED AFTER INTEGRATION WITH BACKEND

    public static File uploadedfile;

    //****************************************************************************************************************//
    //INITIALISING THE ASSIGNMENTS PANE

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(User.currentUser.getPosition() == Positions.STUDENT){btnaddassignments.setVisible(false);}
        txtteacher.setText(User.currentUser.getFirst_name()+" "+ User.currentUser.getLast_name());
        newassignment.setVisible(false);
        lblfile.setVisible(false);

        for (Assignment assignmentLoop: Assignment.database )
        {
            assignmentexampleController.newassignment(assignmentLoop.getTitle(), assignmentLoop.creator.getFirst_name(),
                    assignmentLoop.getBody(), assignmentLoop.getDeadline().toString());
            AnchorPane root;
            try {
                root = FXMLLoader.load(getClass().getResource("/frontend/fxml/homepage/assignmentexample.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            vbox.getChildren().add(root);
        }
    }

    public void addassignments(ActionEvent event) throws IOException {
        newassignment.setVisible(true);
    }

    //****************************************************************************************************************//
    //THE ADD ASSIGNMENTS PAGE

    @FXML
    void cancelassignment(ActionEvent event) { //CLEARING FIELDS ON CANCEL BUTTON
        newassignment.setVisible(false);
        txttopic.clear();
        txtdescription.clear();
    }

    @FXML
    void saveassignment(ActionEvent event) throws Exception {//SAVE ASSIGNMENTS BUTTON
        topic = txttopic.getText();
        description = txtdescription.getText();

        txtdeadline.setDisable(false);
        newassignment.setVisible(false);
        AnchorPane root;
        root = FXMLLoader.load(getClass().getResource("/frontend/fxml/homepage/assignmentexample.fxml"));
        vbox.getChildren().add(root);

        new Assignment(User.currentUser, topic, description, uploadedfile.getAbsolutePath(), deadline);
        //USE TO INITIALISE THE ASSIGNMENTS IN THE DATABASE BEFORE ADDING MORE, USE IN INITIALISE, IDK SOLVE IT
        /*for (int i = 0; i <5; i++){
            root = FXMLLoader.load(getClass().getResource("/frontend/fxml/homepage/assignmentexample.fxml"));
            vbox.getChildren().add(root);
        }*/
    }

    @FXML
    void uploadattachement(ActionEvent event) { //UPLOAD ATTACHMENT BUTTON USING FILECHOOSER
        filechooser.setTitle("Choose Attachment File");
        File file = filechooser.showOpenDialog(stage);
        uploadedfile = file;
        if (file != null) {lblfile.setText("File Uploaded!"); lblfile.setVisible(true); fileexists = true;}
        else {lblfile.setText("No File Uploaded!"); lblfile.setVisible(true); fileexists = false;}
    }

    @FXML
    public void choosedate(ActionEvent event) {//PICK DATE USING DATE PICKER

        LocalDate date = txtdeadline.getValue();
        deadline = date.atStartOfDay();
    }
}
