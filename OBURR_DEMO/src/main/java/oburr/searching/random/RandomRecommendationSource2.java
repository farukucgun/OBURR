/**
 *@CenkOlcay
 *java version 16.0.1
 */

package oburr.searching.random;

import oburr.searching.Ingredient;
import oburr.searching.Recipe;
import oburr.searching.webSearching.Resource;
import oburr.searching.webSearching.SearchResult;
import oburr.searching.webSearching.WebRecipe;
import oburr.user.User;

import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * a class to define and use the searching functions of the resource that will be used for scrapping
 */
public class RandomRecommendationSource2 extends Resource{

	Element resultCard;
    protected String searchURL;
    protected String buttonName, resultPath, linkPath, titlePath,
            descriptionPath, ingredientPath, totalTimePath, nutritionFactsPath;


    public RandomRecommendationSource2(String platformName, String defaultLink, boolean ingredientSearchAvailable,
                              User user,
                              String buttonName,
                              String resultPath, String titlePath, String linkPath,
                              String descriptionPath, String ingredientPath,
                              String totalTimePath, String nutritionFactsPath){

        super(platformName, defaultLink, ingredientSearchAvailable, user);
        setResultPath(resultPath);
        setTitlePath(titlePath);
        setLinkPath(linkPath);
        setIngredientPath(ingredientPath);
        setDescriptionPath(descriptionPath);
        setTotalTimePath(totalTimePath);
        setNutritionFactsPath(nutritionFactsPath);
    }

    public WebRecipe getRecipe(SearchResult searchResult) {
        try {

            long now = System.currentTimeMillis();

            Document recipePage = Jsoup.connect(searchResult.getRecipeUrl()).get();
            
            ArrayList<Ingredient> recipeIngredients = new ArrayList<Ingredient>();
            String recipeDescription = "";
            String recipeTitle = searchResult.getRecipeTitle();
            String imageUrl = searchResult.getImageUrl();

            for (Element ingredient : recipePage.select(ingredientPath)) {
                recipeIngredients.add(new Ingredient(ingredient.text() + " "));
            }

            int stepCount = 0;
            for (Element step : recipePage.select(descriptionPath)) {

                recipeDescription += "Step " + (++stepCount) + ": " + "\n" + step.text() + "\n";
            }
            
            String recipeTimeInfo = "",
                    nutritionFacts = "";

            if (recipePage.select(totalTimePath).first() != null) {
            	/*for ( int i = 0; i < recipePage.select(totalTimePath).size(); i++) {
            		recipeTimeInfo += recipePage.select(totalTimePath).get(i).text();
            	}*/
            	if ((recipePage.select(totalTimePath)).size() == 1) {
            		recipeTimeInfo = recipePage.select(totalTimePath).get(0).text();
            	}
            	else if ((recipePage.select(totalTimePath)).size() == 2){
            		recipeTimeInfo = recipePage.select(totalTimePath).get(0).text() + " + " + recipePage.select(totalTimePath).get(1).text();
            	}
            	System.out.println(recipeTimeInfo);
            }	

            if (recipePage.select(nutritionFactsPath).first() != null) {
                nutritionFacts = recipePage.select(nutritionFactsPath).first().text();
            }
            return new WebRecipe(recipeTitle, platformName, imageUrl,
                        recipeIngredients, recipeDescription,
                        recipeTimeInfo, nutritionFacts, user);
        }

        catch(Exception exception){
            exception.printStackTrace();
        }
            return null;

    }

    @Override
    public void initializeRecipes() {

    }


    public void includeIngredients(ArrayList<Ingredient> ingredients, String buttonName) {}

    public void excludeAllergens() {}

