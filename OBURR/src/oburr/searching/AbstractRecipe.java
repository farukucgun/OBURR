package oburr.searching;

import oburr.user.User;

import java.util.ArrayList;

public abstract class AbstractRecipe {

    protected String recipeName, recipeSteps, timeInfo, nutritionFacts;
    protected ArrayList<Ingredient> recipeIngredients;
    protected int  totalTime, difficultyLevel, calories, recipeScore;

    public AbstractRecipe(String recipeName, ArrayList<Ingredient> recipeIngredients,
                  String recipeSteps, String timeInfo, int totalTime, int calories, String nutritionFacts, User user){

        setRecipeName(recipeName);
        setRecipeIngredients(recipeIngredients);
        setRecipeSteps(recipeSteps);
        setTimeInfo(timeInfo);
        setTotalTime(totalTime);
        setNutritionFacts(nutritionFacts);
        setCalories(calories);

        recipeScore = 0;
        setRecipeScore(user);

        setDifficultyLevel();
    }


    public void setRecipeName(String recipeName){
        this.recipeName = recipeName;
    }
    public void setRecipeIngredients(ArrayList<Ingredient> recipeIngredients){ this.recipeIngredients = recipeIngredients;}
    public void setRecipeSteps(String recipeSteps){
        this.recipeSteps = recipeSteps;
    }
    public void setNutritionFacts(String nutritionFacts){
        this.nutritionFacts = nutritionFacts;
    }
    public void setTimeInfo(String timeInfo){ this.timeInfo = timeInfo; }
    public void setTotalTime(int totalTime){
        this.totalTime = totalTime;
    }
    public void setCalories(int calories){
        this.calories = calories;
    }

    public boolean included(Ingredient ingredient){
        for(int i = 0; i < recipeIngredients.size(); i++){

            if(recipeIngredients.get(i).toString().contains(ingredient + " ")
                    || recipeIngredients.get(i).toString().contains(ingredient + ",") ){
                return true;
            }

            int length = ingredient.toString().length();

            if( ingredient.toString().charAt(length - 1) != 's') {
                if (recipeIngredients.get(i).toString().contains(ingredient + "s ")
                        || recipeIngredients.get(i).toString().contains(ingredient + "s,")) {

                    return true;
                }
            }

            if(ingredient.toString().charAt(length - 1) == 's'){
                if (recipeIngredients.get(i).toString().contains(ingredient.toString().substring(0,length-1) + " ")
                        || recipeIngredients.get(i).toString().contains(ingredient.toString().substring(0,length-1) + ",")) {

                    return true;
                }
            }

            if(recipeIngredients.get(i).toString().contains(ingredient + "es ")
                    || recipeIngredients.get(i).toString().contains(ingredient + "es,")){

                return true;
            }

            if(ingredient.toString().substring(length-2).equals("es")){
                if (recipeIngredients.get(i).toString().contains(ingredient.toString().substring(0,length-2) + " ")
                        || recipeIngredients.get(i).toString().contains(ingredient.toString().substring(0,length-2) + ",")) {

                    return true;
                }
            }

        }

        return false;
    }

    public void setRecipeScore(User user){

        if(user == null){
            recipeScore = 0;
        }

        else{

            if(user.getLikedIngredients() != null && user.getLikedIngredients().size() > 0) {

                for (Ingredient ingredient : user.getLikedIngredients()) {
                    if (included(ingredient)) {
                        recipeScore += 5;
                    }
                }

            }

            if(user.getDisLikedIngredients() != null && user.getDisLikedIngredients().size() > 0) {
                for (Ingredient ingredient : user.getDisLikedIngredients()) {
                    if (included(ingredient)) {
                        recipeScore -= 3;
                    }
                }
            }

        }

    }

    public void setDifficultyLevel(){
        difficultyLevel = (recipeIngredients.size() ) + (totalTime/30) + (recipeSteps.length()/10);
    }

    public String getRecipeName(){ return recipeName;}
    public String getRecipeSteps(){ return recipeSteps;}
    public ArrayList<Ingredient> getRecipeIngredients(){ return recipeIngredients;}
    public String getNutritionFacts(){ return nutritionFacts;}
    public String getTimeInfo(){ return timeInfo; }
    public int getTotalTime(){ return totalTime;}
    public int getCalories(){ return calories;}
    public int getDifficultyLevel(){ return difficultyLevel;}
    public int getRecipeScore(){ return recipeScore; }



}

