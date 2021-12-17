/**
 *@CenkOlcay
 *java version 16.0.1
 */
package oburr.searching;

import java.util.ArrayList;
import oburr.user.User;

public class Recipe {

    private String recipeName,recipeResource, imageUrl, recipeSteps, nutritionFacts;
    private ArrayList<Ingredient> recipeIngredients;
    private int  totalTime;
    private int difficultyLevel;
    private int calories;
    private int recipeScore;

    public Recipe(String recipeName, String recipeResource, String imageUrl, ArrayList<Ingredient> recipeIngredients,
                  String recipeSteps, String timeInfo, String nutritionFacts, User user){

        setRecipeName(recipeName);
        setRecipeResource(recipeResource);
        setImageUrl(imageUrl);
        setRecipeIngredients(recipeIngredients);
        setRecipeSteps(recipeSteps);
        setTotalTime(readTime(timeInfo));
        setNutritionFacts(nutritionFacts);
        setCalories(readCalories(nutritionFacts));

        setRecipeScore(user);

        setDifficultyLevel();
    }


    public void setRecipeName(String recipeName){
        this.recipeName = recipeName;
    }
    public void setRecipeResource(String recipeResource){
        this.recipeResource = recipeResource;
    }
    public void setImageUrl(String imageUrl){this.imageUrl = imageUrl;}
    public void setRecipeIngredients(ArrayList<Ingredient> recipeIngredients){this.recipeIngredients = recipeIngredients;}
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

            if(recipeIngredients.get(i).toString().contains(ingredient + " ")
            || recipeIngredients.get(i).toString().contains(ingredient + ",") ){
                return true;
            }

            else if( recipeIngredients.get(i).toString().charAt(recipeIngredients.get(i).toString().length() - 1) != 's'
                    ||
                    (recipeIngredients.get(i).toString().contains(ingredient + "s ")
                    || recipeIngredients.get(i).toString().contains(ingredient + "s,")) ){
                return true;

            }
            else if(recipeIngredients.get(i).toString().contains(ingredient + "es ")
                    || recipeIngredients.get(i).toString().contains(ingredient + "es,")){

                return true;
            }

        }

        return false;
    }

    public int readCalories(String nutritionFacts){
        int caloriesIntake = 0;


        int caloriesIndex = nutritionFacts.indexOf("calories");

            nutritionFacts = nutritionFacts.toLowerCase();

            if (caloriesIndex >= 0) {
                int index = 0;
                while (!Character.isDigit(nutritionFacts.charAt(index))) {
                    index++;
                }

                caloriesIntake = (int) Double.parseDouble(nutritionFacts.substring(index, caloriesIndex));
            }


        return caloriesIntake;
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

    public int readTime(String timeInfo){
        int time = 0;

        timeInfo = timeInfo.toLowerCase();

        if(timeInfo.indexOf('h') > 0){

            time += Integer.parseInt(timeInfo.substring(0,timeInfo.indexOf(" ")));
            time *= 60;

            if(timeInfo.indexOf('m') > 0){

                timeInfo = timeInfo.substring(timeInfo.indexOf("h"));

                time += Integer.parseInt(timeInfo.substring(timeInfo.indexOf(" ")+1,timeInfo.indexOf("m")-1));
            }

        }

        else if(timeInfo.indexOf('m') > 0){
            time += Integer.parseInt(timeInfo.substring(0,timeInfo.indexOf(" ")));
        }


        return time;
    }

    public void setDifficultyLevel(){
        difficultyLevel = (recipeIngredients.size() ) + (totalTime/30) + (recipeSteps.length()/10);
    }

    public String getRecipeName(){ return recipeName;}
    public String getRecipeResource(){ return recipeResource;}
    public String getImageUrl(){ return imageUrl;}
    public String getRecipeSteps(){ return recipeSteps;}
    public ArrayList<Ingredient> getRecipeIngredients(){ return recipeIngredients;}
    public String getNutritionFacts(){ return nutritionFacts;}
    public int getTotalTime(){ return totalTime;}
    public int getCalories(){ return calories;}
    public int getDifficultyLevel(){ return difficultyLevel;}
    public int getRecipeScore(){ return recipeScore; }



}
