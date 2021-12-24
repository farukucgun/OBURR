package com.example.fxtester;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oburr.user.UserUpdater;

import java.io.IOException;

public class SettingsSceneController {


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

    @FXML
    private TextField userNameTextField;

    public void changeName( ActionEvent e ){
        UserUpdater.changeUserName( userNameTextField.getText() );
    }


    @FXML
    private PasswordField newPasswordField1;
    @FXML
    private PasswordField newPasswordField2;
    @FXML
    private Label errorLabel;
    @FXML
    private PasswordField oldPasswordField;

    public void changePassword( ActionEvent e ){
        String oldPassword = UserUpdater.returnPassword();

        if( !oldPasswordField.getText().equals( oldPassword ) ){
            errorLabel.setText( "Incorrect old password!");
            errorLabel.setVisible(true);
        }
        else{
            if( !newPasswordField1.getText().equals( newPasswordField2.getText() ) ){
                errorLabel.setText( "Passwords does not match!");
                errorLabel.setVisible(true);
            }
            else{
                UserUpdater.changePassword( newPasswordField1.getText() );
                errorLabel.setVisible(false);

            }
        }
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

    public void back( ActionEvent e ){
        changeScenetoMenu( e );
    }

}
