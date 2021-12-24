package com.example.fxtester;
/**
 *@OmerFiratBekiroglu
 *java version 15.0.2
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RecipePageController {


    @FXML
    private BorderPane borderPane;

    @FXML
    private TextArea facts;

    @FXML
    private Label header;

    @FXML
    private Label time;

    @FXML
    private Label calories;

    @FXML
    private Label source;

    @FXML
    private ImageView img;

    @FXML
    private TextArea recipe;

    @FXML
    private TextArea ingr;
    @FXML
    private Label errorLabel;

    public void displayRecipe (String name, Image image, String recipeSteps, String timeInfo,
                               String ingrList, String ntrFacts, String recipeSource,String cal, boolean isSuitable ){
        if( !isSuitable){
            errorLabel.setVisible(true);
        }
        header.setText(name);
        img.setImage(image);
        recipe.setWrapText(true);
        recipe.setText(recipeSteps);
        time.setText(timeInfo);
        ingr.setWrapText(true);
        ingr.setText(ingrList);
        facts.setWrapText(true);
        facts.setText(ntrFacts);
        source.setText(recipeSource);
        calories.setText(cal);
    }

    @FXML
    private Button backToSearch;
    @FXML
    private Button backToIngredientSearch;
    @FXML
    private Button backToDiscover;
    @FXML
    private Button backToRandom;
    @FXML
    private Button backToMenu;


    public void returnSearch( ActionEvent e ){
        changeScene( "SearchMenuScene.fxml", e );
    }
    public void returnIngredientSearch( ActionEvent e ){
        changeScene( "IngredientSearchScene.fxml", e );
    }
    public void returnRandomSearch( ActionEvent e ){
        changeScene( "RandomSearchScene.fxml", e );
    }
    public void returnDiscover( ActionEvent e ){
        changeScene( "DiscoverMenu.fxml", e );
    }
    public void returnMenu( ActionEvent e ){
        changeScenetoMenu( e );
    }

    public void fromMenu(  ){
        backToMenu.setVisible(true);
    }
    public void fromSearch( ){
        backToSearch.setVisible(true);
    }
    public void fromIngredientSearch( ){
        backToIngredientSearch.setVisible(true);
    }
    public void fromDiscover( ){
        backToDiscover.setVisible(true);
    }
    public void fromRandomSearch(  ){
        backToRandom.setVisible(true);
    }

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

}

