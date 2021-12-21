package searchGUI;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import oburr.searching.random.Main_Menu;
import oburr.searching.webSearching.WebRecipe;
import oburr.user.User;

import java.io.IOException;
import java.util.ArrayList;

public class UpcomingEventMenu {

    Scene scene;
    Stage stage;
    Parent root;

    @FXML
    private HBox hbox;

    @FXML
    private ImageView img;

    @FXML
    private Label name;

    @FXML
    private Label r;

    @FXML
    private Button zartzurt;

    WebRecipe recipe;

    @FXML
    void getUpcomingRecpie(MouseEvent event) {
        User user = new User("faruk","enes");

        Main_Menu allRecipes = new Main_Menu(
                "AllRecipes.com",
                "https://www.allrecipes.com/recipes/", true, user, "dessert", ".category-page-list-inner > .karma-main-column.category-page-list-content__recipe-card.category-"
                + "page-list-content > div.card__category.card.component > .card__imageContainer" ,".card__title" ,".manual-link-behavior", ".paragraph > p",
                ".ingredients-item-name",
                "body > div.docked-sharebar-content-container > div > main > div.recipe-container.two-col-container > div.content.two-col-main-content.karma-content-container > "
                        + "div.recipe-content.two-col-content.karma-main-column > div.two-col-content-wrapper > div.recipe-content-container > div.lead-content-wrapper.two-col-style > "
                        + "div.lead-content-aside-wrapper > div > section > div:nth-child(1) > div:nth-child(3) > div.recipe-meta-item-body",
                ".recipe-nutrition-section.partial > .section-body"
        );

        recipe = allRecipes.getRecipe();

        hbox.setVisible(true);
        name.setText(recipe.getRecipeName());
        String recipeImg1URL = recipe.getImageUrl();
        Image recipeImg1 = new Image(recipeImg1URL);
        img.setImage(recipeImg1);
        r.setText(recipe.getRecipeResource());
    }

    @FXML
    void openRecipe(Event event){
        String name = null;
        Image img = null;
        String recipeSteps = null;
        String time = null;
        String ingr = "";
        String facts = null;
        String source = null;
        String calories = "Calories: ";


        name = recipe.getRecipeName();
        String url = recipe.getImageUrl();
        img = new Image(url);
        recipeSteps = recipe.getRecipeSteps();
        if ((recipe.getTotalTime()) == 0) {
            time = "NA";
        } else {
            time = recipe.getTimeInfo();
        }
        ArrayList ingrList = recipe.getRecipeIngredients();
        for (int i = 0; i < ingrList.size(); i++) {
            ingr = ingr + " - " + ingrList.get(i) + "\n ";
        }
        facts = recipe.getNutritionFacts();
        source = recipe.getRecipeResource();
        if ((recipe.getCalories()) == 0) {
            calories = "NA";
        } else {
            calories = calories + String.valueOf(recipe.getCalories());
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("recipiePage.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RecipiePage recipiePage = loader.getController();
        recipiePage.displayRecipe(name, img, recipeSteps, time, ingr, facts, source, calories);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}

