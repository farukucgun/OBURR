/**
 * @EnesBektas
 * java version 14.0.2
 */
package com.example.fxtester;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import oburr.searching.Main_Menu;
import oburr.searching.webSearching.WebRecipe;
import oburr.user.User;
import oburr.user.UserUpdater;

import java.io.IOException;
import java.util.ArrayList;

public class MenuSceneController {


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

    //Menu scene

    // GUI variables
    @FXML
    private ImageView menuLogo;
    @FXML
    private Button menuSearchButton;
    @FXML
    private Button menuIngredientSearchButton;
    @FXML
    private Button menuDiscoverButton;
    @FXML
    private Button menuRandomSearchButton;
    @FXML
    private Button menuSettingsButton;
    @FXML
    private Button menuProfileButton;
    @FXML
    private Button menuDropMenuButton;
    @FXML
    private Rectangle menuBar;

    /**
     * Animation for bringing left menu
     * @param event action event
     */
    public void bringMenu( ActionEvent event ){

        TranslateTransition slider = new TranslateTransition();
        slider.setDuration(Duration.seconds(0.8));
        slider.setNode( menuBar );
        slider.setToX( 200 );
        slider.play();


        slider.setOnFinished( (e)-> {
            menuLogo.setVisible(true);
            menuProfileButton.setVisible(true);
            menuSettingsButton.setVisible(true);
            menuSearchButton.setVisible(true);
            menuIngredientSearchButton.setVisible(true);
            menuDiscoverButton.setVisible(true);
            menuRandomSearchButton.setVisible(true);
            menuDropMenuButton.setVisible(true);
        } );
    }

    /**
     * Animation for dropping left menu
     * @param event action event
     */
    public void dropMenu( ActionEvent event ){

        TranslateTransition slider = new TranslateTransition();
        slider.setDuration(Duration.seconds(0.5));
        slider.setNode( menuBar );
        slider.setToX( -200 );
        slider.play();

        menuLogo.setVisible(false);
        menuProfileButton.setVisible(false);
        menuSettingsButton.setVisible(false);
        menuSearchButton.setVisible(false);
        menuIngredientSearchButton.setVisible(false);
        menuDiscoverButton.setVisible(false);
        menuRandomSearchButton.setVisible(false);
        menuDropMenuButton.setVisible(false);
        slider.setOnFinished( (e)-> {} );
    }

    /**
     * Turns scene to profile scene
     * @param e action event
     */
    public void toProfileScene( ActionEvent e ){
        changeScene( "ProfileScene.fxml", e );
    }
    /**
     * Turns scene to settings scene
     * @param e action event
     */
    public void toSettingsScene( ActionEvent e ){
        changeScene( "SettingsScene.fxml", e );
    }
    /**
     * Turns scene to search by name scene
     * @param e action event
     */
    public void toSearchScene( ActionEvent e ){
        changeScene( "SearchMenuScene.fxml", e );
    }
    /**
     * Turns scene to search by ingredients scene
     * @param e action event
     */
    public void toIngredientSearchScene( ActionEvent e ){
        changeScene( "IngredientSearchScene.fxml", e );
    }
    /**
     * Turns scene to random search scene
     * @param e action event
     */
    public void toRandomSearchScene( ActionEvent e ){
        changeScene( "RandomSearchScene.fxml", e );
    }
    /**
     * Turns scene to discover scene
     * @param e action event
     */
    public void toDiscoverScene( ActionEvent e ){
        changeScene( "DiscoverMenu.fxml", e );
    }


    // Upcoming events

    Scene scene;
    Parent root;
    WebRecipe recipe;

    // GUI variables
    @FXML
    private HBox hbox;
    @FXML
    private ImageView img;
    @FXML
    private Label name;
    @FXML
    private Label r;

    /**
     * Opens recipe that is in the menu scene
     * @param event action event
     */
    public void openRecipe(Event event){
        String name = null;
        Image img = null;
        String recipeSteps = null;
        String time = null;
        String ingr = "";
        String facts = null;
        String source = null;
        String calories = "Calories: ";
        boolean isSuitable = true;


        name = recipe.getRecipeName();
        String url = recipe.getImageUrl();
        img = new Image(url);
        recipeSteps = recipe.getRecipeSteps();
        isSuitable = recipe.getIsSuitable();
        if ((recipe.getTotalTime()) == 0) {
            time = "time: N/A";
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
            calories = "calories: N/A";
        } else {
            calories = calories + String.valueOf(recipe.getCalories());
        }


        FXMLLoader loader = new FXMLLoader(getClass().getResource("RecipePage.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RecipePageController recipiePage = loader.getController();
        recipiePage.displayRecipe(name, img, recipeSteps, time, ingr, facts, source, calories, isSuitable );
        recipiePage.fromMenu(  );

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Gets upcoming event
     * @param e action event
     */
    public void getUpcomingRecipe( ActionEvent e ) {
        User user = UserUpdater.returnUser();

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

        recipe = allRecipes.getRecipe( null );

        hbox.setVisible(true);
        name.setText(recipe.getRecipeName());
        String recipeImg1URL = recipe.getImageUrl();
        Image recipeImg1 = new Image(recipeImg1URL);
        img.setImage(recipeImg1);
        r.setText(recipe.getRecipeResource());
    }
}