    public String updatedURL(String baseURL, String buttonName){

        String updatedURL = baseURL;
        int rand = (int)(Math.random() * randomLists(buttonName).length);
        
        if ( buttonName.equals("breakfast") ) {updatedURL += randomLists("breakfast")[rand];}
        if ( buttonName.equals("lunch") ) {updatedURL += randomLists("lunch")[rand];}
        if ( buttonName.equals("dinner") ) {updatedURL += randomLists("dinner")[rand];}
        if ( buttonName.equals("snack") ) {updatedURL += randomLists("snack")[rand];}
        if ( buttonName.equals("quick") ) {updatedURL += "quick-recipes";}
        if ( buttonName.equals("drink") ) {updatedURL += randomLists("drink")[rand];}
        if ( buttonName.equals("holiday") ) {updatedURL += randomLists("holiday")[rand];}
        if ( buttonName.equals("dessert") ) {updatedURL += randomLists("dessert")[rand];}
             
        //System.out.println(updatedURL);
        return updatedURL;  
    }    
    
    public String[] randomLists(String listName) {
    	
    	String[] drink = {"festive-drinks-recipes", "non-alcoholic-cocktail-recipes", "hot-chocolate-recipes", "coffee-recipes"};
    	String[] breakfast = {"easter-breakfast-recipes", "breakfast-recipes", "low-carb-breakfast-recipes", "breakfast-pancake-recipes"};
    	String[] lunch = {"family-lunch-recipes", "quick-lunch-recipes", "summer-lunch-recipes", "storecupboard-lunch-recipes", "easter-lunch-recipes"};
    	String[] dinner = {"easy-dinner-recipes", "traybake-dinner-recipes", "easy-roast-dinner-recipes", "summer-dinner-party-recipes", "healthy-vegetarian-dinner-recipes"};
    	String[] holiday = {"holiday-home-recipes", "school-holiday-recipes", "bank-holiday-recipes", "easter-barbecue-recipes"};
    	String[] dessert = {"comforting-dessert-recipes-2", "frozen-dessert-recipes", "easter-desserts-recipes", "strawberry-dessert-recipes", "lemon-dessert-recipes"};
    	String[] snack = {"picnic-snack-recipes", "bonfire-night-snacks-recipes", "snack-recipes"};
    	
    	if ( listName.equals("breakfast") ) {return breakfast;}
    	if ( listName.equals("drink") ) {return drink;}
    	if ( listName.equals("lunch") ) {return lunch;}
    	if ( listName.equals("dinner") ) {return dinner;}
    	if ( listName.equals("holiday") ) {return holiday;}
    	if ( listName.equals("dessert") ) {return dessert;}
    	if ( listName.equals("snack") ) {return snack;}
    	return null;
    }

    @Override
    public ArrayList<SearchResult> findResults( ArrayList<Ingredient> ingredients, String buttonName) {

        createSearchResultList();
        createRecipesList();

        searchURL = new String(defaultLink);

        /*if(ingredientSearchAvailable){
        excludeAllergens();
        }

        includeIngredients(ingredients, search);*/


        String url = updatedURL(searchURL, buttonName);

        try {

            long now = System.currentTimeMillis();

            Document doc = Jsoup.connect(url).get();
            Elements resultCards = doc.select(resultPath);

            int resultCount = 0;
            
            while ( resultCount < resultCards.size() && searchResults.size() < 3){
            	
            	int rand = (int)(Math.random() * resultCards.size());
                Element result = resultCards.get(rand);

                String resultURL = result.select(linkPath).attr("abs:href");
                String imageURL = result.select("img").attr("src");

                String recipeTitle = doc.select(titlePath).get(resultCount).text();
                
                searchResults.add(new SearchResult( platformName, recipeTitle, resultURL, imageURL));
                resultCount++;
            }

            long now2 = System.currentTimeMillis();

            System.out.println("elapsed time: " + (now2-now)/1000);

            return searchResults;
        }
        catch(Exception exception){
            exception.printStackTrace();

        }
        return null;
    }

    public void setResultPath(String resultPath){ this.resultPath = resultPath;}
    public void setTitlePath(String titlePath){ this.titlePath = titlePath;}
    public void setLinkPath(String linkPath){ this.linkPath = linkPath;}
    public void setDescriptionPath(String descriptionPath){this.descriptionPath = descriptionPath;}
    public void setIngredientPath(String ingredientPath){ this.ingredientPath = ingredientPath; }
    public void setTotalTimePath(String totalTimePath){this.totalTimePath = totalTimePath;}
    public void setNutritionFactsPath(String nutritionFactsPath){this.nutritionFactsPath = nutritionFactsPath;}

}
