/**
 *@CenkOlcay
 *java version 16.0.1
 */

package oburr.searching;

import oburr.user.User;
import java.util.ArrayList;

public abstract class Resource {

    protected User user;
    protected String defaultLink;

    public void excludeDislikes(ArrayList<Recipe> results){}

    public abstract String updateURL(String search);
    public abstract String includeIngredients(String search);
    public abstract ArrayList<Recipe> findRecipe(String search);

}
