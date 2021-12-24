/**
 * @EnesBektas
 * java version 14.0.2
 */
package com.example.Oburr;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfileSceneController {

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

    /**
     * Changes scene to menu and calls a method from MenuSceneController to initialize menu scene
     * @param event action event
     */
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

    // Profile

    /**
     * Turns back to menu scene
     * @param e action event
     */
    public void back( ActionEvent e ){
        changeScenetoMenu( e );
    }

    /**
     * Turns scene to likes scene
     * @param e action event
     */
    public void toLikesScene( ActionEvent e ){
        changeScene( "LikesScene.fxml", e );
    }
    /**
     * Turns scene to dislikes scene
     * @param e action event
     */
    public void toDislikesScene( ActionEvent e ){
        changeScene( "DislikesScene.fxml", e );
    }
    /**
     * Turns scene to allergies scene
     * @param e action event
     */
    public void toAllergiesScene( ActionEvent e ){
        changeScene( "AllergiesScene.fxml", e );
    }
    /**
     * Turns scene to diet type scene
     * @param e action event
     */
    public void toDietTypeScene( ActionEvent e ) {
        changeScene( "DietTypeScene.fxml", e );
    }
    /**
     * Turns scene to add recipe scene
     * @param e action event
     */
    public void toAddRecipeScene( ActionEvent e ) {
        changeScene( "AddRecipeScene.fxml", e );
    }
    /**
     * Turns scene to added recipes scene
     * @param e action event
     */
    public void toAddedRecipesScene( ActionEvent e ) {
        changeScene( "AddedRecipesScene.fxml", e );
    }
}
