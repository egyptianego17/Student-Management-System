package frontend.controllers;

import backend.users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Utils {
    public static void redirectScene(Class from, ActionEvent event, String scenePath, Stage stage, Scene scene) throws IOException {

        Parent root = FXMLLoader.load(from.getResource(scenePath));

        stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);

        stage.show();

    }

    public static void redirectScene(Class from, MouseEvent event, String scenePath, Stage stage, Scene scene) throws IOException {

        Parent root = FXMLLoader.load(from.getResource(scenePath));

        stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);

        stage.show();

    }
}
