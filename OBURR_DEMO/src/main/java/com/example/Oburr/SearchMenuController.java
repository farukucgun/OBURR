package com.example.Oburr;
/**
 *@OmerFiratBekiroglu
 *java version 15.0.2
 */

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import oburr.searching.webSearching.Resource;
import oburr.user.*;
import oburr.searching.webSearching.*;
import oburr.searching.sorting.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class SearchMenuController {

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

    //search menu controller
    Scene scene;
    Parent root;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private BorderPane bPane;

    @FXML
    private HBox hbox1, hbox2, hbox3, hbox4, hbox5, hbox6, hbox7, hbox8, hbox9, hbox10;

    @FXML
    private ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9, img10;

    @FXML
    private Label name1, name2, name3, name4, name5, name6, name7, name8, name9, name10;

    @FXML
    private Label r1, r2, r3, r4, r5, r6, r7, r8, r9, r10;

    @FXML
    private Button sourceButton1, sourceButton2, sourceButton3;

    @FXML
    private TextField searchTextField;

    @FXML
    private CheckBox sortTime, sortName, sortCalories, sortDiff, sortScore;


    ArrayList<SearchResult> recipeResults;
    Resource resource;

    void display() {
        hbox1.setVisible(true);
        name1.setText(recipeResults.get(0).getRecipeTitle());
        String recipeImg1URL = recipeResults.get(0).getImageUrl();
        Image recipeImg1 = new Image(recipeImg1URL);
        img1.setImage(recipeImg1);
        r1.setText(recipeResults.get(0).getResource());

        hbox2.setVisible(true);
        name2.setText(recipeResults.get(1).getRecipeTitle());
        String recipeImg2URL = recipeResults.get(1).getImageUrl();
        Image recipeImg2 = new Image(recipeImg2URL);
        img2.setImage(recipeImg2);
        r2.setText(recipeResults.get(1).getResource());

        hbox3.setVisible(true);
        name3.setText(recipeResults.get(2).getRecipeTitle());
        String recipeImg3URL = recipeResults.get(2).getImageUrl();
        Image recipeImg3 = new Image(recipeImg3URL);
        img3.setImage(recipeImg3);
        r3.setText(recipeResults.get(2).getResource());

        hbox4.setVisible(true);
        name4.setText(recipeResults.get(3).getRecipeTitle());
        String recipeImg4URL = recipeResults.get(3).getImageUrl();
        Image recipeImg4 = new Image(recipeImg4URL);
        img4.setImage(recipeImg4);
        r4.setText(recipeResults.get(3).getResource());

        hbox5.setVisible(true);
        name5.setText(recipeResults.get(4).getRecipeTitle());
        String recipeImg5URL = recipeResults.get(4).getImageUrl();
        Image recipeImg5 = new Image(recipeImg5URL);
        img5.setImage(recipeImg5);
        r5.setText(recipeResults.get(4).getResource());

        hbox6.setVisible(true);
        name6.setText(recipeResults.get(5).getRecipeTitle());
        String recipeImg6URL = recipeResults.get(5).getImageUrl();
        Image recipeImg6 = new Image(recipeImg6URL);
        img6.setImage(recipeImg6);
        r6.setText(recipeResults.get(5).getResource());

        hbox7.setVisible(true);
        name7.setText(recipeResults.get(6).getRecipeTitle());
        String recipeImg7URL = recipeResults.get(6).getImageUrl();
        Image recipeImg7 = new Image(recipeImg7URL);
        img7.setImage(recipeImg7);
        r7.setText(recipeResults.get(6).getResource());

        hbox8.setVisible(true);
        name8.setText(recipeResults.get(7).getRecipeTitle());
        String recipeImg8URL = recipeResults.get(7).getImageUrl();
        Image recipeImg8 = new Image(recipeImg8URL);
        img8.setImage(recipeImg8);
        r8.setText(recipeResults.get(7).getResource());

        hbox9.setVisible(true);
        name9.setText(recipeResults.get(8).getRecipeTitle());
        String recipeImg9URL = recipeResults.get(8).getImageUrl();
        Image recipeImg9 = new Image(recipeImg9URL);
        img9.setImage(recipeImg9);
        r9.setText(recipeResults.get(8).getResource());

        hbox10.setVisible(true);
        name10.setText(recipeResults.get(9).getRecipeTitle());
        String recipeImg10URL = recipeResults.get(9).getImageUrl();
        Image recipeImg10 = new Image(recipeImg10URL);
        img10.setImage(recipeImg10);
        r10.setText(recipeResults.get(9).getResource());
    }

    @FXML
    void sortByTime(ActionEvent event) {
        for (int i = 0; i < recipeResults.size(); i++) {
            recipeResults.get(i).setRecipe(resource.getRecipe(recipeResults.get(i)));
        }
        Collections.sort(recipeResults, new SortByName());

        display();

    }

    @FXML
    void sortByName(ActionEvent event) {
        for (int i = 0; i < recipeResults.size(); i++) {
            recipeResults.get(i).setRecipe(resource.getRecipe(recipeResults.get(i)));
        }
        Collections.sort(recipeResults, new SortByName());

        display();

    }


    @FXML
    void sortByCalories(ActionEvent event) {
        for (int i = 0; i < recipeResults.size(); i++) {
            recipeResults.get(i).setRecipe(resource.getRecipe(recipeResults.get(i)));
        }
        Collections.sort(recipeResults, new SortByCalories());

        display();

    }

    @FXML
    void sortByDifficultyLevel(ActionEvent event) {
        for (int i = 0; i < recipeResults.size(); i++) {
            recipeResults.get(i).setRecipe(resource.getRecipe(recipeResults.get(i)));
        }
        Collections.sort(recipeResults, new SortByDifficultyLevel());

        display();
    }

    @FXML
    void sortByRecipeScore(ActionEvent event) {
        for (int i = 0; i < recipeResults.size(); i++) {
            recipeResults.get(i).setRecipe(resource.getRecipe(recipeResults.get(i)));
        }
        Collections.sort(recipeResults, new SortByRecipeScore());

        display();
    }


    @FXML
    void getSearch(ActionEvent event) {

        String str = searchTextField.getText();

        Button b = (Button) event.getSource();

        User user = UserUpdater.returnUser();



        if (b == sourceButton1) {
            resource = new AllRecipesResource(
                    "AllRecipes.com",
                    "https://www.allrecipes.com/search/results/?", true,
                    user, "search=",
                    ".card__recipe.card__facetedSearchResult", ".card__title", ".manual-link-behavior",
                    ".paragraph > p", ".ingredients-item-name",
                    "body > div.docked-sharebar-content-container > div > main > div.recipe-container.two-col-container > div.content.two-col-main-content.karma-content-container > div.recipe-content.two-col-content.karma-main-column > div.two-col-content-wrapper > div.recipe-content-container > div.lead-content-wrapper.two-col-style > div.lead-content-aside-wrapper > div > section > div:nth-child(1) > div:nth-child(3) > div.recipe-meta-item-body",
                    ".recipe-nutrition-section.partial > .section-body"
            );

            recipeResults = resource.findResults(null, str);


            display();
        } else if (b == sourceButton2) {
            resource = new MyRecipesResource(
                    "MyRecipes.com",
                    "https://www.myrecipes.com/search?q=", false,
                    user, "",
                    "div.search-result", ".linkHoverStyle.search-result-title-link-text", ".search-result-title-link",
                    ".paragraph > p", ".elementFont__body.ingredients-item-name",
                    "body > div.docked-sharebar-content-container > div > main > div.recipe-container.two-col-container > div.content.two-col-main-content.karma-content-container > div.recipe-content.two-col-content.karma-main-column > div.two-col-content-wrapper > div.recipe-content-container > div.lead-content-wrapper.two-col-style > div.lead-content-aside-wrapper > div > section > div:nth-child(1) > div:nth-child(3) > div.recipe-meta-item-body.elementFont__subtitle",
                    ".recipeNutritionSectionBlock > .section-body"
            );
            recipeResults = resource.findResults(null, str);

            display();
        } else if (b == sourceButton3) {
            resource = new FoodNetworkResource("foodnetwork.com",
                    "https://www.foodnetwork.com/search/", false,
                    user, "", ".m-MediaBlock.o-ResultCard__m-MediaBlock > .m-MediaBlock__m-MediaWrap ",
                    ".m-MediaBlock.o-ResultCard__m-MediaBlock > .m-MediaBlock__m-TextWrap > .m-MediaBlock__a-Headline > [href] > .m-MediaBlock__a-HeadlineText",
                    "a", "li.o-Method__m-Step", ".o-Ingredients__a-Ingredient--CheckboxLabel",
                    ".recipeInfo > .o-RecipeInfo > .o-RecipeInfo__m-Level > li > .m-RecipeInfo__a-Description--Total.o-RecipeInfo__a-Description",
                    ""
            );
            recipeResults = resource.findResults(null, str);

            display();
        }
    }


    @FXML
    void openRecipe(Event event) {
        HBox h = (HBox) event.getSource();
        String name = null;
        Image img = null;
        String recipeSteps = null;
        String time = null;
        String ingr = "";
        String facts = null;
        String source = null;
        String calories = "Calories: ";
        boolean isSuitable = true;

        if (h == hbox1) {
            recipeResults.get(0).setRecipe(resource.getRecipe(recipeResults.get(0)));
            name = recipeResults.get(0).getRecipeTitle();
            String url = recipeResults.get(0).getImageUrl();

            img = new Image(url);
            recipeSteps = recipeResults.get(0).getRecipe().getRecipeSteps();
            isSuitable = recipeResults.get(0).getRecipe().getIsSuitable();
            if ((recipeResults.get(0).getRecipe().getTotalTime()) == 0) {
                time = "NA";
            } else {
                time = recipeResults.get(0).getRecipe().getTimeInfo();
            }
            ArrayList ingrList = recipeResults.get(0).getRecipe().getRecipeIngredients();
            for (int i = 0; i < ingrList.size(); i++) {
                ingr = ingr + " - " + ingrList.get(i) + "\n ";
            }
            facts = recipeResults.get(0).getRecipe().getNutritionFacts();
            source = recipeResults.get(0).getRecipe().getRecipeResource();
            if ((recipeResults.get(0).getRecipe().getCalories()) == 0) {
                calories = "NA";
            } else {
                calories = calories + String.valueOf(recipeResults.get(0).getRecipe().getCalories());
            }
        } else if (h == hbox2) {
            recipeResults.get(1).setRecipe(resource.getRecipe(recipeResults.get(1)));
            name = recipeResults.get(1).getRecipeTitle();
            String url = recipeResults.get(1).getImageUrl();
            img = new Image(url);
            recipeSteps = recipeResults.get(1).getRecipe().getRecipeSteps();
            isSuitable = recipeResults.get(1).getRecipe().getIsSuitable();

            ArrayList ingrList = recipeResults.get(1).getRecipe().getRecipeIngredients();
            for (int i = 0; i < ingrList.size(); i++) {
                ingr = ingr + " - " + ingrList.get(i) + "\n ";
            }
            facts = recipeResults.get(1).getRecipe().getNutritionFacts();
            source = recipeResults.get(1).getRecipe().getRecipeResource();
            if ((recipeResults.get(1).getRecipe().getCalories()) == 0) {
                calories = "NA";
            } else {
                calories = calories + String.valueOf(recipeResults.get(1).getRecipe().getCalories());
            }if ((recipeResults.get(1).getRecipe().getTotalTime()) == 0) {
                time = "NA";
            } else {
                time = recipeResults.get(1).getRecipe().getTimeInfo();
            }
        } else if (h == hbox3) {
            recipeResults.get(2).setRecipe(resource.getRecipe(recipeResults.get(2)));
            name = recipeResults.get(2).getRecipeTitle();
            String url = recipeResults.get(2).getImageUrl();
            img = new Image(url);
            recipeSteps = recipeResults.get(2).getRecipe().getRecipeSteps();
            isSuitable = recipeResults.get(2).getRecipe().getIsSuitable();
            if ((recipeResults.get(2).getRecipe().getTotalTime()) == 0) {
                time = "NA";
            } else {
                time = recipeResults.get(2).getRecipe().getTimeInfo();
            }
            ArrayList ingrList = recipeResults.get(2).getRecipe().getRecipeIngredients();
            for (int i = 0; i < ingrList.size(); i++) {
                ingr = ingr + " - " + ingrList.get(i) + "\n ";
            }
            facts = recipeResults.get(2).getRecipe().getNutritionFacts();
            source = recipeResults.get(2).getRecipe().getRecipeResource();
            if ((recipeResults.get(2).getRecipe().getCalories()) == 0) {
                calories = "NA";
            } else {
                calories = calories + String.valueOf(recipeResults.get(2).getRecipe().getCalories());
            }
        } else if (h == hbox4) {
            recipeResults.get(3).setRecipe(resource.getRecipe(recipeResults.get(3)));
            name = recipeResults.get(3).getRecipeTitle();
            String url = recipeResults.get(3).getImageUrl();
            img = new Image(url);
            recipeSteps = recipeResults.get(3).getRecipe().getRecipeSteps();
            isSuitable = recipeResults.get(3).getRecipe().getIsSuitable();
            if ((recipeResults.get(3).getRecipe().getTotalTime()) == 0) {
                time = "NA";
            } else {
                time = recipeResults.get(3).getRecipe().getTimeInfo();
            }
            ArrayList ingrList = recipeResults.get(3).getRecipe().getRecipeIngredients();
            for (int i = 0; i < ingrList.size(); i++) {
                ingr = ingr + " - " + ingrList.get(i) + "\n ";
            }
            facts = recipeResults.get(3).getRecipe().getNutritionFacts();
            source = recipeResults.get(3).getRecipe().getRecipeResource();
            if ((recipeResults.get(3).getRecipe().getCalories()) == 0) {
                calories = "NA";
            } else {
                calories = calories + String.valueOf(recipeResults.get(3).getRecipe().getCalories());
            }
        } else if (h == hbox5) {
            recipeResults.get(4).setRecipe(resource.getRecipe(recipeResults.get(4)));
            name = recipeResults.get(4).getRecipeTitle();
            String url = recipeResults.get(4).getImageUrl();
            img = new Image(url);
            recipeSteps = recipeResults.get(4).getRecipe().getRecipeSteps();
            isSuitable = recipeResults.get(4).getRecipe().getIsSuitable();
            if ((recipeResults.get(4).getRecipe().getTotalTime()) == 0) {
                time = "NA";
            } else {
                time = recipeResults.get(4).getRecipe().getTimeInfo();
            }
            ArrayList ingrList = recipeResults.get(4).getRecipe().getRecipeIngredients();
            for (int i = 0; i < ingrList.size(); i++) {
                ingr = ingr + " - " + ingrList.get(i) + "\n ";
            }
            facts = recipeResults.get(4).getRecipe().getNutritionFacts();
            source = recipeResults.get(4).getRecipe().getRecipeResource();
            if ((recipeResults.get(4).getRecipe().getCalories()) == 0) {
                calories = "NA";
            } else {
                calories = calories + String.valueOf(recipeResults.get(4).getRecipe().getCalories());
            }
        } else if (h == hbox6) {
            recipeResults.get(5).setRecipe(resource.getRecipe(recipeResults.get(5)));
            name = recipeResults.get(5).getRecipeTitle();
            String url = recipeResults.get(5).getImageUrl();
            img = new Image(url);
            recipeSteps = recipeResults.get(5).getRecipe().getRecipeSteps();
            isSuitable = recipeResults.get(5).getRecipe().getIsSuitable();
            if ((recipeResults.get(5).getRecipe().getTotalTime()) == 0) {
                time = "NA";
            } else {
                time = recipeResults.get(5).getRecipe().getTimeInfo();
            }
            ArrayList ingrList = recipeResults.get(5).getRecipe().getRecipeIngredients();
            for (int i = 0; i < ingrList.size(); i++) {
                ingr = ingr + " - " + ingrList.get(i) + "\n ";
            }
            facts = recipeResults.get(5).getRecipe().getNutritionFacts();
            source = recipeResults.get(5).getRecipe().getRecipeResource();
            if ((recipeResults.get(5).getRecipe().getCalories()) == 0) {
                calories = "NA";
            } else {
                calories = calories + String.valueOf(recipeResults.get(5).getRecipe().getCalories());
            }
        } else if (h == hbox7) {
            recipeResults.get(6).setRecipe(resource.getRecipe(recipeResults.get(1)));
            name = recipeResults.get(6).getRecipeTitle();
            String url = recipeResults.get(6).getImageUrl();
            img = new Image(url);
            recipeSteps = recipeResults.get(6).getRecipe().getRecipeSteps();
            isSuitable = recipeResults.get(6).getRecipe().getIsSuitable();
            if ((recipeResults.get(6).getRecipe().getTotalTime()) == 0) {
                time = "NA";
            } else {
                time = recipeResults.get(6).getRecipe().getTimeInfo();
            }

            ArrayList ingrList = recipeResults.get(6).getRecipe().getRecipeIngredients();
            for (int i = 0; i < ingrList.size(); i++) {
                ingr = ingr + " - " + ingrList.get(i) + "\n ";
            }
            facts = recipeResults.get(6).getRecipe().getNutritionFacts();
            source = recipeResults.get(6).getRecipe().getRecipeResource();
            if ((recipeResults.get(6).getRecipe().getCalories()) == 0) {
                calories = "NA";
            } else {
                calories = calories + String.valueOf(recipeResults.get(6).getRecipe().getCalories());
            }
        } else if (h == hbox8) {
            recipeResults.get(7).setRecipe(resource.getRecipe(recipeResults.get(7)));
            name = recipeResults.get(7).getRecipeTitle();
            String url = recipeResults.get(7).getImageUrl();
            img = new Image(url);
            recipeSteps = recipeResults.get(7).getRecipe().getRecipeSteps();
            isSuitable = recipeResults.get(7).getRecipe().getIsSuitable();
            if ((recipeResults.get(7).getRecipe().getTotalTime()) == 0) {
                time = "NA";
            } else {
                time = recipeResults.get(7).getRecipe().getTimeInfo();
            }
            ArrayList ingrList = recipeResults.get(7).getRecipe().getRecipeIngredients();
            for (int i = 0; i < ingrList.size(); i++) {
                ingr = ingr + " - " + ingrList.get(i) + "\n ";
            }
            facts = recipeResults.get(7).getRecipe().getNutritionFacts();
            source = recipeResults.get(7).getRecipe().getRecipeResource();
            if ((recipeResults.get(7).getRecipe().getCalories()) == 0) {
                calories = "NA";
            } else {
                calories = calories + String.valueOf(recipeResults.get(7).getRecipe().getCalories());
            }
        } else if (h == hbox9) {
            recipeResults.get(8).setRecipe(resource.getRecipe(recipeResults.get(8)));
            name = recipeResults.get(8).getRecipeTitle();
            String url = recipeResults.get(8).getImageUrl();
            img = new Image(url);
            recipeSteps = recipeResults.get(8).getRecipe().getRecipeSteps();
            isSuitable = recipeResults.get(8).getRecipe().getIsSuitable();
            if ((recipeResults.get(8).getRecipe().getTotalTime()) == 0) {
                time = "NA";
            } else {
                time = recipeResults.get(8).getRecipe().getTimeInfo();
            }
            ArrayList ingrList = recipeResults.get(8).getRecipe().getRecipeIngredients();
            for (int i = 0; i < ingrList.size(); i++) {
                ingr = ingr + " - " + ingrList.get(i) + "\n ";
            }
            facts = recipeResults.get(8).getRecipe().getNutritionFacts();
            source = recipeResults.get(8).getRecipe().getRecipeResource();
            if ((recipeResults.get(8).getRecipe().getCalories()) == 0) {
                calories = "NA";
            } else {
                calories = calories + String.valueOf(recipeResults.get(8).getRecipe().getCalories());
            }
        } else if (h == hbox10) {
            recipeResults.get(9).setRecipe(resource.getRecipe(recipeResults.get(9)));
            name = recipeResults.get(9).getRecipeTitle();
            String url = recipeResults.get(9).getImageUrl();
            img = new Image(url);
            recipeSteps = recipeResults.get(9).getRecipe().getRecipeSteps();
            isSuitable = recipeResults.get(9).getRecipe().getIsSuitable();
            if ((recipeResults.get(9).getRecipe().getTotalTime()) == 0) {
                time = "NA";
            } else {
                time = recipeResults.get(9).getRecipe().getTimeInfo();
            }
            ArrayList ingrList = recipeResults.get(9).getRecipe().getRecipeIngredients();
            for (int i = 0; i < ingrList.size(); i++) {
                ingr = ingr + " - " + ingrList.get(i) + "\n ";
            }
            facts = recipeResults.get(9).getRecipe().getNutritionFacts();
            source = recipeResults.get(9).getRecipe().getRecipeResource();
            if ((recipeResults.get(9).getRecipe().getCalories()) == 0) {
                calories = "NA";
            } else {
                calories = calories + String.valueOf(recipeResults.get(9).getRecipe().getCalories());
            }
        }


        FXMLLoader loader = new FXMLLoader(getClass().getResource("RecipePage.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RecipePageController recipiePage = loader.getController();
        recipiePage.displayRecipe(name, img, recipeSteps, time, ingr, facts, source, calories, isSuitable);
        recipiePage.fromSearch(  );

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}