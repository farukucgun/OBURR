/**
 *@CenkOlcay
 *java version 16.0.1
 */

package oburr.searching;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import oburr.user.User;
import java.util.ArrayList;

public abstract class Resource {


    protected User user;
    protected String platformName, defaultLink;

    public Resource(String platformName, String defaultLink, User user){

        setPlatformName(platformName);
        setDefaultLink(defaultLink);
        setUser(user);

    }

    public void excludeDislikes(ArrayList<Recipe> results){
        for(Ingredient dislikedIngredient: user.getDisLikedIngredients()){

            for(int i = 0; i < results.size(); i++){
                if(results.get(i).included(dislikedIngredient)){
                    results.remove(i);
                }
            }
        }
    }

    public abstract String updatedURL(String search);
    public abstract String includeIngredients(String search);
    public abstract ArrayList<Recipe> findRecipe(String search);

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
