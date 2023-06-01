package frontend.controllers;

import backend.Validators;
import backend.constants.Positions;
import backend.users.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Signup {

	private Stage stage;
	private Scene scene;

	@FXML
	private ComboBox<Positions> roles;

	@FXML
	private Text adminkeyerror; //ERRORS THAT APPEAR TO THE USER
	@FXML
	private Text emailerror;
	@FXML
	private Text fnameerror;
	@FXML
	private Text lnameerror;
	@FXML
	private Text passerror;

	@FXML
	private Button btnsignup; //BUTTONS THAT ARE AVAILABLE TO THE USER
	@FXML
	private Button btnreturn;

	@FXML
    private AnchorPane adminkey;
	@FXML
	private AnchorPane registeredpane;
	@FXML
	private AnchorPane rolepane;

	@FXML
	private VBox vbox;

	@FXML
    private Text signupswitch;
    @FXML
    private Text txtsignup;

    @FXML
    private TextField txtemail;
    @FXML
    private TextField txtfname;
    @FXML
    private TextField txtlname;

    @FXML
    private PasswordField txtpassword;
	@FXML
	private PasswordField txtadminkey;

    private boolean switched = false;
	private boolean errors = false;
    private Positions position;


	private ObservableList<Positions> UserOptions = FXCollections.
			observableArrayList(Positions.TEACHER, Positions.STUDENT);

	public ObservableList<Positions> getUserOptions() {
		return UserOptions;
	}//INITIALISING A LIST FOR THE COMBOBOX
	//****************************************************************************************************************//
	//THE 2 SIGNUP PAGES

    @FXML
    void adminsignup(MouseEvent event) { //ADMIN SIGNUP PAGE (ADD ADMIN KEY INTEGERATION)
    	if (switched) {
    		adminkey.setVisible(false);
        	txtsignup.setText("User Sign-up");
        	rolepane.setVisible(true);
        	signupswitch.setText("Click here to sign-up as an admin");
        	switched = false;
			passerror.setVisible(false);
			lnameerror.setVisible(false);
			fnameerror.setVisible(false);
			emailerror.setVisible(false);
			adminkeyerror.setVisible(false);
    	}
    	else {
	    	adminkey.setVisible(true);
	    	txtsignup.setText("Admin Sign-up");
	    	rolepane.setVisible(false);
	    	signupswitch.setText("Click here to sign-up as a user");
	    	switched = true;
			passerror.setVisible(false);
			lnameerror.setVisible(false);
			fnameerror.setVisible(false);
			emailerror.setVisible(false);
			adminkeyerror.setVisible(false);
    	}
    }

	@FXML
	void signup(ActionEvent event) throws Exception {//NORMAL USER SIGNUP PAGE
		errors = false;

		if (switched) {//CHECKING FOR ADMIN POSITION TO RETURN TO DATABASE
			position = Positions.ADMIN;
		}
		else {//CHECKING FOR NORMAL POSITION TO RETURN TO DATABASE
			position = roles.getValue();
		}

		//CHECKING IF INPUTS ARE ACCORDING TO GUIDELINES
		try{
			Validators.lengthValidator(txtfname.getText(), 3);
			fnameerror.setVisible(false);
		}catch (Exception e){
			if (e instanceof IOException) {
				fnameerror.setVisible(true);
				errors = true;
			}
		}
		try{
			Validators.lengthValidator(txtlname.getText(), 3);
			lnameerror.setVisible(false);
		}catch (Exception e){
			if (e instanceof IOException) {
				lnameerror.setVisible(true);
				errors=true;
			}
		}
		if (User.fetchByEmail(txtemail.getText()) != null){
			emailerror.setText("This email is already in use");
			emailerror.setVisible(true);
			errors=true;
		}
		else{
			emailerror.setText(null);
			emailerror.setVisible(false);
			try{
				Validators.emailValidator(txtemail.getText());
				emailerror.setText("");
				emailerror.setVisible(false);
			}catch (Exception e){
				if (e instanceof IOException) {
					emailerror.setText("Not a valid email");
					emailerror.setVisible(true);
					errors = true;
				}
			}
		}
		try{
			Validators.passwordValidator(txtpassword.getText());
			passerror.setText("");
			passerror.setVisible(false);
		}catch (Exception e){
			if (e instanceof IOException) {
				passerror.setText(e.getMessage());
				passerror.setVisible(true);
				errors = true;
			}
		}

		if (!errors) { //CHECKING IF THERE WERE ERRORS IN INPUTS
			User user = User.initializeChild(txtfname.getText(), txtlname.getText(), txtemail.getText(), txtpassword.getText(), position, false);
			registeredpane.setVisible(true);
			vbox.setDisable(true);
//			signupswitch.setDisable(true);
			btnreturn.setDisable(true);
			btnsignup.setDisable(true);
			System.out.println(user);
		}
	}

	//****************************************************************************************************************//
	// RETURNING TO THE SIGNUP PAGE

    @FXML
	void returnToMainPage(ActionEvent event) throws IOException { //RETURNING WITHOUT USER CREATION
		Utils.redirectScene(this.getClass(),event, "/frontend/fxml/signin.fxml", stage, scene);
	}

	@FXML
	void signedupreturn(ActionEvent event) throws IOException{ //RETURNING AFTER USER CREATION POPUP
		Utils.redirectScene(this.getClass(), event, "/frontend/fxml/signin.fxml", stage, scene);

	}


	//****************************************************************************************************************//
	//SCENE INITIALISATION
    public void initialize() {
		// TODO Auto-generated method stub
		roles.setValue(Positions.STUDENT);
		roles.setItems(getUserOptions());

	}

}
