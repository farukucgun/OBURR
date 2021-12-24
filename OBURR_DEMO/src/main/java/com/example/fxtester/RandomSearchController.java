package com.example.fxtester;
/**
 *@OmerFiratBekiroglu
 *java version 15.0.2
 */

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
import javafx.stage.Stage;
import javafx.event.*;

import oburr.searching.webSearching.*;
import oburr.searching.random.*;
import oburr.user.User;
import oburr.user.UserUpdater;

import java.io.IOException;
import java.util.ArrayList;

public class RandomSearchController {

    // Change scene
    Stage stage;
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

    public void back( ActionEvent e ){
        changeScenetoMenu( e );
    }
    Scene scene;
    Parent root;

    @FXML
    private HBox hbox1,hbox2,hbox3;

    @FXML
    private ImageView img1,img2,img3;

    @FXML
    private Label name1,name2,name3;

    @FXML
    private Label r1,r2,r3;

    @FXML
    private Button breakfast,dessert,dinner,drink,holiday,lunch,quick,snack;

    ArrayList<SearchResult> randomList;
    Resource allRecipes;


    @FXML
    void getRandom(ActionEvent event){
        Button b = (Button)event.getSource();
        String url="https://www.allrecipes.com/recipes/";

        User user = UserUpdater.returnUser();


        allRecipes = new RandomRecommendationSource(
                "AllRecipes.com",
                "https://www.allrecipes.com/recipes/", true, user, "dessert", ".category-page-list-inner > .karma-main-column.category-page-list-content__recipe-card.category-"
                + "page-list-content > div.card__category.card.component > .card__imageContainer" ,".card__title" ,".manual-link-behavior", ".paragraph > p",
                ".ingredients-item-name",
                "body > div.docked-sharebar-content-container > div > main > div.recipe-container.two-col-container > div.content.two-col-main-content.karma-content-container > "
                        + "div.recipe-content.two-col-content.karma-main-column > div.two-col-content-wrapper > div.recipe-content-container > div.lead-content-wrapper.two-col-style > "
                        + "div.lead-content-aside-wrapper > div > section > div:nth-child(1) > div:nth-child(3) > div.recipe-meta-item-body",
                ".recipe-nutrition-section.partial > .section-body"
        );

        randomList = allRecipes.findResults(null,b.getId());

        hbox1.setVisible(true);
        name1.setText(randomList.get(0).getRecipeTitle());
        String recipeImg1URL = randomList.get(0).getImageUrl();
        Image recipeImg1 = new Image (recipeImg1URL);
        img1.setImage(recipeImg1);
        r1.setText(randomList.get(0).getResource());

        hbox2.setVisible(true);
        name2.setText(randomList.get(1).getRecipeTitle());
        String recipeImg2URL = randomList.get(1).getImageUrl();
        Image recipeImg2 = new Image (recipeImg2URL);
        img2.setImage(recipeImg2);
        r2.setText(randomList.get(1).getResource());

        hbox3.setVisible(true);
        name3.setText(randomList.get(2).getRecipeTitle());
        String recipeImg3URL = randomList.get(2).getImageUrl();
        Image recipeImg3 = new Image (recipeImg3URL);
        img3.setImage(recipeImg3);
        r3.setText(randomList.get(2).getResource());
    }


    @FXML
    void openRandomRecipe(Event event) {
        HBox h = (HBox)event.getSource();
        String name = null;
        Image img = null;
        String recipeSteps = null;
        String time = null;
        String ingr = "";
        String facts = null;
        String source = null;
        String calories = "Calories: ";
        boolean isSuitable = true;

        if( h == hbox1 ){
            randomList.get(0).setRecipe(allRecipes.getRecipe(randomList.get(0)));
            name = randomList.get(0).getRecipeTitle();
            String url = randomList.get(0).getImageUrl();
            img = new Image(url);
            recipeSteps = randomList.get(0).getRecipe().getRecipeSteps();

            isSuitable = randomList.get(0).getRecipe().getIsSuitable();
            time = randomList.get(0).getRecipe().getTimeInfo();
            ArrayList ingrList = randomList.get(0).getRecipe().getRecipeIngredients();
            for ( int i = 0; i < ingrList.size(); i++){
                ingr = ingr +" - " + ingrList.get(i)+"\n ";
            }
            facts = randomList.get(0).getRecipe().getNutritionFacts();
            source = randomList.get(0).getRecipe().getRecipeResource();
            calories = calories + String.valueOf(randomList.get(0).getRecipe().getCalories());
        }
        else if ( h == hbox2){
            randomList.get(1).setRecipe(allRecipes.getRecipe(randomList.get(1)));
            name = randomList.get(1).getRecipeTitle();
            String url = randomList.get(1).getImageUrl();
            img = new Image(url);
            recipeSteps = randomList.get(1).getRecipe().getRecipeSteps();
            isSuitable = randomList.get(1).getRecipe().getIsSuitable();
            time = randomList.get(1).getRecipe().getTimeInfo();
            ArrayList ingrList = randomList.get(1).getRecipe().getRecipeIngredients();
            for ( int i = 0; i < ingrList.size(); i++){
                ingr = ingr +" - " + ingrList.get(i)+"\n ";
            }
            facts = randomList.get(1).getRecipe().getNutritionFacts();
            source = randomList.get(1).getRecipe().getRecipeResource();
            calories = calories + String.valueOf(randomList.get(1).getRecipe().getCalories());
        }
        else if ( h == hbox3){
            randomList.get(2).setRecipe(allRecipes.getRecipe(randomList.get(2)));
            name = randomList.get(2).getRecipeTitle();
            String url = randomList.get(2).getImageUrl();
            img = new Image(url);
            recipeSteps = randomList.get(2).getRecipe().getRecipeSteps();
            isSuitable = randomList.get(2).getRecipe().getIsSuitable();
            time = randomList.get(2).getRecipe().getTimeInfo();
            ArrayList ingrList = randomList.get(2).getRecipe().getRecipeIngredients();
            for ( int i = 0; i < ingrList.size(); i++){
                ingr = ingr +" - " + ingrList.get(i)+"\n ";
            }
            facts = randomList.get(2).getRecipe().getNutritionFacts();
            source = randomList.get(2).getRecipe().getRecipeResource();
            calories = calories + String.valueOf(randomList.get(2).getRecipe().getCalories());
        }


        FXMLLoader loader = new FXMLLoader(getClass().getResource("RecipePage.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RecipePageController recipiePage = loader.getController();
        recipiePage.displayRecipe(name,img,recipeSteps,time,ingr,facts,source,calories,isSuitable);
        recipiePage.fromRandomSearch();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
