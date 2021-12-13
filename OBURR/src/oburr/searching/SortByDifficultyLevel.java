package oburr.searching;

import java.util.Comparator;

public class SortByDifficultyLevel implements Comparator<Recipe>{

    public int compare(Recipe firstRecipe, Recipe secondRecipe){

        return firstRecipe.getDifficultyLevel() - secondRecipe.getDifficultyLevel();
    }

}
