/**
 * @EnesBektas
 * java version 14.0.2
 */
package com.example.fxtester;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import oburr.searching.OburrRecipe;
import oburr.user.UserUpdater;

import java.io.IOException;
import java.util.ArrayList;

public class AddedRecipesSceneController {

    // Change scene
    Stage stage;

    /**
     * Changes current scene
     * @param fxml fxml name of the next scene
     * @param event action event
     */
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

    // Added Recipes Scene

    // GUI variables
    @FXML
    private ImageView imageView1;
    @FXML
    private ImageView imageView2;
    @FXML
    private ImageView imageView3;
    @FXML
    private ImageView imageView4;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private HBox hbox1;
    @FXML
    private HBox hbox2;
    @FXML
    private HBox hbox3;
    @FXML
    private HBox hbox4;

    /**
     * Initializes scene according to added recipes by the current user
     */
    public void initialize(){
        ArrayList<OburrRecipe> recipeList = UserUpdater.returnAddedRecipes();
        int size = recipeList.size();
        if( size > 0 ){
            hbox1.setVisible(true);
            imageView1.setPreserveRatio(true);

            imageView1.setImage( SwingFXUtils.toFXImage( recipeList.get(0).getRecipeImage(), null ) );
            imageView1.setFitHeight( 150);
            imageView1.setFitWidth( 200);
            label1.setText( recipeList.get(0).getRecipeName() );
        }
        if( size > 1 ){
            imageView2.setPreserveRatio(true);

            imageView2.setFitHeight( 150);
            imageView2.setFitWidth( 200);
            hbox2.setVisible(true);
            imageView2.setImage( SwingFXUtils.toFXImage( recipeList.get(1).getRecipeImage(), null ) );

            label2.setText( recipeList.get(1).getRecipeName() );
        }
        if( size > 2 ){
            imageView3.setPreserveRatio(true);

            imageView3.setFitHeight( 150);
            imageView3.setFitWidth( 200);
            hbox3.setVisible(true);
            imageView3.setImage( SwingFXUtils.toFXImage( recipeList.get(2).getRecipeImage(), null ) );

            label3.setText( recipeList.get(2).getRecipeName() );
        }
        if( size > 3 ){
            hbox4.setVisible(true);
            imageView4.setImage( SwingFXUtils.toFXImage( recipeList.get(3).getRecipeImage(), null ) );
            imageView4.setFitHeight( 150);
            imageView4.setFitWidth( 200);
            label4.setText( recipeList.get(3).getRecipeName() );
        }



    }

    /**
     * Turns back to profile
     * @param e action event
     */
    public void back( ActionEvent e ){
        changeScene( "ProfileScene.fxml", e );
    }
}
