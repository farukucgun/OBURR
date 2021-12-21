/**
 *@OmerFiratBekiroglu
 *java version 15.0.2
 */
package searchGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RecipiePage {

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

    public void displayRecipe (String name, Image image, String recipeSteps, String timeInfo,
                               String ingrList, String ntrFacts, String recipeSource,String cal){
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
    void retunSearchMenu(ActionEvent event) {
        loadUI("searchMenu");
    }

    void loadUI (String ui){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui+".fxml"));
        } catch (IOException e) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
        }
        borderPane.setCenter(root);
    }

}

