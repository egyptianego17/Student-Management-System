package frontend.controllers;

import backend.users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Signin implements Initializable{

    private Stage stage;
    private Scene scene;

    @FXML
    private TextField txtemail;
    @FXML
    private PasswordField txtpassword;

    @FXML
    private Text emailerror;
    @FXML
    private Text passerror;

    //****************************************************************************************************************//
    //MAIN PANE

    @FXML
    void signup(MouseEvent event) throws IOException { //SIGNUP TEXT AT THE BUTTOM
        Utils.redirectScene(this.getClass(), event, "/frontend/fxml/signup.fxml", stage, scene);
    }

    @FXML
    void studentsigninclick(ActionEvent event) throws Exception{ // SIGN IN BUTTON
        User user = User.fetchByEmail(txtemail.getText());
        if (user != null){
            if (user.checkPassword(txtpassword.getText())){
                User.currentUser = user;
                Utils.redirectScene(this.getClass(),event, "/frontend/fxml/homepage.fxml", stage, scene);
            }
            passerror.setText("Incorrect Password");
            passerror.setTextAlignment(TextAlignment.LEFT);
            passerror.setVisible(true);
            emailerror.setVisible(false);
            return;
        }
        emailerror.setText("This user does not exist");
        emailerror.setTextAlignment(TextAlignment.LEFT);
        emailerror.setVisible(true);
    }

    @FXML
    void systemexit(ActionEvent event) {
        System.exit(0);
    }//EXIT BUTTON

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
