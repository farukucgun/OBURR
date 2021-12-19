package oburr.searching;

public class OburrResult {

    private String publisher, recipeTitle;
    private Recipe recipe;

    public OburrResult(String publisher, String recipeTitle, String recipeUrl, String imageUrl){
        setPublisher(publisher);
        setRecipeTitle(recipeTitle);
        setRecipe(null);
    }

    public void setPublisher(String publisher){ this.publisher = publisher; }
    public void setRecipeTitle(String recipeTitle){ this.recipeTitle = recipeTitle;}
    public void setRecipe(Recipe recipe){
        this.recipe = recipe;
    }


    public String getPublisher(){ return publisher;}
    public String getRecipeTitle(){ return recipeTitle; }
    public Recipe getRecipe(){ return recipe;}



}
