package com.example.fxtester;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginSceneController {

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

    // Login scene

    @FXML
    private TextField loginUserName;
    @FXML
    private TextField loginPassword;
    @FXML
    private Label loginErrorLabel;

    public void signInButton( ActionEvent e ) {
        loginErrorLabel.setText("");
        if( oburr.user.Tester.login( loginUserName.getText(), loginPassword.getText() ) ){
            changeScene( "MenuScene.fxml", e );
        }
        else{
            // incorrect username or password
            loginErrorLabel.setText( "Incorrect username or password");
        }
    }

    public void toSignUp( ActionEvent e ) {
        changeScene( "RegisterScene.fxml", e );
    }

}
