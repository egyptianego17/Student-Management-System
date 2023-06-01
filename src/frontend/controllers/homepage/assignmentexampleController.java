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

public class assignmentexampleController implements Initializable {

    @FXML
    private AnchorPane assignmentpane; //main assignment example pane with everything build on top

    @FXML
    private AnchorPane assignment; //pane responsible for the assignment itself
    @FXML
    private AnchorPane submitscreen; //pane responsible for the submit screen for the students
    @FXML
    private AnchorPane feedback; //feedback pane for users to access


    @FXML
    private Button btnedit; //edit button
    @FXML
    private Button btnfeedback; //feedback button
    @FXML
    private Button btnremove; //remove button accessible when using edit
    @FXML
    private Button btnsaveedit; //save button accessible when using edit
    @FXML
    private Button btncanceledit; //cancel button accessible when using edit
    @FXML
    private Button btnattachment; //attachments button accessible by users
    @FXML
    private Button btnstudentreturn; //button responsible for returning to main assignment page if user is a STUDENT
    @FXML
    private Button btn;
    @FXML
    private Button btnsavefeedback;
    @FXML
    private Button btncancelfeedback;

    @FXML
    private DatePicker datepicker; // a date picker to change the deadline on edit mode


    @FXML
    private Text teacher; //teacher text
    @FXML
    private Text topic; //topic text
    @FXML
    private Text deadline; //deadline text
    @FXML
    private Text posteddate; //current date text .....not operational yet


    @FXML
    private TextField userrating; //user rating for teacher to input ....should be a number

    @FXML
    private TextArea description; //main assignment description area
    @FXML
    private TextArea userfeedback; //main user feed-back description area

    @FXML
    private Label lblfile; // label to tell user if attachment file was downloaded or not


    private static Stage stage;
    private static final DirectoryChooser directoryChooser = new DirectoryChooser(); //browse for file screen

    private String assignmentdescriptiontext, userfeedbackrating, userfeedbacktext; //text that save the description of the assignment for when edited
    private static String asstopic, assteacher, assdescription, assdeadline;

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
        btnfeedback.setVisible(false);

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
        deadline.setText(assignmentsController.deadline.toString());

        description.setEditable(false);
        btncanceledit.setVisible(false);
        btnsaveedit.setVisible(false);
        btnremove.setVisible(false);

        btnedit.setVisible(true);
        btnfeedback.setVisible(true);

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
        btnfeedback.setVisible(true);

        datepicker.setVisible(false);
    }

    @FXML
    void removeASS(ActionEvent event) throws IOException { //accessible by teachers and admins (students shouldn't get here
        VBox parent = (VBox) assignmentpane.getParent();
        parent.getChildren().remove(assignmentpane);
    }

    //****************************************************************************************************************//
    // FEEDBACK SECTION OF THE PANE

    //remaining part is if the position of the user is student he can only access the feedback but without it's buttons
    @FXML
    void assignfeedback(ActionEvent event) { //accessible by teachers and admins (students shouldn't get here
        feedback.setVisible(true);
        assignment.setVisible(false);
        if(User.currentUser.getPosition() == Positions.STUDENT){
            btnsavefeedback.setVisible(false);
            btncancelfeedback.setText("Return");

            userrating.setEditable(false);
            userfeedback.setEditable(false);
        }
        else {
            userfeedbackrating = userrating.getText();
            userfeedbacktext = userfeedback.getText();

            userfeedbacktext = userfeedback.getText();
            userfeedbackrating = userrating.getText();
        }
    }

    @FXML
    void savefeedback(ActionEvent event) { //accessible by teachers and admins (students shouldn't get here
        userfeedback.setText(userfeedback.getText());
        userrating.setText(userrating.getText());

        userfeedbackrating = userrating.getText();
        userfeedbacktext = userfeedback.getText();

        feedback.setVisible(false);
        assignment.setVisible(true);
    }

    @FXML
    void cancelfeedback(ActionEvent event) { //accessible by teachers and admins (students shouldn't get here
        userfeedback.setText(userfeedbacktext);
        userrating.setText(userfeedbackrating);

        feedback.setVisible(false);
        assignment.setVisible(true);
    }

    //this function has an accompanying button which should only be visible to STUDENTS
    @FXML
    void returntoassignment(ActionEvent event) { //this should be visible if the user is a student
        feedback.setVisible(false);
    }


    //****************************************************************************************************************//
    //MAIN ASSIGNMENT PANE

    @FXML
    void showattachement(ActionEvent event) throws IOException{
        if (assignmentsController.fileexists) {
            //creating new directory chooser
            directoryChooser.setTitle("Choose Save Directory");
            File seldir = directoryChooser.showDialog(stage);

            if (seldir != null) {
                try {
                    //getting the new path file and original file path
                    Path original = new File(String.valueOf(assignmentsController.uploadedfile)).toPath();
                    Path newFilePath = new File(seldir.getAbsolutePath() + "/" + assignmentsController.uploadedfile.getName()).toPath();

                    System.out.println(original);
                    System.out.println(newFilePath);
                    //copying the files over
                    Files.copy(original, newFilePath, StandardCopyOption.REPLACE_EXISTING);
                    btnattachment.setText("Downloaded");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @FXML
    void submit(ActionEvent event) { //accessible by student only
        submitscreen.setVisible(true);
        assignment.setVisible(false);
    }

    @FXML
    void submitassignment(ActionEvent event) { //accessible by student only
        assignment.setVisible(true);
        submitscreen.setVisible(false);
    }

    @FXML
    void cancelsubmission(ActionEvent event){ //accessible by student only
        assignment.setVisible(true);
        submitscreen.setVisible(false);
    }

    @FXML
    void uploadattachment(ActionEvent event) { //accessible by student only
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Choose Attachment File");
        File file = filechooser.showOpenDialog(stage);
        if (file != null) {lblfile.setText("File Uploaded!"); lblfile.setVisible(true);}
        else {lblfile.setText("No File Uploaded!"); lblfile.setVisible(true);}
    }


    //****************************************************************************************************************//
    //ASSIGNMENT INITIALISATION

    public static void newassignment(String atopic, String ateacher, String adescription, String adeadline){
        assteacher = ateacher;
        asstopic = atopic;
        assdescription = adescription;
        assdeadline = adeadline;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(User.currentUser.getPosition() == Positions.STUDENT){
            btnedit.setVisible(false);
        }

        submitscreen.setVisible(false);
        teacher.setText(assteacher);
        deadline.setText(assdeadline);
        topic.setText(asstopic);
        description.setText(assdescription);

        btnremove.setVisible(false);
        btncanceledit.setVisible(false);
        btnsaveedit.setVisible(false);
    }
}
