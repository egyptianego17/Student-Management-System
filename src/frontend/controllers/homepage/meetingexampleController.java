package frontend.controllers.homepage;

import backend.constants.Positions;
import backend.users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.StandardCopyOption;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class meetingexampleController implements Initializable {

    @FXML
    private AnchorPane assignmentpane; //main assignment example pane with everything build on top


    @FXML
    private Button btnedit; //edit button
    @FXML
    private Button btnremove; //remove button accessible when using edit
    @FXML
    private Button btnsaveedit; //save button accessible when using edit
    @FXML
    private Button btncanceledit; //cancel button accessible when using edit


    @FXML
    private DatePicker datepicker; // a date picker to change the deadline on edit mode


    @FXML
    private Text teacher; //teacher text
    @FXML
    private Text topic; //topic text
    @FXML
    private Text deadline; //deadline text
    @FXML
    private Text meetingtype; //meeting type


    @FXML
    private TextArea description; //main assignment description area


    private static Stage stage;

    private String assignmentdescriptiontext; //text that save the description of the assignment for when edited


    //****************************************************************************************************************//
    //EDIT ASSIGNMENT PART ACCESSIBLE BY CLICKING THE EDIT BUTTON (NO STUDENT HERE)

    @FXML
    void edit(ActionEvent event) { //should only be accessed by teacher and admins
        assignmentdescriptiontext = description.getText();
        description.setEditable(true);

        btnsaveedit.setVisible(true);
        btncanceledit.setVisible(true);
        btnremove.setVisible(true);

        btnedit.setVisible(false);

        datepicker.setVisible(true);

    }

    @FXML
    void newdeadline(ActionEvent event) { //accessible by teachers and admins (students shouldn't get here
        LocalDate date = datepicker.getValue();
        assignmentsController.deadline = date.atStartOfDay();
    }

    @FXML
    void saveedit(ActionEvent event) throws IOException{ //accessible by teachers and admins (students shouldn't get here
        description.setText(description.getText());
        assignmentdescriptiontext = description.getText();
        //deadline.setText(assignmentsController.deadline);

        description.setEditable(false);
        btncanceledit.setVisible(false);
        btnsaveedit.setVisible(false);
        btnremove.setVisible(false);

        btnedit.setVisible(true);

        datepicker.setVisible(false);
    }

    @FXML
    void canceledit(ActionEvent event) throws IOException{ //accessible by teachers and admins (students shouldn't get here
        description.setText(assignmentdescriptiontext);

        description.setEditable(false);
        btncanceledit.setVisible(false);
        btnsaveedit.setVisible(false);
        btnremove.setVisible(false);

        btnedit.setVisible(true);

        datepicker.setVisible(false);
    }

    @FXML
    void removeASS(ActionEvent event) throws IOException { //accessible by teachers and admins (students shouldn't get here
        VBox parent = (VBox) assignmentpane.getParent();
        parent.getChildren().remove(assignmentpane);
    }


    //****************************************************************************************************************//
    //MEETING INITIALISATION

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(backend.users.User.currentUser.getPosition() == Positions.STUDENT){
            btnedit.setVisible(false);
        }
        teacher.setText(backend.users.User.currentUser.getFirst_name());
        topic.setText(assignmentsController.topic);
        //deadline.setText(assignmentsController.deadline);
        description.setText(assignmentsController.description);
        meetingtype.setText(meetingsController.type);

        btnremove.setVisible(false);
        btncanceledit.setVisible(false);
        btnsaveedit.setVisible(false);
    }
}
