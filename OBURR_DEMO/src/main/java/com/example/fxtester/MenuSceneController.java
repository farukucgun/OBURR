package com.example.fxtester;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class MenuSceneController {


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
    //Menu scene

    @FXML
    private ImageView menuLogo;
    @FXML
    private Button menuEatingProgramButton;
    @FXML
    private Button menuSearchingButton;
    @FXML
    private Button menuSettingsButton;
    @FXML
    private Button menuFavoritesButton;
    @FXML
    private Button menuProfileButton;
    @FXML
    private Button menuDropMenuButton;
    @FXML
    private Rectangle menuBar;


    public void bringMenu( ActionEvent event ){

        TranslateTransition slider = new TranslateTransition();
        slider.setDuration(Duration.seconds(0.8));
        slider.setNode( menuBar );
        slider.setToX( 200 );
        slider.play();


        slider.setOnFinished( (e)-> {
            menuLogo.setVisible(true);
            menuProfileButton.setVisible(true);
            menuFavoritesButton.setVisible(true);
            menuSettingsButton.setVisible(true);
            menuSearchingButton.setVisible(true);
            menuEatingProgramButton.setVisible(true);
            menuDropMenuButton.setVisible(true);
        } );
    }
    public void dropMenu( ActionEvent event ){

        TranslateTransition slider = new TranslateTransition();
        slider.setDuration(Duration.seconds(0.5));
        slider.setNode( menuBar );
        slider.setToX( -200 );
        slider.play();

        menuLogo.setVisible(false);
        menuProfileButton.setVisible(false);
        menuFavoritesButton.setVisible(false);
        menuSettingsButton.setVisible(false);
        menuSearchingButton.setVisible(false);
        menuEatingProgramButton.setVisible(false);
        menuDropMenuButton.setVisible(false);
        slider.setOnFinished( (e)-> {} );
    }

    public void toProfileScene( ActionEvent e ){
        changeScene( "ProfileScene.fxml", e );
    }

}
