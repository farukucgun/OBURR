package oburr.searching;

import oburr.user.User;

import java.util.ArrayList;

public class OnlySearchableResource extends SearchableResource {

    public OnlySearchableResource(String platformName, String defaultLink, boolean ingredientSearchAvailable,
                              User user,
                              String searchKeyWord, String resultPath, String titlePath, String linkPath,
                              String descriptionPath, String ingredientPath,
                              String totalTimePath, String nutritionFactsPath){

        super(platformName, defaultLink, ingredientSearchAvailable,
                user,
                searchKeyWord, resultPath, titlePath, linkPath,
                descriptionPath, ingredientPath,
                totalTimePath, nutritionFactsPath);
        setSearchKeyWord(searchKeyWord);
        setResultPath(resultPath);
        setTitlePath(titlePath);
        setLinkPath(linkPath);
        setIngredientPath(ingredientPath);
        setDescriptionPath(descriptionPath);
        setTotalTimePath(totalTimePath);
        setNutritionFactsPath(nutritionFactsPath);
    }

    public void excludeAllergens(){

        for(int r = 0; r < results.size(); r++){
            boolean isFound = false;
            for(int i = 0; i < user.getAllergies().size() && !isFound; i++){

                if(results.get(r).included(user.getAllergies().get(i))){
                    isFound = true;
                    results.remove(r);
                    r--;
                }
            }
        }
    }

    @Override
    public void includeIngredients(ArrayList<Ingredient> ingredients, String search) {}

}
