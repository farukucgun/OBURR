/**
 *@CenkOlcay
 *java version 16.0.1
 */

package oburr.searching;

import oburr.user.User;

import java.util.ArrayList;

public abstract class AbstractRecipe {

    /**
     * defines the properties of an Recipe object
     */


    protected String recipeName, recipeSteps, timeInfo, nutritionFacts;
    protected ArrayList<Ingredient> recipeIngredients;
    protected int  totalTime, difficultyLevel, calories, recipeScore;

    private boolean isSuitable ;

    public AbstractRecipe(String recipeName, ArrayList<Ingredient> recipeIngredients,
                  String recipeSteps, String timeInfo, int totalTime, int calories, String nutritionFacts, User user){
        isSuitable = true;

        setRecipeName(recipeName);
        setRecipeIngredients(recipeIngredients);
        setRecipeSteps(recipeSteps);
        setTimeInfo(timeInfo);
        setTotalTime(totalTime);
        setNutritionFacts(nutritionFacts);
        setCalories(calories);
        setIsSuitable( user );

        recipeScore = 0;
        setRecipeScore(user);
        setIsSuitable(user);

        setDifficultyLevel();
    }


    /**
     * mutator method for recipeName
     * @param recipeName
     */
    public void setRecipeName(String recipeName){
        this.recipeName = recipeName;
    }

    /**
     * mutator method for recipeIngredients
     * @param recipeIngredients
     */
    public void setRecipeIngredients(ArrayList<Ingredient> recipeIngredients){ this.recipeIngredients = recipeIngredients;}

    /**
     * mutator for recipeSteps
     * @param recipeSteps
     */
    public void setRecipeSteps(String recipeSteps){
        this.recipeSteps = recipeSteps;
    }

    /**
     * mutator for nutritionFacts
     * @param nutritionFacts
     */
    public void setNutritionFacts(String nutritionFacts){
        this.nutritionFacts = nutritionFacts;
    }

    /**
     * mutator for timeInfo
     * @param timeInfo
     */
    public void setTimeInfo(String timeInfo){ this.timeInfo = timeInfo; }

    /**
     * mutator for TotalTime
     * @param totalTime
     */
    public void setTotalTime(int totalTime){
        this.totalTime = totalTime;
    }

    /**
     * mutator for calories
     * @param calories
     */
    public void setCalories(int calories){
        this.calories = calories;
    }

    /**
     * checks whether the given ingredient is included in the recipe
     * @param ingredient
     * @return
     */
    public boolean included(Ingredient ingredient){

        //iterates and checks the presence of same word with different forms
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

            if(ingredient.toString().charAt(length - 1) == 'y'){
                if (recipeIngredients.get(i).toString().contains(ingredient.toString().substring(0,length-1) + "ies ")
                        || recipeIngredients.get(i).toString().contains(ingredient.toString().substring(0,length-1) + "ies,")) {

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

    /**
     * calculates and sets the recipeScore based on user's preferences
     * @param user
     */
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

    /**
     * determines whether the recipe is suitable for user or not
     * @param user
     */
    public void setIsSuitable(User user){
        for( Ingredient i: user.getAllergies() ){
            if( included( i ) ){
                isSuitable = false;
                return;
            }
        }
        for( Ingredient i: user.getBannedIngredients() ){
            if( included( i ) ){
                isSuitable = false;
                return;
            }
        }
    }

    /**
     * sets the difficulty based on several parameters
     */
    public void setDifficultyLevel(){
        difficultyLevel = (recipeIngredients.size() ) + (totalTime/30) + (recipeSteps.length()/10);
    }


    /**
     * accessor for recipeName
     * @return
     */
    public String getRecipeName(){ return recipeName;}

    /**
     * accessor for recipeSteps
     * @return
     */
    public String getRecipeSteps(){ return recipeSteps;}

    /**
     * accessor for recipeIngredients
     * @return
     */
    public ArrayList<Ingredient> getRecipeIngredients(){ return recipeIngredients;}

    /**
     * accessor for nutritionFacts
     * @return
     */
    public String getNutritionFacts(){ return nutritionFacts;}

    /**
     * accessor for timeInfo
     * @return
     */
    public String getTimeInfo(){ return timeInfo; }

    /**
     * accessor for totalTime
     * @return
     */
    public int getTotalTime(){ return totalTime;}

    /**
     * accessor for calories
     * @return
     */
    public int getCalories(){ return calories;}

    /**
     * accessor for difficultyLevel
     * @return
     */
    public int getDifficultyLevel(){ return difficultyLevel;}

    /**
     * accessor for recipeScore
     * @return
     */
    public int getRecipeScore(){ return recipeScore; }

    /**
     * accessor for isSuitable
     * @return
     */
    public boolean getIsSuitable(){ return isSuitable; }


}

