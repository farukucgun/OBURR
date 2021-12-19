package oburr.searching;

import java.util.ArrayList;
import oburr.user.User;

public class OburrRecipe extends AbstractRecipe {

    private String recipeName, publisher, recipeSteps, nutritionFacts;
    private ArrayList<Ingredient> recipeIngredients;

    private int publisherId;

    public OburrRecipe(String recipeName, String publisher, int publisherId,  ArrayList<Ingredient> ingredients,
                       String recipeSteps, String timeInfo,
                       int totalTime,  String nutritionFacts, int calories,  User user){

        super(recipeName, ingredients, recipeSteps, timeInfo, totalTime, calories, nutritionFacts, user);

        setPublisher(publisher);
        setPublisherId(publisherId);

    }



    public void setPublisher(String publisher){ this.publisher = publisher;}
    public void setPublisherId(int publisherId){ this.publisherId = publisherId; }

}
