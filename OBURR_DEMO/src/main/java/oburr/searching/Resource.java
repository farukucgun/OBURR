/**
 *@CenkOlcay
 *java version 16.0.1
 */

package oburr.searching;

import oburr.user.User;
import java.util.ArrayList;

public abstract class Resource {

    public static final int maxResultSize = 20;

    protected User user;
    protected String platformName, defaultLink;
    protected boolean ingredientSearchAvailable;
    protected ArrayList<SearchResult> searchResults;
    protected ArrayList<Recipe> recipes;

    public Resource(String platformName, String defaultLink, boolean ingredientSearchAvailable, User user){

        setPlatformName(platformName);
        setDefaultLink(defaultLink);
        setIngredientSearchAvailable(ingredientSearchAvailable);
        setUser(user);

    }

    public abstract String updatedURL(String baseURL, String search);
    public abstract ArrayList<SearchResult> findResults(ArrayList<Ingredient> ingredients, String search);
    public abstract Recipe getRecipe(SearchResult searchResult);
    public abstract void initializeRecipes();
    public abstract void excludeAllergens();

    public void setIngredientSearchAvailable(boolean ingredientSearchAvailable){ this.ingredientSearchAvailable = ingredientSearchAvailable;}
    public void setPlatformName(String platformName){
        this.platformName = platformName;
    }
    public void setDefaultLink(String defaultLink){
        this.defaultLink = defaultLink;
    }
    public void setUser(User user){
        this.user = user;
    }

    public void createRecipesList(){ recipes = new ArrayList<Recipe>(maxResultSize);}
    public void createSearchResultList(){ searchResults = new ArrayList<SearchResult>(maxResultSize);}

}
