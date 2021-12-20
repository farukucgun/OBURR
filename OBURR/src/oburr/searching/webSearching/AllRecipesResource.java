/**
 *@CenkOlcay
 *java version 16.0.1
 */

package oburr.searching.webSearching;

import oburr.searching.Ingredient;
import oburr.user.User;

import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class AllRecipesResource extends Resource {

    /**
     * a class to define and use the searching functions of the resource that will be used for scrapping
     */

    protected String searchURL;
    protected String searchKeyWord, resultPath, linkPath, titlePath,
            descriptionPath, ingredientPath, totalTimePath, nutritionFactsPath;


    public AllRecipesResource(String platformName, String defaultLink, boolean ingredientSearchAvailable,
                              User user,
                              String searchKeyWord, String resultPath, String titlePath, String linkPath,
                              String descriptionPath, String ingredientPath,
                              String totalTimePath, String nutritionFactsPath){

        super(platformName, defaultLink, ingredientSearchAvailable, user);
        setSearchKeyWord(searchKeyWord);
        setResultPath(resultPath);
        setTitlePath(titlePath);
        setLinkPath(linkPath);
        setIngredientPath(ingredientPath);
        setDescriptionPath(descriptionPath);
        setTotalTimePath(totalTimePath);
        setNutritionFactsPath(nutritionFactsPath);
    }

    /**
     * initializes all the recipes of searchResults
     */
    public void initializeRecipes(){
        for(SearchResult result: searchResults){
            if(result.getRecipe() == null){
                result.setRecipe(getRecipe(result));
            }
        }

    }

    /**
     * returns the recipe of a given searchResult
     * @param searchResult
     * @return
     */
    public WebRecipe getRecipe(SearchResult searchResult) {
        try {

            long now = System.currentTimeMillis();

            //parse the recipeApge
            Document recipePage = Jsoup.connect(searchResult.getRecipeUrl()).get();

            //create the variables of the recipe
            ArrayList<Ingredient> recipeIngredients = new ArrayList<Ingredient>();
            String recipeDescription = "";
            String recipeTitle = searchResult.getRecipeTitle();
            String imageUrl = searchResult.getImageUrl();

            //initialize ingredients
            for (Element ingredient : recipePage.select(ingredientPath)) {
                if( !ingredient.text().equals("Deselect All")){
                    recipeIngredients.add(new Ingredient(ingredient.text() + " "));
                }
            }

            int stepCount = 0;

            //initializes the steps of the recipe
            for (Element step : recipePage.select(descriptionPath)) {

                recipeDescription += "Step " + (++stepCount) + ": " + "\n" + step.text() + "\n";
            }

            String recipeTimeInfo = "",
                    nutritionFacts = "";

            //set the timeInfo
            if (recipePage.select(totalTimePath).first() != null) {
                recipeTimeInfo = recipePage.select(totalTimePath).first().text();
            }


            //set nutritionFacts
            if ( (nutritionFactsPath != null && !nutritionFactsPath.equals(""))
                    && recipePage.select(nutritionFactsPath).first() != null) {
                nutritionFacts = recipePage.select(nutritionFactsPath).first().text();

                nutritionFacts = nutritionFacts.substring(0, nutritionFacts.lastIndexOf('.'));

            }

            //returns the new Recipe object
            return new WebRecipe(recipeTitle, platformName, imageUrl,
                        recipeIngredients, recipeDescription,
                        recipeTimeInfo, nutritionFacts, user);
        }

        catch(Exception exception){
            exception.printStackTrace();
        }
            return null;

    }


    /**
     * updates the url for ingredient search
     * @param ingredients
     * @param search
     */
    public void includeIngredients(ArrayList<Ingredient> ingredients, String search){

        if(ingredients != null && ingredients.size() != 0){

            for (Ingredient ingredient : ingredients) {

                searchURL += "IngIncl=" + ingredient.toString();

                searchURL += "&";

            }
        }

    }

    /**
     * updates the url for excluding the allergens
     */
    public void excludeAllergens() {

        if(user.getAllergies() != null && user.getAllergies().size() != 0){

            for (Ingredient allergen : user.getAllergies()) {

                searchURL += "IngExcl=" + allergen.toString();

                searchURL += "&";

            }
        }
    }

    /**
     * updates search url for given search
     * @param baseURL
     * @param search
     * @return
     */
    public String updatedURL(String baseURL, String search){

        if(search == null || search.equals("")){
            return baseURL;
        }

        String updatedURL = baseURL + searchKeyWord;
        Scanner reader = new Scanner(search);

        reader.useDelimiter("[^A-Za-zİğ]");

        int wordCount = 0;

        while(reader.hasNext()){

            if(wordCount > 0){
                updatedURL += "+";
            }
            updatedURL += reader.next();
            wordCount++;
        }

        return updatedURL;
    }


    /**
     * finds and returns the results
     * @param ingredients
     * @param search
     * @return
     */
    public ArrayList<SearchResult> findResults( ArrayList<Ingredient> ingredients, String search) {

        createSearchResultList();
        createRecipesList();

        searchURL = new String(defaultLink);

        if(ingredientSearchAvailable){
        excludeAllergens();
        }

        includeIngredients(ingredients, search);

        String url = updatedURL(searchURL,search);

        System.out.println(url);

        try {

            //parse the search page
            Document doc = Jsoup.connect(url).get();

            //get the results
            Elements resultCards = doc.select(resultPath);

            int resultCount = 0;

            //iterate through all results
            while ( resultCount < resultCards.size() && searchResults.size() < maxResultSize){


                //create the SearchResult
                Element result = resultCards.get(resultCount);

                String resultURL = result.select(linkPath).attr("abs:href");
                String imageURL = result.select("img").attr("abs:src");


                String recipeTitle = doc.select(titlePath).get(resultCount).text();

                searchResults.add(new SearchResult( platformName, recipeTitle, resultURL, imageURL));
                resultCount++;
            }

            return searchResults;
        }
        catch(Exception exception){
            exception.printStackTrace();

        }
        return null;
    }


    /**
     * mutator for resultPath
     * @param resultPath
     */
    public void setResultPath(String resultPath){ this.resultPath = resultPath;}

    /**
     * mutator for searchKeyWord
     * @param searchKeyWord
     */
    public void setSearchKeyWord(String searchKeyWord){ this.searchKeyWord = searchKeyWord;}

    /**
     * mutator for titlePath
     * @param titlePath
     */
    public void setTitlePath(String titlePath){ this.titlePath = titlePath;}

    /**
     * mutator for linkPath
     * @param linkPath
     */
    public void setLinkPath(String linkPath){ this.linkPath = linkPath;}

    /**
     * mutator for descriptionPath
     * @param descriptionPath
     */
    public void setDescriptionPath(String descriptionPath){this.descriptionPath = descriptionPath;}

    /**
     * mutator for ingredientPath
     * @param ingredientPath
     */
    public void setIngredientPath(String ingredientPath){ this.ingredientPath = ingredientPath; }

    /**
     * mutator for totalTimePath
     * @param totalTimePath
     */
    public void setTotalTimePath(String totalTimePath){this.totalTimePath = totalTimePath;}

    /**
     * mutator for nutritionFacts
     * @param nutritionFactsPath
     */
    public void setNutritionFactsPath(String nutritionFactsPath){this.nutritionFactsPath = nutritionFactsPath;}



}
