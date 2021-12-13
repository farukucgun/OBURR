package oburr.searching;

import java.util.Comparator;

public class SortByTime implements Comparator<Recipe> {

    public int compare(Recipe firstRecipe, Recipe secondRecipe){
        return firstRecipe.getTotalTime() - secondRecipe.getTotalTime();
    }

}
