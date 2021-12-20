package com.example.fxtester;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfileSceneController {

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

    public void back( ActionEvent e ){
        changeScene( "MenuScene.fxml", e );
    }

    // Profile
    public void toLikesScene( ActionEvent e ){
        changeScene( "LikesScene.fxml", e );
    }
    public void toDislikesScene( ActionEvent e ){
        changeScene( "DislikesScene.fxml", e );
    }
    public void toAllergiesScene( ActionEvent e ){
        changeScene( "AllergiesScene.fxml", e );
    }
    public void toDietTypeScene( ActionEvent e ) {
        changeScene( "DietTypeScene.fxml", e );
    }

}
