package frontend.controllers.homepage;

import backend.users.User;
import frontend.controllers.Signin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;

public class profileController implements Initializable {

    @FXML
    private AnchorPane verifypane;


    @FXML
    private Button btncancel;
    @FXML
    private Button btnedit;
    @FXML
    private Button btnsave;


    @FXML
    private TextField txtemail;
    @FXML
    private TextField txtid;
    @FXML
    private TextField txtname;

    @FXML
    private PasswordField txtpassword;
    @FXML
    private PasswordField txtconfirmpass;

    //****************************************************************************************************************//
    //EDIT USER PROFILE PANEL

    @FXML
    void profileEdit(ActionEvent event) { //profile edit button
        btncancel.setVisible(true);
        btnsave.setVisible(true);
        btnedit.setVisible(false);

        txtname.setEditable(true);
        txtemail.setEditable(true);
        //txtpassword.setEditable(true);
        //txtpass.setEditable(true);

    }

    @FXML
    void saveEdits(ActionEvent event) {
        verifypane.setVisible(true);
    } // profile save button

    @FXML
    void cancelEdits(ActionEvent event) { //profile cancel button
        btncancel.setVisible(false);
        btnsave.setVisible(false);
        btnedit.setVisible(true);

        txtname.setEditable(false);
        txtemail.setEditable(false);
        //txtpassword.setEditable(false);

        txtname.clear();
        txtemail.clear();
        //txtpassword.clear();
    }

    //****************************************************************************************************************//
    //VERIFY USER PANEL POPUP

    @FXML
    void saveverify(ActionEvent event) {
        verifypane.setVisible(false);

        btncancel.setVisible(false);
        btnsave.setVisible(false);
        btnedit.setVisible(true);

        txtname.getText();
        txtemail.getText();
        //txtpassword.getText();

        txtname.setEditable(false);
        txtemail.setEditable(false);
        //txtpassword.setEditable(false);


        txtconfirmpass.clear();
    }

    @FXML
    void cancelverify(ActionEvent event) {
        verifypane.setVisible(false);
        txtconfirmpass.clear();
        cancelEdits(event);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        verifypane.setVisible(false);
        txtname.setText(User.currentUser.getFirst_name());
        txtemail.setText(User.currentUser.getEmail());
        txtid.setText(Integer.toString(User.currentUser.id));

    }
}
