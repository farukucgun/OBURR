/**
 *@CenkOlcay
 *java version 16.0.1
 */
package oburr.searching;

import java.util.ArrayList;

public class Recipe {

    private String recipeName,recipeResource, recipeSteps, nutritionFacts;
    private ArrayList<Ingredient> recipeIngredients;
    private int totalTime, calories;

    public Recipe(String recipeName, String recipeResource, ArrayList<Ingredient> recipeIngredients,
                  String recipeSteps, int totalTime, int calories){
        setRecipeName(recipeName);
        setRecipeResource(recipeResource);
        setRecipeIngredients(recipeIngredients);
        setRecipeSteps(recipeSteps);
        setTotalTime(totalTime);
        setCalories(calories);
    }

    public void setRecipeName(String recipeName){
        this.recipeName = recipeName;
    }


    public void setRecipeResource(String recipeResource){
        this.recipeResource = recipeResource;
    }

    public void setRecipeIngredients(ArrayList<Ingredient> recipeIngredients){
        this.recipeIngredients = recipeIngredients;
    }

    public void setRecipeSteps(String recipeSteps){
        this.recipeSteps = recipeSteps;
    }

    public void setNutritionFacts(String nutritionFacts){
        this.nutritionFacts = nutritionFacts;
    }

    public void setTotalTime(int totalTime){
        this.totalTime = totalTime;
    }

    public void setCalories(int calories){
        this.calories = calories;
    }

    public boolean included(Ingredient ingredient){
        for(int i = 0; i < recipeIngredients.size(); i++){
            if(recipeIngredients.get(i).getName().indexOf(ingredient.getName()) >= 0){
                return true;
            }
        }

        return false;
    }



}
