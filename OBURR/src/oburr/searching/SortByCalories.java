package oburr.searching;

import java.util.Comparator;

public class SortByCalories implements Comparator<Recipe> {

    public int compare(Recipe firstRecipe, Recipe secondRecipe){
        return firstRecipe.getCalories() - secondRecipe.getCalories();
    }

}
