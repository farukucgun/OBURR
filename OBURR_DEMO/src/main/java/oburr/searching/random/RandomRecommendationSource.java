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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * a class to define and use the searching functions of the resource that will be used for scrapping
 */
public class RandomRecommendationSource extends Resource {


    protected String searchURL;
    protected String buttonName, resultPath, linkPath, titlePath,
            descriptionPath, ingredientPath, totalTimePath, nutritionFactsPath;


    public RandomRecommendationSource(String platformName, String defaultLink, boolean ingredientSearchAvailable,
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
                recipeTimeInfo = recipePage.select(totalTimePath).first().text();
            }

            if (recipePage.select(nutritionFactsPath).first() != null) {
                nutritionFacts = recipePage.select(nutritionFactsPath).first().text();

                nutritionFacts = nutritionFacts.substring(0, nutritionFacts.lastIndexOf('.'));
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

        // baseURL = "https://www.allrecipes.com/recipes/";
        String updatedURL = baseURL;
        
        if ( buttonName.equals("breakfast") ) {updatedURL += "78/breakfast-and-brunch/";}
        if ( buttonName.equals("lunch") ) {updatedURL += "17561/lunch/";}
        if ( buttonName.equals("dinner") ) {updatedURL += "17562/dinner/";}
        if ( buttonName.equals("snack") ) {updatedURL += "76/appetizers-and-snacks/";}
        if ( buttonName.equals("quick") ) {updatedURL += "1947/everyday-cooking/quick-and-easy/";}
        if ( buttonName.equals("drink") ) {updatedURL += "77/drinks/";}
        if ( buttonName.equals("holiday") ) {updatedURL += "85/holidays-and-events/";}
        if ( buttonName.equals("dessert") ) {updatedURL += "79/desserts/";}
          
        //System.out.println(updatedURL);
        return updatedURL;     
    }
    
    public final boolean containsDigit(String s) {
        boolean containsDigit = false;

        if (s != null && !s.isEmpty()) {
            for (char c : s.toCharArray()) {
                if (containsDigit = Character.isDigit(c)) {
                    break;
                }
            }
        }
        return containsDigit;
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

                String resultURL = result.select(linkPath).attr("href");
                String imageURL = result.select("img").attr("src");

                String recipeTitle = doc.select(titlePath).get(resultCount).text();
                //System.out.println(recipeTitle);
                
                if ( !containsDigit(recipeTitle) ) {
                	searchResults.add(new SearchResult( platformName, recipeTitle, resultURL, imageURL));
                }
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
