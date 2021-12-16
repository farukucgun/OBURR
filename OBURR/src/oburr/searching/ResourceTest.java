package oburr.searching;

import oburr.user.User;
import  java.util.ArrayList;
import java.util.Collections;

public class ResourceTest {

    public static void main(String[] args){

        User user = new User("faruk","enes");

        ArrayList<Ingredient> allergies = new ArrayList<Ingredient>();
        allergies.add(new Ingredient("garlic"));
        allergies.add(new Ingredient("mustard"));

        ArrayList<Ingredient> likes = new ArrayList<Ingredient>();
        likes.add(new Ingredient("onion"));
        likes.add(new Ingredient("carrot"));

        ArrayList<Ingredient> dislikes = new ArrayList<Ingredient>();
        dislikes.add(new Ingredient("ham"));

        user.setAllergies(allergies);
        user.setLikedIngredients(likes);
        user.setDislikedIngredients(dislikes);

        Resource allRecipes = new SearchableResource(
                "AllRecipes.com",
                "https://www.allrecipes.com/search/results/?", true,
                user, "search=",
                ".card__recipe.card__facetedSearchResult" ,".card__title" ,".manual-link-behavior",
                ".paragraph > p", ".ingredients-item-name",
                "body > div.docked-sharebar-content-container > div > main > div.recipe-container.two-col-container > div.content.two-col-main-content.karma-content-container > div.recipe-content.two-col-content.karma-main-column > div.two-col-content-wrapper > div.recipe-content-container > div.lead-content-wrapper.two-col-style > div.lead-content-aside-wrapper > div > section > div:nth-child(1) > div:nth-child(3) > div.recipe-meta-item-body",
                ".recipe-nutrition-section.partial > .section-body"
                );

        Resource myRecipes = new OnlySearchableResource(
          "MyRecipes.com",
                "https://www.myrecipes.com/search?q=", false,
                user, "",
                "div.search-result",".linkHoverStyle.search-result-title-link-text",".search-result-title-link",
                ".paragraph > p", ".elementFont__body.ingredients-item-name",
                "body > div.docked-sharebar-content-container > div > main > div.recipe-container.two-col-container > div.content.two-col-main-content.karma-content-container > div.recipe-content.two-col-content.karma-main-column > div.two-col-content-wrapper > div.recipe-content-container > div.lead-content-wrapper.two-col-style > div.lead-content-aside-wrapper > div > section > div:nth-child(1) > div:nth-child(3) > div.recipe-meta-item-body.elementFont__subtitle",
                ".recipeNutritionSectionBlock > .section-body"
        );


        long now = System.currentTimeMillis();

        ArrayList<SearchResult> allRecipesResults = allRecipes.findResults(null,"potato");

        long now2 = System.currentTimeMillis();
        System.out.println("It took " + (now2-now)/1000 + " seconds for allRecipes results");

        now = System.currentTimeMillis();

        ArrayList<SearchResult> myRecipesResults = myRecipes.findResults(null,"potato");

        now2 = System.currentTimeMillis();
        System.out.println("It took " + (now2-now)/1000 + " seconds myRecipes results");

        ArrayList<Recipe> recipes1 = new ArrayList<Recipe>();
        ArrayList<Recipe> recipes2 = new ArrayList<Recipe>();

        System.out.println(allRecipesResults.size());
        System.out.println(myRecipesResults.size());

        now = System.currentTimeMillis();

        for(int i = 0; i < allRecipesResults.size(); i++){
            System.out.println(allRecipesResults.get(i).getRecipeTitle());
            recipes1.add(allRecipes.getRecipe(allRecipesResults.get(i)));
            System.out.println(recipes1.get(i).getRecipeScore());
        }

        now2 = System.currentTimeMillis();
        System.out.println("It took " + (now2-now)/1000 + " seconds to initialize AllRecipesResults");


        now = System.currentTimeMillis();

        for(int i = 0; i < myRecipesResults.size(); i++){
            System.out.println(myRecipesResults.get(i).getRecipeTitle());
            recipes2.add(myRecipes.getRecipe(myRecipesResults.get(i)));

        }


        now2 = System.currentTimeMillis();
        System.out.println("It took " + (now2-now)/1000 + " seconds to initialize MyRecipesResults");
    }



}
