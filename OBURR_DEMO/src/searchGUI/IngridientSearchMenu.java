/**
 *@OmerFiratBekiroglu
 *java version 15.0.2
 */
package searchGUI;

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
import oburr.searching.*;
import oburr.searching.sorting.*;
import oburr.searching.webSearching.*;
import oburr.searching.webSearching.Resource;
import oburr.user.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class IngridientSearchMenu {
    Scene scene;
    Stage stage;
    Parent root;



    @FXML
    private AnchorPane anchorPane;

    @FXML
    private BorderPane bPane;


    @FXML
    private HBox hbox1,hbox2,hbox3,hbox4,hbox5,hbox6,hbox7,hbox8,hbox9,hbox10;

    @FXML
    private ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9,img10;

    @FXML
    private Label name1,name2,name3,name4,name5,name6,name7,name8,name9,name10;

    @FXML
    private Label r1,r2,r3,r4,r5,r6,r7,r8,r9,r10;

    @FXML
    private Button searchExecutionButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private CheckBox sortTime,sortName;


    ArrayList<SearchResult> allRecipesResults;
    Resource allRecipes;

    void display(){
        hbox1.setVisible(true);
        name1.setText(allRecipesResults.get(0).getRecipeTitle());
        String recipeImg1URL = allRecipesResults.get(0).getImageUrl();
        Image recipeImg1 = new Image (recipeImg1URL);
        img1.setImage(recipeImg1);
        r1.setText(allRecipesResults.get(0).getResource());

        hbox2.setVisible(true);
        name2.setText(allRecipesResults.get(1).getRecipeTitle());
        String recipeImg2URL = allRecipesResults.get(1).getImageUrl();
        Image recipeImg2 = new Image (recipeImg2URL);
        img2.setImage(recipeImg2);
        r2.setText(allRecipesResults.get(1).getResource());

        hbox3.setVisible(true);
        name3.setText(allRecipesResults.get(2).getRecipeTitle());
        String recipeImg3URL = allRecipesResults.get(2).getImageUrl();
        Image recipeImg3 = new Image (recipeImg3URL);
        img3.setImage(recipeImg3);
        r3.setText(allRecipesResults.get(2).getResource());

        hbox4.setVisible(true);
        name4.setText(allRecipesResults.get(3).getRecipeTitle());
        String recipeImg4URL = allRecipesResults.get(3).getImageUrl();
        Image recipeImg4 = new Image (recipeImg4URL);
        img4.setImage(recipeImg4);
        r4.setText(allRecipesResults.get(3).getResource());

        hbox5.setVisible(true);
        name5.setText(allRecipesResults.get(4).getRecipeTitle());
        String recipeImg5URL = allRecipesResults.get(4).getImageUrl();
        Image recipeImg5 = new Image (recipeImg5URL);
        img5.setImage(recipeImg5);
        r5.setText(allRecipesResults.get(4).getResource());

        hbox6.setVisible(true);
        name6.setText(allRecipesResults.get(5).getRecipeTitle());
        String recipeImg6URL = allRecipesResults.get(5).getImageUrl();
        Image recipeImg6 = new Image (recipeImg6URL);
        img6.setImage(recipeImg6);
        r6.setText(allRecipesResults.get(5).getResource());

        hbox7.setVisible(true);
        name7.setText(allRecipesResults.get(6).getRecipeTitle());
        String recipeImg7URL = allRecipesResults.get(6).getImageUrl();
        Image recipeImg7 = new Image (recipeImg7URL);
        img7.setImage(recipeImg7);
        r7.setText(allRecipesResults.get(6).getResource());

        hbox8.setVisible(true);
        name8.setText(allRecipesResults.get(7).getRecipeTitle());
        String recipeImg8URL = allRecipesResults.get(7).getImageUrl();
        Image recipeImg8 = new Image (recipeImg8URL);
        img8.setImage(recipeImg8);
        r8.setText(allRecipesResults.get(7).getResource());

        hbox9.setVisible(true);
        name9.setText(allRecipesResults.get(8).getRecipeTitle());
        String recipeImg9URL = allRecipesResults.get(8).getImageUrl();
        Image recipeImg9 = new Image (recipeImg9URL);
        img9.setImage(recipeImg9);
        r9.setText(allRecipesResults.get(8).getResource());

        hbox10.setVisible(true);
        name10.setText(allRecipesResults.get(9).getRecipeTitle());
        String recipeImg10URL = allRecipesResults.get(9).getImageUrl();
        Image recipeImg10 = new Image (recipeImg10URL);
        img10.setImage(recipeImg10);
        r10.setText(allRecipesResults.get(9).getResource());
    }

    @FXML
    void sortByTime(ActionEvent event) {
        for(int i=0; i<allRecipesResults.size();i++) {
            allRecipesResults.get(i).setRecipe(allRecipes.getRecipe(allRecipesResults.get(i)));
        }
        Collections.sort(allRecipesResults, new SortByTime());

        display();

    }

    @FXML
    void sortByName(ActionEvent event) {
        for(int i=0; i<allRecipesResults.size();i++) {
            allRecipesResults.get(i).setRecipe(allRecipes.getRecipe(allRecipesResults.get(i)));
        }
        Collections.sort(allRecipesResults, new SortByName());

        display();

    }


    @FXML
    void sortByCalories(ActionEvent event) {
        for(int i=0; i<allRecipesResults.size();i++) {
            allRecipesResults.get(i).setRecipe(allRecipes.getRecipe(allRecipesResults.get(i)));
        }
        Collections.sort(allRecipesResults, new SortByCalories());

        display();

    }

    @FXML
    void sortByDifficultyLevel(ActionEvent event){
        for(int i=0; i<allRecipesResults.size();i++) {
            allRecipesResults.get(i).setRecipe(allRecipes.getRecipe(allRecipesResults.get(i)));
        }
        Collections.sort(allRecipesResults, new SortByDifficultyLevel());

        display();
    }

    @FXML
    void sortByRecipeScore(ActionEvent event){
        for(int i=0; i<allRecipesResults.size();i++) {
            allRecipesResults.get(i).setRecipe(allRecipes.getRecipe(allRecipesResults.get(i)));
        }
        Collections.sort(allRecipesResults, new SortByRecipeScore());

        display();
    }

    @FXML
    void getSearch(ActionEvent event) {

        String str = searchTextField.getText();
        System.out.println("Text" + str);
        ArrayList <Ingredient> ingredientList= new ArrayList<Ingredient>();

        Scanner scn = new Scanner(str);
        scn.useDelimiter("[,]");

        while (scn.hasNext()){
            String s = scn.next();
            ingredientList.add(new Ingredient(s));
            System.out.println(s);
        }


        User user = new User("faruk","enes");

        ArrayList<Ingredient> allergies = new ArrayList<Ingredient>();
        allergies.add(new Ingredient("garlic"));
        allergies.add(new Ingredient("mustard"));

        ArrayList<Ingredient> likes = new ArrayList<Ingredient>();
        likes.add(new Ingredient("onion"));
        likes.add(new Ingredient("carrot"));

        ArrayList<Ingredient> dislikes = new ArrayList<Ingredient>();
        dislikes.add(new Ingredient("ham"));

        user.setAllergenIngredients(allergies);
        user.setLikedIngredients(likes);
        user.setDislikedIngredients(dislikes);

        allRecipes = new AllRecipesResource(
                "AllRecipes.com",
                "https://www.allrecipes.com/search/results/?", true,
                user, "search=",
                ".card__recipe.card__facetedSearchResult" ,".card__title" ,".manual-link-behavior",
                ".paragraph > p", ".ingredients-item-name",
                "body > div.docked-sharebar-content-container > div > main > div.recipe-container.two-col-container > div.content.two-col-main-content.karma-content-container > div.recipe-content.two-col-content.karma-main-column > div.two-col-content-wrapper > div.recipe-content-container > div.lead-content-wrapper.two-col-style > div.lead-content-aside-wrapper > div > section > div:nth-child(1) > div:nth-child(3) > div.recipe-meta-item-body",
                ".recipe-nutrition-section.partial > .section-body"
        );

        allRecipesResults = allRecipes.findResults(ingredientList,"");

        display();

    }


    @FXML
    void openRecipe(Event event) {
        HBox h = (HBox)event.getSource();
        String name = null;
        Image img = null;
        String recipeSteps = null;
        String time = null;
        String ingr = "";
        String facts = null;
        String source = null;
        String calories = "Calories: ";

        if( h == hbox1 ){
            allRecipesResults.get(0).setRecipe(allRecipes.getRecipe(allRecipesResults.get(0)));
            name = allRecipesResults.get(0).getRecipeTitle();
            String url = allRecipesResults.get(0).getImageUrl();
            img = new Image(url);
            recipeSteps = allRecipesResults.get(0).getRecipe().getRecipeSteps();
            if((allRecipesResults.get(0).getRecipe().getTotalTime()) == 0){
                time = "NA";
            }else {
                time = allRecipesResults.get(0).getRecipe().getTimeInfo();
            }
            ArrayList ingrList = allRecipesResults.get(0).getRecipe().getRecipeIngredients();
            for ( int i = 0; i < ingrList.size(); i++){
                ingr = ingr +" - " + ingrList.get(i)+"\n ";
            }
            facts = allRecipesResults.get(0).getRecipe().getNutritionFacts();
            source = allRecipesResults.get(0).getRecipe().getRecipeResource();
            if((allRecipesResults.get(0).getRecipe().getCalories()) == 0){
                calories = "NA";
            }else {
                calories = calories + String.valueOf(allRecipesResults.get(0).getRecipe().getCalories());
            }
        }
        else if ( h == hbox2){
            allRecipesResults.get(1).setRecipe(allRecipes.getRecipe(allRecipesResults.get(1)));
            name = allRecipesResults.get(1).getRecipeTitle();
            String url = allRecipesResults.get(1).getImageUrl();
            img = new Image(url);
            recipeSteps = allRecipesResults.get(1).getRecipe().getRecipeSteps();
            if((allRecipesResults.get(1).getRecipe().getTotalTime()) == 0){
                time = "NA";
            }else {
                time = allRecipesResults.get(1).getRecipe().getTimeInfo();
            }
            ArrayList ingrList = allRecipesResults.get(1).getRecipe().getRecipeIngredients();
            for ( int i = 0; i < ingrList.size(); i++){
                ingr = ingr +" - " + ingrList.get(i)+"\n ";
            }
            facts = allRecipesResults.get(1).getRecipe().getNutritionFacts();
            source = allRecipesResults.get(1).getRecipe().getRecipeResource();
            if((allRecipesResults.get(1).getRecipe().getCalories()) == 0){
                calories = "NA";
            }else {
                calories = calories + String.valueOf(allRecipesResults.get(1).getRecipe().getCalories());
            }
        }
        else if ( h == hbox3){
            allRecipesResults.get(2).setRecipe(allRecipes.getRecipe(allRecipesResults.get(2)));
            name = allRecipesResults.get(2).getRecipeTitle();
            String url = allRecipesResults.get(2).getImageUrl();
            img = new Image(url);
            recipeSteps = allRecipesResults.get(2).getRecipe().getRecipeSteps();
            if((allRecipesResults.get(2).getRecipe().getTotalTime()) == 0){
                time = "NA";
            }else {
                time = allRecipesResults.get(2).getRecipe().getTimeInfo();
            }
            ArrayList ingrList = allRecipesResults.get(2).getRecipe().getRecipeIngredients();
            for ( int i = 0; i < ingrList.size(); i++){
                ingr = ingr +" - " + ingrList.get(i)+"\n ";
            }
            facts = allRecipesResults.get(2).getRecipe().getNutritionFacts();
            source = allRecipesResults.get(2).getRecipe().getRecipeResource();
            if((allRecipesResults.get(2).getRecipe().getCalories()) == 0){
                calories = "NA";
            }else {
                calories = calories + String.valueOf(allRecipesResults.get(2).getRecipe().getCalories());
            }        }
        else if ( h == hbox4){
            allRecipesResults.get(3).setRecipe(allRecipes.getRecipe(allRecipesResults.get(3)));
            name = allRecipesResults.get(3).getRecipeTitle();
            String url = allRecipesResults.get(3).getImageUrl();
            img = new Image(url);
            recipeSteps = allRecipesResults.get(3).getRecipe().getRecipeSteps();
            if((allRecipesResults.get(3).getRecipe().getTotalTime()) == 0){
                time = "NA";
            }else {
                time = allRecipesResults.get(3).getRecipe().getTimeInfo();
            }
            ArrayList ingrList = allRecipesResults.get(3).getRecipe().getRecipeIngredients();
            for ( int i = 0; i < ingrList.size(); i++){
                ingr = ingr +" - " + ingrList.get(i)+"\n ";
            }
            facts = allRecipesResults.get(3).getRecipe().getNutritionFacts();
            source = allRecipesResults.get(3).getRecipe().getRecipeResource();
            if((allRecipesResults.get(3).getRecipe().getCalories()) == 0){
                calories = "NA";
            }else {
                calories = calories + String.valueOf(allRecipesResults.get(3).getRecipe().getCalories());
            }        }
        else if ( h == hbox5){
            allRecipesResults.get(4).setRecipe(allRecipes.getRecipe(allRecipesResults.get(4)));
            name = allRecipesResults.get(4).getRecipeTitle();
            String url = allRecipesResults.get(4).getImageUrl();
            img = new Image(url);
            recipeSteps = allRecipesResults.get(4).getRecipe().getRecipeSteps();
            if((allRecipesResults.get(4).getRecipe().getTotalTime()) == 0){
                time = "NA";
            }else {
                time = allRecipesResults.get(4).getRecipe().getTimeInfo();
            }
            ArrayList ingrList = allRecipesResults.get(4).getRecipe().getRecipeIngredients();
            for ( int i = 0; i < ingrList.size(); i++){
                ingr = ingr +" - " + ingrList.get(i)+"\n ";
            }
            facts = allRecipesResults.get(4).getRecipe().getNutritionFacts();
            source = allRecipesResults.get(4).getRecipe().getRecipeResource();
            if((allRecipesResults.get(4).getRecipe().getCalories()) == 0){
                calories = "NA";
            }else {
                calories = calories + String.valueOf(allRecipesResults.get(4).getRecipe().getCalories());
            }        }
        else if ( h == hbox6){
            allRecipesResults.get(5).setRecipe(allRecipes.getRecipe(allRecipesResults.get(5)));
            name = allRecipesResults.get(5).getRecipeTitle();
            String url = allRecipesResults.get(5).getImageUrl();
            img = new Image(url);
            recipeSteps = allRecipesResults.get(5).getRecipe().getRecipeSteps();
            if((allRecipesResults.get(5).getRecipe().getTotalTime()) == 0){
                time = "NA";
            }else {
                time = allRecipesResults.get(5).getRecipe().getTimeInfo();
            }
            ArrayList ingrList = allRecipesResults.get(5).getRecipe().getRecipeIngredients();
            for ( int i = 0; i < ingrList.size(); i++){
                ingr = ingr +" - " + ingrList.get(i)+"\n ";
            }
            facts = allRecipesResults.get(5).getRecipe().getNutritionFacts();
            source = allRecipesResults.get(5).getRecipe().getRecipeResource();
            if((allRecipesResults.get(5).getRecipe().getCalories()) == 0){
                calories = "NA";
            }else {
                calories = calories + String.valueOf(allRecipesResults.get(5).getRecipe().getCalories());
            }        }
        else if ( h == hbox7){
            allRecipesResults.get(6).setRecipe(allRecipes.getRecipe(allRecipesResults.get(1)));
            name = allRecipesResults.get(6).getRecipeTitle();
            String url = allRecipesResults.get(6).getImageUrl();
            img = new Image(url);
            recipeSteps = allRecipesResults.get(6).getRecipe().getRecipeSteps();
            if((allRecipesResults.get(6).getRecipe().getTotalTime()) == 0){
                time = "NA";
            }else {
                time = allRecipesResults.get(6).getRecipe().getTimeInfo();
            }

            ArrayList ingrList = allRecipesResults.get(6).getRecipe().getRecipeIngredients();
            for ( int i = 0; i < ingrList.size(); i++){
                ingr = ingr +" - " + ingrList.get(i)+"\n ";
            }
            facts = allRecipesResults.get(6).getRecipe().getNutritionFacts();
            source = allRecipesResults.get(6).getRecipe().getRecipeResource();
            if((allRecipesResults.get(6).getRecipe().getCalories()) == 0){
                calories = "NA";
            }else {
                calories = calories + String.valueOf(allRecipesResults.get(6).getRecipe().getCalories());
            }
        }
        else if ( h == hbox8){
            allRecipesResults.get(7).setRecipe(allRecipes.getRecipe(allRecipesResults.get(7)));
            name = allRecipesResults.get(7).getRecipeTitle();
            String url = allRecipesResults.get(7).getImageUrl();
            img = new Image(url);
            recipeSteps = allRecipesResults.get(7).getRecipe().getRecipeSteps();
            if((allRecipesResults.get(7).getRecipe().getTotalTime()) == 0){
                time = "NA";
            }else {
                time = allRecipesResults.get(7).getRecipe().getTimeInfo();
            }
            ArrayList ingrList = allRecipesResults.get(7).getRecipe().getRecipeIngredients();
            for ( int i = 0; i < ingrList.size(); i++){
                ingr = ingr +" - " + ingrList.get(i)+"\n ";
            }
            facts = allRecipesResults.get(7).getRecipe().getNutritionFacts();
            source = allRecipesResults.get(7).getRecipe().getRecipeResource();
            if((allRecipesResults.get(7).getRecipe().getCalories()) == 0){
                calories = "NA";
            }else {
                calories = calories + String.valueOf(allRecipesResults.get(7).getRecipe().getCalories());
            }
        }else if ( h == hbox9){
            allRecipesResults.get(8).setRecipe(allRecipes.getRecipe(allRecipesResults.get(8)));
            name = allRecipesResults.get(8).getRecipeTitle();
            String url = allRecipesResults.get(8).getImageUrl();
            img = new Image(url);
            recipeSteps = allRecipesResults.get(8).getRecipe().getRecipeSteps();
            if((allRecipesResults.get(8).getRecipe().getTotalTime()) == 0){
                time = "NA";
            }else {
                time = allRecipesResults.get(8).getRecipe().getTimeInfo();
            }
            ArrayList ingrList = allRecipesResults.get(8).getRecipe().getRecipeIngredients();
            for ( int i = 0; i < ingrList.size(); i++){
                ingr = ingr +" - " + ingrList.get(i)+"\n ";
            }
            facts = allRecipesResults.get(8).getRecipe().getNutritionFacts();
            source = allRecipesResults.get(8).getRecipe().getRecipeResource();
            if((allRecipesResults.get(8).getRecipe().getCalories()) == 0){
                calories = "NA";
            }else {
                calories = calories + String.valueOf(allRecipesResults.get(8).getRecipe().getCalories());
            }
        }else if ( h == hbox10){
            allRecipesResults.get(9).setRecipe(allRecipes.getRecipe(allRecipesResults.get(9)));
            name = allRecipesResults.get(9).getRecipeTitle();
            String url = allRecipesResults.get(9).getImageUrl();
            img = new Image(url);
            recipeSteps = allRecipesResults.get(9).getRecipe().getRecipeSteps();
            if((allRecipesResults.get(9).getRecipe().getTotalTime()) == 0){
                time = "NA";
            }else {
                time = allRecipesResults.get(9).getRecipe().getTimeInfo();
            }
            ArrayList ingrList = allRecipesResults.get(9).getRecipe().getRecipeIngredients();
            for ( int i = 0; i < ingrList.size(); i++){
                ingr = ingr +" - " + ingrList.get(i)+"\n ";
            }
            facts = allRecipesResults.get(9).getRecipe().getNutritionFacts();
            source = allRecipesResults.get(9).getRecipe().getRecipeResource();
            if((allRecipesResults.get(9).getRecipe().getCalories()) == 0){
                calories = "NA";
            }else {
                calories = calories + String.valueOf(allRecipesResults.get(9).getRecipe().getCalories());
            }
        }


        FXMLLoader loader = new FXMLLoader(getClass().getResource("recipiePage.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RecipiePage recipiePage = loader.getController();
        recipiePage.displayRecipe(name,img,recipeSteps,time,ingr,facts,source,calories);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
