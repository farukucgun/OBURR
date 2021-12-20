/**
 *@CenkOlcay
 *java version 16.0.1
 */

package oburr.searching;

import java.util.ArrayList;
import oburr.user.User;
import java.awt.image.BufferedImage;

public class OburrRecipe extends AbstractRecipe {
    /**
     * defines the Recipes in Oburr database
     */

    private String recipeName, publisher, recipeSteps;
    private BufferedImage recipeImage;
    private ArrayList<Ingredient> recipeIngredients;

    private int publisherId;

    public OburrRecipe(String recipeName, String publisher, int publisherId,  ArrayList<Ingredient> ingredients,
                       String recipeSteps, String timeInfo,
                       int totalTime,  String nutritionFacts, int calories,  User user,
                       BufferedImage recipeImage){

        super(recipeName, ingredients, recipeSteps, timeInfo, totalTime, calories, nutritionFacts, user);

        setPublisher(publisher);
        setPublisherId(publisherId);
        setRecipeImage(recipeImage);

    }

    /**
     * mutator for publisher
     * @param publisher
     */
    public void setPublisher(String publisher){ this.publisher = publisher;}

    /**
     * mutator for publisherId
     * @param publisherId
     */
    public void setPublisherId(int publisherId){ this.publisherId = publisherId; }

    /**
     * mutator for recipeImage
     * @param recipeImage
     */
    public void setRecipeImage(BufferedImage recipeImage){ this.recipeImage = recipeImage; }

    /**
     * accessor for publisher
     * @return
     */
    public String getPublisher(){ return publisher;}

    /**
     * accessor for publisherId
     * @return
     */
    public int getPublisherId(){ return publisherId; }

    /**
     * accessor for recipeImage
     * @return
     */
    public BufferedImage getRecipeImage(){ return recipeImage; }



}
