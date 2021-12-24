/**
 *@CenkOlcay
 *java version 16.0.1
 */

package oburr.searching.webSearching;

import oburr.searching.Ingredient;
import oburr.user.User;
import java.util.ArrayList;

public abstract class Resource {
    /**
     * defines the properties of a searching resource
     */

    public static final int maxResultSize = 10;

    protected User user;
    protected String platformName, defaultLink;
    protected boolean ingredientSearchAvailable;
    protected ArrayList<SearchResult> searchResults;
    protected ArrayList<WebRecipe> recipes;

    public Resource(String platformName, String defaultLink, boolean ingredientSearchAvailable, User user){

        setPlatformName(platformName);
        setDefaultLink(defaultLink);
        setIngredientSearchAvailable(ingredientSearchAvailable);
        setUser(user);

    }

    public abstract String updatedURL(String baseURL, String search);
    public abstract ArrayList<SearchResult> findResults(ArrayList<Ingredient> ingredients, String search);
    public abstract WebRecipe getRecipe(SearchResult searchResult);
    public abstract void initializeRecipes();
    public abstract void excludeAllergens();

    /**
     * mutator for ingredientSearchAvailable
     * @param ingredientSearchAvailable
     */
    public void setIngredientSearchAvailable(boolean ingredientSearchAvailable){ this.ingredientSearchAvailable = ingredientSearchAvailable;}

    /**
     * mutator for platformName
     * @param platformName
     */
    public void setPlatformName(String platformName){
        this.platformName = platformName;
    }

    /**
     * mutator for defaultLink
     * @param defaultLink
     */
    public void setDefaultLink(String defaultLink){
        this.defaultLink = defaultLink;
    }

    /**
     * mutator for user
     * @param user
     */
    public void setUser(User user){
        this.user = user;
    }

    /**
     * creates recipesList
     */
    public void createRecipesList(){ recipes = new ArrayList<WebRecipe>(maxResultSize);}

    /**
     * creates searchResultList
     */
    public void createSearchResultList(){ searchResults = new ArrayList<SearchResult>(maxResultSize);}

}
