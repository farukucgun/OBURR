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
    protected ArrayList<Recipe> results;

    public Resource(String platformName, String defaultLink, boolean ingredientSearchAvailable, User user){

        setPlatformName(platformName);
        setDefaultLink(defaultLink);
        setIngredientSearchAvailable(ingredientSearchAvailable);
        setUser(user);

    }

    public abstract String updatedURL(String baseURL, String search);
    public abstract ArrayList<Recipe> findResults(ArrayList<Ingredient> ingredients, String search);
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

    public void createResultsList(){ results = new ArrayList<Recipe>(maxResultSize);}

}
