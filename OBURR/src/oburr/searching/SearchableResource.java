package oburr.searching;

import oburr.user.User;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import javax.swing.*;

public class SearchableResource extends Resource{

    private String searchURL;
    private String resultPath,imagePath,descriptionPath, ingredientPath, totalTimePath,caloriesPath;


    public SearchableResource(String platformName, String defaultLink, User user, String imagePath,
                              String descriptionPath, String totalTimePath, String caloriesPath){

        super(platformName, defaultLink, user);
        setImagePath(imagePath);
        setDescriptionPath(descriptionPath);
        setTotalTimePath(totalTimePath);
        setCaloriesPath(caloriesPath);
    }

    public String includeIngredients(ArrayList<Ingredient> ingredients, String search){
        String link = new String(defaultLink);

        int ingredientCount = 0;

        for(Ingredient ingredient: ingredients){

            if(ingredientCount > 0){
                link += "&";
            }

            link += "IngIncl=" + ingredient.getName();
        }

        return link;
    }

    public String updatedURL(String search){

        String updatedURL = new String(defaultLink) + "search=";
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
    public String includeIngredients(String search) {
        return null;
    }

    @Override
    public ArrayList<Recipe> findRecipe(String search) {

        String url = updatedURL(search);

        try {
            Document doc = Jsoup.connect(url).get();
            Elements results = doc.select(resultPath);
            ArrayList<Recipe> recipes = new ArrayList<Recipe>();

            for(Element result: results){

                Document recipePage = Jsoup.connect(result.attr("href")).get();

                String recipeTitle = recipePage.title();
                ArrayList<Ingredient> recipeIngredients = new ArrayList<Ingredient>();
                String recipeDescription = "";
                String imageURL = recipePage.attr("data-src");

                for(Element ingredient: recipePage.select(ingredientPath)){
                    recipeIngredients.add(new Ingredient(ingredient.text()));
                }

                int stepCount = 0;


                for(Element step: recipePage.select(descriptionPath)){
                    recipeDescription += "Step " + (++stepCount) + ": " + "\n" + step.text() + "\n";
                }

                int recipeTime = Integer.parseInt(recipePage.selectXpath(totalTimePath).text());
                int calories = ((int) Double.parseDouble(recipePage.selectXpath(caloriesPath).text()));

                return recipes;
            }


        }
        catch(Exception exception){
            System.out.println("It doesnt work out mate");

        }
        return null;
    }


    public void setImagePath(String imagePath){this.imagePath = imagePath;}
    public void setDescriptionPath(String descriptionPath){this.descriptionPath = descriptionPath;}
    public void setTotalTimePath(String totalTimePath){this.totalTimePath = totalTimePath;}
    public void setCaloriesPath(String caloriesPath){this.caloriesPath = caloriesPath;}



}
