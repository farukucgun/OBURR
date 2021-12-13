package oburr.searching;

import java.util.Comparator;

public class SortByName implements Comparator<Recipe>{

    public int compare(Recipe firstRecipe, Recipe secondRecipe){
        return firstRecipe.getRecipeName().compareTo(secondRecipe.getRecipeName());
    }

}
