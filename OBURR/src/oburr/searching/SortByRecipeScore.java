package oburr.searching;

import java.util.Comparator;

public class SortByRecipeScore implements Comparator<Recipe> {

    public int compare(Recipe firstRecipe, Recipe secondRecipe){

        return firstRecipe.getRecipeScore() - secondRecipe.getRecipeScore();
    }

}
