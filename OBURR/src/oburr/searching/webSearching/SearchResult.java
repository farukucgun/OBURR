/**
 *@CenkOlcay
 *java version 16.0.1
 */
package oburr.searching.webSearching;

public class SearchResult {
    /**
     * defines the properties of the results in searching page
     */
    private String resource, recipeTitle, recipeUrl, imageUrl;
    private WebRecipe recipe;

    public SearchResult(String resource, String recipeTitle, String recipeUrl, String imageUrl){
        setResource(resource);
        setRecipeTitle(recipeTitle);
        setRecipeUrl(recipeUrl);
        setImageUrl(imageUrl);
        setRecipe(null);

    }

    /**
     * mutator for resource
     * @param resource
     */
    public void setResource(String resource){ this.resource = resource; }

    /**
     * mutator for recipeTitle
     * @param recipeTitle
     */
    public void setRecipeTitle(String recipeTitle){ this.recipeTitle = recipeTitle;}

    /**
     * mutator for recipeUrl
     * @param recipeUrl
     */
    public void setRecipeUrl(String recipeUrl){ this.recipeUrl = recipeUrl; }

    /**
     * mutator for imageUrl
     * @param imageUrl
     */
    public void setImageUrl(String imageUrl){ this.imageUrl = imageUrl; }

    /**
     * mutator for recipe
     * @param recipe
     */
    public void setRecipe(WebRecipe recipe){
        this.recipe = recipe;
    }

    /**
     * accessor for resource
     * @return
     */
    public String getResource(){ return resource;}

    /**
     * accessor for recipeTitle
     * @return
     */
    public String getRecipeTitle(){ return recipeTitle; }

    /**
     * accessor for recipeUrl
     * @return
     */
    public String getRecipeUrl(){ return recipeUrl; }

    /**
     * accessor for imageUrl
     * @return
     */
    public String getImageUrl(){ return imageUrl;}

    /**
     * accessor for recipe
     * @return
     */
    public WebRecipe getRecipe(){ return recipe;}



}
