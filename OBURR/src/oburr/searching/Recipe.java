/**
 *@CenkOlcay
 *java version 16.0.1
 */
package oburr.searching;

public class Recipe {

    private String recipeName,recipeResource,recipeIngredients, recipeSteps, nutritionFacts;
    int totalTime, calories;

    public void setRecipeName(String recipeName){
        this.recipeName = recipeName;
    }


    public void setRecipeResource(String recipeResource){
        this.recipeResource = recipeResource;
    }

    public void setRecipeIngredients(String recipeIngredients){
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



}
