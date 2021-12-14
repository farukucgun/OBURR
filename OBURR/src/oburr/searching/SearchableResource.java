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


    private String searchURL;
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


        if(ingredients != null && ingredients.size() != 0){

            for (Ingredient ingredient : ingredients) {

                searchURL += "IngIncl=" + ingredient.toString();

                searchURL += "&";

            }
        }

        return updatedURL(searchURL,search);
    }

    public void excludeAllergens() {

        searchURL = new String(defaultLink);

        if(user.getAllergies() != null && user.getAllergies().size() != 0){

            for (Ingredient allergen : user.getAllergies()) {

                searchURL += "IngExcl=" + allergen.toString();

                searchURL += "&";

            }
        }
    }

    public String updatedURL(String baseURL, String search){

        if(search == null || search.equals("")){
            return baseURL;
        }

        String updatedURL = baseURL + "search=";
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


    @Override
    public ArrayList<Recipe> findResults( ArrayList<Ingredient> ingredients, String search) {

        excludeAllergens();
        String url = includeIngredients(ingredients, search);


        try {

            long now = System.currentTimeMillis();

            Document doc = Jsoup.connect(url).get();
            Elements resultCards = doc.select(resultPath);
            ArrayList<Recipe> results = new ArrayList<Recipe>();

            int resultCount = 0;

            while ( resultCount < resultCards.size() && resultCount < maxResultSize){

                Element result = resultCards.get(resultCount++);

                String recipeTitle = result.select(".manual-link-behavior").attr("title");
                String resultURL = result.select(".manual-link-behavior").attr("href");
                String imageURL = result.select("img").attr("src");


                Document recipePage = Jsoup.connect(resultURL).get();
                ArrayList<Ingredient> recipeIngredients = new ArrayList<Ingredient>();
                String recipeDescription = "";

                for (Element ingredient : recipePage.select(ingredientPath)) {
                    recipeIngredients.add(new Ingredient(ingredient.text()));
                }

                int stepCount = 0;


                for (Element step : recipePage.select(descriptionPath)) {
                    recipeDescription += "Step " + (++stepCount) + ": " + "\n" + step.text() + "\n";
                }

                String recipeTimeInfo = "",
                        nutritionFacts = "";

                if(recipePage.select(totalTimePath).first() != null) {
                    recipeTimeInfo = recipePage.select(totalTimePath).first().text();
                }

                if(recipePage.select(nutritionFactsPath).first() != null) {
                    nutritionFacts = recipePage.select(nutritionFactsPath).first().text();

                    nutritionFacts = nutritionFacts.substring(0, nutritionFacts.lastIndexOf('.'));
                }

                results.add(new Recipe(recipeTitle, platformName, imageURL,
                            recipeIngredients, recipeDescription,
                            recipeTimeInfo, nutritionFacts));
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
