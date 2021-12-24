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
import oburr.user.UserUpdater;

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

    public void changeScenetoMenu( ActionEvent event )  {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuScene.fxml"));
            Parent root = loader.load();
            MenuSceneController controller = loader.getController();
            controller.getUpcomingRecipe( event );

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
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
        if( UserUpdater.login( loginUserName.getText(), loginPassword.getText() ) ){
            changeScenetoMenu( e );
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
