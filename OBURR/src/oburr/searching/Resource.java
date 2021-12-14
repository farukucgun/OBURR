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

    public Resource(String platformName, String defaultLink, User user){

        setPlatformName(platformName);
        setDefaultLink(defaultLink);
        setUser(user);

    }

    public abstract String updatedURL(String baseURL, String search);
    public abstract ArrayList<Recipe> findResults(ArrayList<Ingredient> ingredients, String search);
    public abstract void excludeAllergens();

    public void setPlatformName(String platformName){
        this.platformName = platformName;
    }
    public void setDefaultLink(String defaultLink){
        this.defaultLink = defaultLink;
    }
    public void setUser(User user){
        this.user = user;
    }

}
