package com.example.fxtester;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterSceneController {


    // Change scene
    Stage stage;
    public void changeScene( String fxml, ActionEvent event )  {
        try{
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource( fxml ) );
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene( scene );
            stage.show();
        }
        catch ( IOException e ){}
    }

    // Register scene

    @FXML
    private TextField registerUserName;
    @FXML
    private TextField registerPassword1;
    @FXML
    private TextField registerPassword2;
    @FXML
    private Label registerErrorLabel;

    public void signUpButton( ActionEvent e ){
        registerErrorLabel.setText("");
        if( !registerPassword1.getText().equals( registerPassword2.getText() ) ){
            //Passwords are not the same
            registerErrorLabel.setText( "Passwords does not match");
        }
        else{
            if( oburr.user.Tester.register( registerUserName.getText(), registerPassword1.getText() ) ){
                changeSceneFromRegister( e );
            }
            else{
                // username already exist
                registerErrorLabel.setText( "Username is already in use");
            }
        }
    }

    public void toSignIn( ActionEvent e ) {
        changeScene( "LoginScene.fxml", e );
    }


    // From Register
    public void changeSceneFromRegister( ActionEvent event )  {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DietTypeScene.fxml"));
            Parent root = loader.load();
            DietTypeController controller = loader.getController();
            controller.fromRegister();

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene( scene );
            stage.show();
        }
        catch ( IOException e ){}
    }

}
