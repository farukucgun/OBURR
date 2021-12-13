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
    private String resultPath,descriptionPath, ingredientPath, totalTimePath,caloriesPath;


    public SearchableResource(String platformName, String defaultLink, User user, String resultPath,
                              String descriptionPath, String ingredientPath, String totalTimePath, String caloriesPath){

        super(platformName, defaultLink, user);
        setResultPath(resultPath);
        setIngredientPath(ingredientPath);
        setDescriptionPath(descriptionPath);
        setTotalTimePath(totalTimePath);
        setCaloriesPath(caloriesPath);
    }

    public String includeIngredients(ArrayList<Ingredient> ingredients, String search){

        String link = new String(defaultLink);
        if(ingredients != null && ingredients.size() != 0){
            int ingredientCount = 0;

            for (Ingredient ingredient : ingredients) {

                if (ingredientCount > 0) {
                    link += "&";
                }

                link += "IngIncl=" + ingredient.getName();
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

    public static int readTime(String timeInfo){
        int time = 0;

        timeInfo = timeInfo.toLowerCase();

        if(timeInfo.indexOf('h') > 0){

            time += Integer.parseInt(timeInfo.substring(0,timeInfo.indexOf(" ")));
            time *= 60;

            if(timeInfo.indexOf('m') > 0){

                timeInfo = timeInfo.substring(timeInfo.indexOf("h"));

                time += Integer.parseInt(timeInfo.substring(timeInfo.indexOf(" ")+1,timeInfo.indexOf("m")-1));
            }

        }

        else if(timeInfo.indexOf('m') > 0){
            time += Integer.parseInt(timeInfo.substring(0,timeInfo.indexOf(" ")));
        }



        return time;
    }

    @Override
    public ArrayList<Recipe> findRecipes( ArrayList<Ingredient> ingredients ,String search) {

        String url = includeIngredients(ingredients, search);

        try {
            Document doc = Jsoup.connect(url).get();
            Elements results = doc.select(resultPath);
            ArrayList<Recipe> recipes = new ArrayList<Recipe>();

            for(Element result: results){
;
                Document recipePage = Jsoup.connect(result.select(".manual-link-behavior").attr("href")).get();

                String recipeTitle = result.select(".manual-link-behavior").attr("title");
                ArrayList<Ingredient> recipeIngredients = new ArrayList<Ingredient>();
                String recipeDescription = "";
                String imageURL = result.select("img").attr("src");

                for(Element ingredient: recipePage.select(ingredientPath)){
                    recipeIngredients.add(new Ingredient(ingredient.text()));
                }

                int stepCount = 0;


                for(Element step: recipePage.select(descriptionPath)){
                    recipeDescription += "Step " + (++stepCount) + ": " + "\n" + step.text() + "\n";
                }

                int recipeTime;
                int calories;
                recipeTime = readTime(recipePage.select(totalTimePath).text());
                calories = 15;

                recipes.add(new Recipe(recipeTitle,platformName,
                        recipeIngredients,recipeDescription,
                        recipeTime,calories));
            }

            return recipes;
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
    public void setCaloriesPath(String caloriesPath){this.caloriesPath = caloriesPath;}



}
