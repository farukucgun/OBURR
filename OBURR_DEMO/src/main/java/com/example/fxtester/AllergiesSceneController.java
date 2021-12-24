package com.example.fxtester;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oburr.searching.Ingredient;
import oburr.user.UserUpdater;

import java.io.IOException;
import java.util.ArrayList;

public class AllergiesSceneController {

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

    // Allergies/Blacklist Scene

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
    @FXML
    private RadioButton radioButton9;
    @FXML
    private RadioButton radioButton10;

    @FXML
    private RadioButton userRadioButton1;
    @FXML
    private RadioButton userRadioButton2;
    @FXML
    private RadioButton userRadioButton3;
    @FXML
    private RadioButton userRadioButton4;
    @FXML
    private RadioButton userRadioButton5;
    @FXML
    private RadioButton userRadioButton6;
    @FXML
    private RadioButton userRadioButton7;
    @FXML
    private RadioButton userRadioButton8;
    @FXML
    private RadioButton userRadioButton9;
    @FXML
    private TextField enterIngredient;


    public void initialize(){
        ArrayList<Ingredient> tmp = UserUpdater.allergensFromDatabase();
        System.out.println(tmp);
        for( Ingredient i: tmp ){
            if( radioButton1.getText().equalsIgnoreCase( i.toString() ) ){
                radioButton1.setSelected(true);
            }
            else if( radioButton2.getText().equalsIgnoreCase( i.toString() ) ){
                radioButton2.setSelected(true);
            }
            else if( radioButton3.getText().equalsIgnoreCase( i.toString() ) ){
                radioButton3.setSelected(true);
            }
            else if( radioButton4.getText().equalsIgnoreCase( i.toString() ) ){
                radioButton4.setSelected(true);
            }
            else if( radioButton5.getText().equalsIgnoreCase( i.toString() ) ){
                radioButton5.setSelected(true);
            }
            else if( radioButton6.getText().equalsIgnoreCase( i.toString() ) ){
                radioButton6.setSelected(true);
            }
            else if( radioButton7.getText().equalsIgnoreCase( i.toString() ) ){
                radioButton7.setSelected(true);
            }
            else if( radioButton8.getText().equalsIgnoreCase( i.toString() ) ){
                radioButton8.setSelected(true);
            }
            else if( radioButton9.getText().equalsIgnoreCase( i.toString() ) ){
                radioButton9.setSelected(true);
            }
            else if( radioButton10.getText().equalsIgnoreCase( i.toString() ) ){
                radioButton10.setSelected(true);
            }
            else if( userRadioButton1.getText().equals( "RadioButton" ) ){
                userRadioButton1.setVisible( true );
                userRadioButton1.setText( i.toString() );
                userRadioButton1.setSelected(true);
            }
            else if( userRadioButton2.getText().equals( "RadioButton" ) ){
                userRadioButton2.setVisible( true );
                userRadioButton2.setText( i.toString() );
                userRadioButton2.setSelected(true);
            }
            else if( userRadioButton3.getText().equals( "RadioButton" ) ){
                userRadioButton3.setVisible( true );
                userRadioButton3.setText( i.toString() );
                userRadioButton3.setSelected(true);
            }
            else if( userRadioButton4.getText().equals( "RadioButton" ) ){
                userRadioButton4.setVisible( true );
                userRadioButton4.setText( i.toString() );
                userRadioButton4.setSelected(true);
            }
            else if( userRadioButton5.getText().equals( "RadioButton" ) ){
                userRadioButton5.setVisible( true );
                userRadioButton5.setText( i.toString() );
                userRadioButton5.setSelected(true);
            }
            else if( userRadioButton6.getText().equals( "RadioButton" ) ){
                userRadioButton6.setVisible( true );
                userRadioButton6.setText( i.toString() );
                userRadioButton6.setSelected(true);
            }
            else if( userRadioButton7.getText().equals( "RadioButton" ) ){
                userRadioButton7.setVisible( true );
                userRadioButton7.setText( i.toString() );
                userRadioButton7.setSelected(true);
            }
            else if( userRadioButton8.getText().equals( "RadioButton" ) ){
                userRadioButton8.setVisible( true );
                userRadioButton8.setText( i.toString() );
                userRadioButton8.setSelected(true);
            }
            else if( userRadioButton9.getText().equals( "RadioButton" ) ){
                userRadioButton9.setVisible( true );
                userRadioButton9.setText( i.toString() );
                userRadioButton9.setSelected(true);
            }
        }

    }



    public void buttonListener( ActionEvent e ){
        RadioButton radioButton = ( RadioButton ) e.getSource();
        if( !radioButton.getText().equals( "RadioButton") ){
            if( radioButton.isSelected() ){
                UserUpdater.addAllergenItem( radioButton.getText() );

            }
            else {
                UserUpdater.removeAllergenItem( radioButton.getText() );
            }
        }

    }

    public void addIngredient( ActionEvent e ){
        String str = enterIngredient.getText();
        if ( userRadioButton1.getText().equals( "RadioButton" ) ){
            userRadioButton1.setVisible(true);
            userRadioButton1.setText( str );
        }
        else if ( userRadioButton2.getText().equals( "RadioButton" ) ){
            userRadioButton2.setVisible(true);
            userRadioButton2.setText( str );
        }
        else if ( userRadioButton3.getText().equals( "RadioButton" ) ){
            userRadioButton3.setVisible(true);
            userRadioButton3.setText( str );
        }
        else if ( userRadioButton4.getText().equals( "RadioButton" ) ){
            userRadioButton4.setVisible(true);
            userRadioButton4.setText( str );
        }
        else if ( userRadioButton5.getText().equals( "RadioButton" ) ){
            userRadioButton5.setVisible(true);
            userRadioButton5.setText( str );
        }
        else if ( userRadioButton6.getText().equals( "RadioButton" ) ){
            userRadioButton6.setVisible(true);
            userRadioButton6.setText( str );
        }
        else if ( userRadioButton7.getText().equals( "RadioButton" ) ){
            userRadioButton7.setVisible(true);
            userRadioButton7.setText( str );
        }
        else if ( userRadioButton8.getText().equals( "RadioButton" ) ){
            userRadioButton8.setVisible(true);
            userRadioButton8.setText( str );
        }
        else if ( userRadioButton9.getText().equals( "RadioButton" ) ){
            userRadioButton9.setVisible(true);
            userRadioButton9.setText( str );
        }

    }
    public void updateAllergies( ActionEvent e ){
        UserUpdater.updateAllergenInfo();
    }


    public void back( ActionEvent e ){
        changeScene( "ProfileScene.fxml", e );
    }

    @FXML
    private Button saveButton;
    @FXML
    private Button backButton;
    @FXML
    private Button skipButton;
    @FXML
    private Button proceedButton;

    public void fromRegister(){
        saveButton.setVisible(false);
        backButton.setVisible(false);
        skipButton.setVisible(true);
        proceedButton.setVisible(true);
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

    public void changeSceneFromRegister( ActionEvent event )  {
        changeScenetoMenu( event );
    }
    public void proceed( ActionEvent e ){
        updateAllergies( e );
        changeScenetoMenu( e );
    }


}
