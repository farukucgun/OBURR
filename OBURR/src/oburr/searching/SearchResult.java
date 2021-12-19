package oburr.searching;

public class SearchResult {

    private String resource, recipeTitle, recipeUrl, imageUrl;
    private Recipe recipe;

    public SearchResult(String resource, String recipeTitle, String recipeUrl, String imageUrl){
        setResource(resource);
        setRecipeTitle(recipeTitle);
        setRecipeUrl(recipeUrl);
        setImageUrl(imageUrl);
        setRecipe(null);

    }

    public void setResource(String resource){ this.resource = resource; }
    public void setRecipeTitle(String recipeTitle){ this.recipeTitle = recipeTitle;}
    public void setRecipeUrl(String recipeUrl){ this.recipeUrl = recipeUrl; }
    public void setImageUrl(String imageUrl){ this.imageUrl = imageUrl; }
    public void setRecipe(Recipe recipe){
        this.recipe = recipe;
    }


    public String getResource(){ return resource;}
    public String getRecipeTitle(){ return recipeTitle; }
    public String getRecipeUrl(){ return recipeUrl; }
    public String getImageUrl(){ return imageUrl;}
    public Recipe getRecipe(){ return recipe;}



}
