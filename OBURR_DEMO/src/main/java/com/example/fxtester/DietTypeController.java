/**
 * @EnesBektas
 * java version 14.0.2
 */
package com.example.fxtester;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import oburr.user.UserUpdater;

import java.io.IOException;

public class DietTypeController {

    // Constant lists for liked items according to diet type
    public static final String[] PROTEIN_BASED_LIKES = { "Egg", "Beef", "Chicken", "Turkey", "Bean", "Fish", "Lentil", "Oat", "Yogurt" };
    public static final String[] KETO_LIKES = { "Bacon", "Butter", "Cheese", "Chicken", "Cream", "Egg", "Onion", "Pepper", "Steak", "Tomatoes", };
    public static final String[] LOW_CARB_LIKES = { "Apple", "Cauliflower", "Egg", "Fish", "Meat", "Nut", "Olive oil", "Strawberry", "Yogurt" };
    public static final String[] GLUTEN_FREE_LIKES = { "Bulgur", "Butter", "Cheese", "Corn", "Fish", "Meat", "Milk", "Potato", "Rice", "Soy" };
    public static final String[] PALEO_LIKES = { "Apple", "Beef", "Broccoli", "Carrot", "Chicken", "Egg", "Garlic", "Onion", "Pepper", "Potato", "Tomatoes" };


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


    // DietType Scene

    // GUI variables (radio buttons are connected)
    @FXML
    private RadioButton radioButton1;
    @FXML
    private RadioButton radioButton2;
    @FXML
    private RadioButton radioButton3;
    @FXML
    private RadioButton radioButton4;
    @FXML
    private RadioButton radioButton5;
    @FXML
    private RadioButton radioButton6;
    @FXML
    private RadioButton radioButton7;
    @FXML
    private RadioButton radioButton8;

    /**
     * Selects radio buttons according to information from database
     */
    public void initialize(){
        String dietType = UserUpdater.dietTypeFromDatabase();
        if( radioButton1.getText().equals( dietType ) ){
            radioButton1.setSelected(true);
        }
        else if( radioButton2.getText().equals( dietType ) ){
            radioButton2.setSelected(true);
        }
        else if( radioButton3.getText().equals( dietType ) ){
            radioButton3.setSelected(true);
        }
        else if( radioButton4.getText().equals( dietType ) ){
            radioButton4.setSelected(true);
        }
        else if( radioButton5.getText().equals( dietType ) ){
            radioButton5.setSelected(true);
        }
        else if( radioButton6.getText().equals( dietType ) ){
            radioButton6.setSelected(true);
        }
        else if( radioButton7.getText().equals( dietType ) ){
            radioButton7.setSelected(true);
        }
        else if( radioButton8.getText().equals( dietType ) ){
            radioButton8.setSelected(true);
        }
    }

    /**
     * Changes diet type, and updates banned items and liked items according to diet type
     * @param e action event
     */
    public void radioButtonListener( ActionEvent e ){
        RadioButton radioButton = (RadioButton) e.getSource();
        UserUpdater.changeDietType( radioButton.getText() );
        UserUpdater.changeBannedItems( radioButton.getText() );
        if( radioButton.getText().equalsIgnoreCase( "protein-based" ) ){
            for( String i: PROTEIN_BASED_LIKES){
                UserUpdater.addLikedItem( i );
            }
        }
        else if( radioButton.getText().equalsIgnoreCase( "keto" ) ){
            for( String i: KETO_LIKES){
                UserUpdater.addLikedItem( i );
            }
        }
        else if( radioButton.getText().equalsIgnoreCase( "low carb" ) ){
            for( String i: LOW_CARB_LIKES){
                UserUpdater.addLikedItem( i );
            }
        }
        else if( radioButton.getText().equalsIgnoreCase( "gluten free" ) ){
            for( String i: GLUTEN_FREE_LIKES){
                UserUpdater.addLikedItem( i );
            }
        }
        else if( radioButton.getText().equalsIgnoreCase( "paleo" ) ){
            for( String i: PALEO_LIKES){
                UserUpdater.addLikedItem( i );
            }
        }
        removeLikedItem();
    }

    /**
     * Removes liked item when diet type is changed
     */
    public void removeLikedItem( ){
        if( !radioButton1.isSelected() ){
            for( String i: PROTEIN_BASED_LIKES){
                UserUpdater.removeLikedItem( i );
            }
        }
        if( !radioButton2.isSelected() ){
            for( String i: KETO_LIKES){
                UserUpdater.removeLikedItem( i );
            }
        }
        if( !radioButton3.isSelected() ){
            for( String i: LOW_CARB_LIKES){
                UserUpdater.removeLikedItem( i );
            }
        }
        if( !radioButton4.isSelected() ){
            for( String i: GLUTEN_FREE_LIKES){
                UserUpdater.removeLikedItem( i );
            }
        }
        if( !radioButton7.isSelected() ){
            for( String i: PALEO_LIKES){
                UserUpdater.removeLikedItem( i );
            }
        }
    }

    /**
     * Updates diet type and liked info that are in the database
     * @param e action event
     */
    public void updateDietType( ActionEvent e ){
        UserUpdater.updateDietTypeInfo();
        UserUpdater.updateLikedInfo();
    }

    /**
     * ActionListener for reset to default button
     * Resets diet type to default (no diet type
     * @param e action event
     */
    public void reset( ActionEvent e ){
        radioButton1.setSelected(false);
        radioButton2.setSelected(false);
        radioButton3.setSelected(false);
        radioButton4.setSelected(false);
        radioButton5.setSelected(false);
        radioButton6.setSelected(false);
        radioButton7.setSelected(false);
        radioButton8.setSelected(false);

        UserUpdater.removeDietType();
        UserUpdater.changeBannedItems( "");
        removeLikedItem();
    }

    /**
     * Turns back to profile scene
     * @param e action event
     */
    public void back( ActionEvent e ){
        changeScene( "ProfileScene.fxml", e );
    }



    // If this scene is displayed from register

    // GUI variables
    @FXML
    private Button saveButton;
    @FXML
    private Button backButton;
    @FXML
    private Button skipButton;
    @FXML
    private Button proceedButton;
    @FXML
    private Label greetingLabel1;
    @FXML
    private Label greetingLabel2;
    @FXML
    private Label dietTypeLabel;

    /**
     * Changes visibility of the buttons
     */
    public void fromRegister(){
        saveButton.setVisible(false);
        backButton.setVisible(false);
        dietTypeLabel.setVisible(false);
        skipButton.setVisible(true);
        proceedButton.setVisible(true);
        greetingLabel1.setVisible(true);
        greetingLabel2.setVisible(true);
    }

    /**
     * Changes scene to likes scene and calls a method from LikesSceneController to initialize likes scene
     * @param event action event
     */
    public void changeSceneFromRegister( ActionEvent event ){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LikesScene.fxml"));
            Parent root = loader.load();
            LikesSceneController controller = loader.getController();
            controller.fromRegister();

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene( scene );
            stage.show();
        }
        catch ( IOException e ){}
    }

    /**
     * ActionListener for proceed button
     * @param e action event
     */
    public void proceed( ActionEvent e ){
        updateDietType( e );
        changeSceneFromRegister( e );
    }
}
