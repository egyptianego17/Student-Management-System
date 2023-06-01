package frontend.controllers;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.stage.Stage;
public class StudentSystemController {
	
	private Stage stage;
	private Scene scene;
	
    @FXML
    void signin(ActionEvent event) throws IOException {
		Utils.redirectScene(this.getClass(),event,"/frontend/fxml/signin_user.fxml", stage, scene);
    }

    @FXML
    void signup(ActionEvent event) throws IOException {
		Utils.redirectScene(this.getClass(),event,"/frontend/fxml/signup.fxml", stage, scene);
    }
    
    @FXML
    void systemexit(ActionEvent event) {
    	System.exit(0);
    }

}