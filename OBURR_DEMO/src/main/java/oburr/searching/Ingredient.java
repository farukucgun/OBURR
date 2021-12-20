/**
 *@CenkOlcay
 *java version 16.0.1
 */

package oburr.searching;

public class Ingredient {
    /**
     * defines a Ingredient with its description
     */

    private String description;

    public Ingredient(String description){
        setDescription(description);
    }


    /**
     * mutator for description
     * @param description
     */
    public void setDescription(String description){
        this.description = description;
    }

    /**
     * toString used for matching words in recipes
     * @return
     */
    public String toString(){ return description;}

}
