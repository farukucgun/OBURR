package oburr.searching;

import oburr.user.User;
import  java.util.ArrayList;

public class ResourceTest {

    public static void main(String[] args){

        User user = new User();

        Resource allRecipes = new SearchableResource("AllRecipes,","https://www.allrecipes.com/search/results/?",
                user, ".card__recipe.card__facetedSearchResult" ,".paragraph > p",
                ".ingredients-item-name",
                "body > div.docked-sharebar-content-container > div > main > div.recipe-container.two-col-container > div.content.two-col-main-content.karma-content-container > div.recipe-content.two-col-content.karma-main-column > div.two-col-content-wrapper > div.recipe-content-container > div.lead-content-wrapper.two-col-style > div.lead-content-aside-wrapper > div > section > div:nth-child(1) > div:nth-child(3) > div.recipe-meta-item-body","body > div.docked-sharebar-content-container > div > main > div.recipe-container.two-col-container > div.content.two-col-main-content.karma-content-container > div.recipe-content.two-col-content.karma-main-column > div.two-col-content-wrapper > div.recipe-content-container > section.nutrition-section.container > div > div.section-body");


        long now = System.currentTimeMillis();

        ArrayList<Recipe> results = allRecipes.findRecipes(null,"salad");



        for(int i = 0; i < results.size(); i++){
            System.out.println(results.get(i).getRecipeName());
            System.out.println(results.get(i).getRecipeResource());
            System.out.println(results.get(i).getRecipeSteps());
            System.out.println(results.get(i).getCalories());
            System.out.println(results.get(i).getTotalTime());
        }

        long now2 = System.currentTimeMillis();

        System.out.println(now);
        System.out.println(now2);
        System.out.println((now2-now)/1000);
    }



}
