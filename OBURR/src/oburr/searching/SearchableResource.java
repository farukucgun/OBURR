/**
 *@CenkOlcay
 *java version 16.0.1
 */

package oburr.searching;

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
public class SearchableResource extends Resource{


    private String resultPath,descriptionPath, ingredientPath, totalTimePath, nutritionFactsPath;


    public SearchableResource(String platformName, String defaultLink, User user, String resultPath,
                              String descriptionPath, String ingredientPath,
                              String totalTimePath, String nutritionFactsPath){

        super(platformName, defaultLink, user);
        setResultPath(resultPath);
        setIngredientPath(ingredientPath);
        setDescriptionPath(descriptionPath);
        setTotalTimePath(totalTimePath);
        setNutritionFactsPath(nutritionFactsPath);
    }

    public String includeIngredients(ArrayList<Ingredient> ingredients, String search){

        String link = new String(defaultLink);
        if(ingredients != null && ingredients.size() != 0){
            int ingredientCount = 0;

            for (Ingredient ingredient : ingredients) {

                if (ingredientCount > 0) {
                    link += "&";
                }

                link += "IngIncl=" + ingredient.toString();
            }
        }
        return updatedURL(link,search);
    }

    public String updatedURL(String baseURL, String search){

        if(search == null || search.equals("")){
            return baseURL;
        }

        String updatedURL = new String(baseURL) + "search=";
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

    public Recipe getRecipe(SearchResult searchResult) {

        try {
            Document recipePage = Jsoup.connect(searchResult.getUrl()).get();

            String recipeTitle = searchResult.getResultTitle();

            ArrayList<Ingredient> recipeIngredients = new ArrayList<Ingredient>();
            String recipeDescription = "";
            String imageUrl = searchResult.getImageURL();

            for (Element ingredient : recipePage.select(ingredientPath)) {
                recipeIngredients.add(new Ingredient(ingredient.text()));
            }

            int stepCount = 0;


            for (Element step : recipePage.select(descriptionPath)) {
                recipeDescription += "Step " + (++stepCount) + ": " + "\n" + step.text() + "\n";
            }

            String recipeTimeInfo = recipePage.select(totalTimePath).text();


            String nutritionFacts = recipePage.select(nutritionFactsPath).first().text();
            nutritionFacts = nutritionFacts.substring(0,nutritionFacts.lastIndexOf('.'));


            return new Recipe(recipeTitle, platformName,imageUrl,
                    recipeIngredients, recipeDescription,
                    recipeTimeInfo, nutritionFacts);
        }

        catch(Exception exception){
        exception.printStackTrace();
        }

    return null;
    }


    @Override
    public ArrayList<SearchResult> findResults( ArrayList<Ingredient> ingredients, String search) {

        String url = includeIngredients(ingredients, search);

        try {

            long now = System.currentTimeMillis();

            Document doc = Jsoup.connect(url).get();
            Elements resultCards = doc.select(resultPath);
            ArrayList<SearchResult> results = new ArrayList<SearchResult>();

            int resultCount = 0;

            while ( resultCount < resultCards.size() && resultCount < maxResultSize){

                Element result = resultCards.get(resultCount++);

                String recipeTitle = result.select(".manual-link-behavior").attr("title");
                String resultURL = result.select(".manual-link-behavior").attr("href");
                String imageURL = result.select("img").attr("src");

                results.add(new SearchResult(recipeTitle,platformName,resultURL,imageURL));
            }

            long now2 = System.currentTimeMillis();

            System.out.println(now2-now);

            return results;
        }
        catch(Exception exception){
            exception.printStackTrace();

        }
        return null;
    }




    public void setResultPath(String resultPath){ this.resultPath = resultPath;}
    public void setDescriptionPath(String descriptionPath){this.descriptionPath = descriptionPath;}
    public void setIngredientPath(String ingredientPath){ this.ingredientPath = ingredientPath; }
    public void setTotalTimePath(String totalTimePath){this.totalTimePath = totalTimePath;}
    public void setNutritionFactsPath(String nutritionFactsPath){this.nutritionFactsPath = nutritionFactsPath;}



}
