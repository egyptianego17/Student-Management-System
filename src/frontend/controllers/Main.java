package frontend.controllers;

import backend.Config;
import backend.Utils;
import backend.database.CRUD;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

//
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {//STARTING THE PROGRAM WITH THE FIRST PANELS
            Parent root = FXMLLoader.load(getClass().getResource("/frontend/fxml/signin.fxml"));
            Scene scene = new Scene(root);

            primaryStage.setTitle("Student System");
            primaryStage.initStyle(StageStyle.UNDECORATED);
            // Add final variables to capture initial cursor coordinates
            final double[] xOffset = {0};
            final double[] yOffset = {0};

// Add mouse event handlers to enable window dragging
            scene.setOnMousePressed(event -> {
                xOffset[0] = event.getSceneX();
                yOffset[0] = event.getSceneY();
            });

            scene.setOnMouseDragged(event -> {
                primaryStage.setX(event.getScreenX() - xOffset[0]);
                primaryStage.setY(event.getScreenY() - yOffset[0]);
            });

            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setResizable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        CRUD.readUserFromCSV(Config.usersCSVPath);
        backend.Utils.print("User database loaded successfully");
        CRUD.readMeetingFromCSV(Config.meetingsCSVPath);
        backend.Utils.print("Meetings database loaded successfully");
        CRUD.readAssignmentFromCSV(Config.assignmentsCSVPath);
        backend.Utils.print("Assignments database loaded successfully");
        CRUD.readSubmissionFromCSV(Config.submissionsCSVPath);
        backend.Utils.print("Submissions database loaded successfully");
        CRUD.readFeedbackFromCSV(Config.feedbackCSVPath);
        Utils.print("Feedback database loaded successfully");
        launch(args);
    }
}