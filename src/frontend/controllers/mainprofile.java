package frontend.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class mainprofile implements Initializable {

    @FXML
    private Button profileButton;

    @FXML
    private Button assignmentsButton;

    @FXML
    private Button projectsButton;

    @FXML
    private Label contactUsLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize your controller here
    }

    @FXML
    private void AssButton() {
        // Handle profile button click event

        System.out.println("Assignments button clicked");
    }

    @FXML
    private void ProjectButton() {
        // Handle assignments button click event
        System.out.println("Project button clicked");
    }

    @FXML
    private void profileButton() {
        // Handle projects button click event
        System.out.println("Profile button clicked");
    }

}

