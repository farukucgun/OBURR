package com.example.fxtester;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import oburr.user.UserUpdater;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AddRecipeSceneController {


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

    // Add Recipe Scene

    @FXML
    private TextField recipeName;
    @FXML
    private TextField recipeTime;
    @FXML
    private TextField recipeCalories;
    @FXML
    private TextArea recipeIngredients;
    @FXML
    private TextArea recipeSteps;
    @FXML
    private Label errorLabel;
    @FXML
    private ImageView imageView;
    @FXML
    private Label successMessage;

    private String extensionType;
    private BufferedImage bufferedImage;

    public void addPicture(ActionEvent e ) throws IOException {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);
        extensionType = file.getAbsolutePath().substring( file.getAbsolutePath().lastIndexOf('.' ) + 1 );
        System.out.println(extensionType);

        bufferedImage = ImageIO.read(file);
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        imageView.setImage(image);



    }

    public void addRecipe( ActionEvent e ){
        try{
            int calories = Integer.parseInt(recipeCalories.getText() );
            int timeAsMinutes = Integer.parseInt( recipeTime.getText() );
            UserUpdater.createOburrRecipe( recipeName.getText(), calories, timeAsMinutes, recipeIngredients.getText(), recipeSteps.getText(), extensionType, bufferedImage );
            successMessage.setVisible(true);
        }
        catch ( NumberFormatException exception ){
            errorLabel.setText( "Please enter an integer value for calories and time");
            errorLabel.setVisible(true);
            successMessage.setVisible(false);
        }
    }



    public void back( ActionEvent e ){
        changeScene( "ProfileScene.fxml", e );
    }



}
