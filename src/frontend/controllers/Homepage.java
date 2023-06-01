package frontend.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Homepage implements Initializable {

    private Stage stage;
    private Scene scene;

    @FXML
    private Pane pane;

    @FXML
    private Button profileButton;
    @FXML
    private Button assignmentsButton;
    @FXML
    private Button projectsButton;

    @FXML
    private Label contactUsLabel;

    private boolean onprojects = false, onassignment = false, onprofile = false;

    //****************************************************************************************************************//
    //INITIALISING THE PANE

    @Override
    public void initialize(URL location, ResourceBundle resources) {//START ON PROFILE PAGE
        Parent fxml;
        try {
            fxml = FXMLLoader.load(getClass().getResource("/frontend/fxml/homepage/profile.fxml"));
            onprofile = true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        pane.getChildren().removeAll();
        pane.getChildren().addAll(fxml);
        // Initialize your controller here
    }

    @FXML
    private void profileButton(ActionEvent event) throws IOException {//ON PROFILE BUTTON CLICK

        System.out.println("Profile button clicked");
        Parent fxml;
        fxml = FXMLLoader.load(getClass().getResource("/frontend/fxml/homepage/profile.fxml"));
        pane.getChildren().removeAll();
        pane.getChildren().addAll(fxml);
        pane.setVisible(true);
        onprofile = true;
    }

    //****************************************************************************************************************//
    //MAIN LEFT PANEL SECTION

    @FXML
    private void AssButton(ActionEvent event) throws IOException {//ON ASSIGNMENT BUTTON CLICK

        Parent fxml;
        fxml = FXMLLoader.load(getClass().getResource("/frontend/fxml/homepage/assignments.fxml"));
        pane.getChildren().removeAll();
        pane.getChildren().addAll(fxml);
        pane.setVisible(true);
        onassignment = true;
        System.out.println("Assignments button clicked");
    }

    @FXML
    private void meetingbutton(ActionEvent event) throws IOException {//ON MEETINGS BUTTON CLICK
        // Handle assignments button click event
        Parent fxml;
        fxml = FXMLLoader.load(getClass().getResource("/frontend/fxml/homepage/meetings.fxml"));
        pane.getChildren().removeAll();
        pane.getChildren().addAll(fxml);
        pane.setVisible(true);
        System.out.println("Project button clicked");
    }

    @FXML
    private void ProjectButton(ActionEvent event) throws IOException {//ON PROJECTS BUTTON CLICK
        // Handle assignments button click event
        Parent fxml;
        fxml = FXMLLoader.load(getClass().getResource("/frontend/fxml/homepage/projects.fxml"));
        pane.getChildren().removeAll();
        pane.getChildren().addAll(fxml);
        pane.setVisible(true);
        System.out.println("Project button clicked");
    }

    //****************************************************************************************************************//
    //SIGN OUT SECTION

    @FXML
    private void signout(ActionEvent event) throws IOException {//ON SIGNOUT BUTTON CLICK
        Parent fxml;
        fxml = FXMLLoader.load(getClass().getResource("/frontend/fxml/signin.fxml"));
        Utils.redirectScene(this.getClass(), event, "/frontend/fxml/signin.fxml", stage, scene);

    }

}

