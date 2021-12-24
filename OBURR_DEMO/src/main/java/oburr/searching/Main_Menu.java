package oburr.searching;
/**
 *@CenkOlcay
 *java version 16.0.1
 */



import oburr.searching.webSearching.Resource;
import oburr.searching.webSearching.SearchResult;
import oburr.searching.webSearching.WebRecipe;
import oburr.user.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * a class to define and use the searching functions of the resource that will be used for scrapping
 */
public class Main_Menu extends Resource{

    String dateTime, event;
    int month, day;
    protected String searchURL;
    protected String buttonName, resultPath, linkPath, titlePath,
            descriptionPath, ingredientPath, totalTimePath, nutritionFactsPath;


    public Main_Menu(String platformName, String defaultLink, boolean ingredientSearchAvailable,
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

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM");
        LocalDateTime now = LocalDateTime.now();
        this.dateTime = dtf.format(now);

        day = Integer.parseInt(dateTime.substring(0, 2));
        month = Integer.parseInt(dateTime.substring(3));
    }


    public WebRecipe getRecipe( SearchResult searchResult ) {

        try {
            System.out.println(1);
            Document doc = null;
            doc = Jsoup.connect(updatedURL("https://www.allrecipes.com/recipes/", "")).get();
            System.out.println(1);
            int rand = (int)(Math.random() * 10);

            Elements resultCards = doc.select(resultPath);

            int resultCount = 0;

            Element result = resultCards.get(0);

            String resultURL = result.select(linkPath).attr("href");
            System.out.println(1);
            String imageURL = result.select("img").attr("src");
            System.out.println(2);
            String recipeTitle = doc.select(titlePath).get(12).text();
            //String recipeTitle = doc.sel
            System.out.println(recipeTitle);
            System.out.println(3);

            Document recipePage = Jsoup.connect(resultURL).get();

            System.out.println("3,5");
            ArrayList<Ingredient> recipeIngredients = new ArrayList<Ingredient>();
            String recipeDescription = "";
            System.out.println(4);
            for (Element ingredient : recipePage.select(ingredientPath)) {
                recipeIngredients.add(new Ingredient(ingredient.text() + " "));
            }
            System.out.println(5);
            int stepCount = 0;
            for (Element step : recipePage.select(descriptionPath)) {

                recipeDescription += "Step " + (++stepCount) + ": " + "\n" + step.text() + "\n";
            }
            System.out.println(6);
            String recipeTimeInfo = "",
                    nutritionFacts = "";

            if (recipePage.select(totalTimePath).first() != null) {
                recipeTimeInfo = recipePage.select(totalTimePath).first().text();
            }

            if (recipePage.select(nutritionFactsPath).first() != null) {
                nutritionFacts = recipePage.select(nutritionFactsPath).first().text();

                nutritionFacts = nutritionFacts.substring(0, nutritionFacts.lastIndexOf('.'));
            }
            return new WebRecipe(recipeTitle, event, imageURL,
                    recipeIngredients, recipeDescription,
                    recipeTimeInfo, nutritionFacts, user);
        }

        catch(Exception exception){
            exception.printStackTrace();
        }
        return null;
    }

    public void includeIngredients(ArrayList<Ingredient> ingredients, String buttonName) {}

    public void excludeAllergens() {}

    public String updatedURL(String baseURL, String search){

        // baseURL = "https://www.allrecipes.com/recipes/";

        if ((month == 12 && day > 20) || (month == 1 && day < 3)) {event = "New Year";return "https://www.allrecipes.com/recipes/193/holidays-and-events/new-year/";}
        else if ((month == 10 && day > 20) || (month == 11 && day < 10)) {event = "Haloween";return "https://www.allrecipes.com/recipes/189/holidays-and-events/halloween/";}
        else if ((month == 11 && day > 15) || (month == 12 && day < 5)) {event = "Thanksgiving";return "https://www.allrecipes.com/recipes/198/holidays-and-events/thanksgiving/";}
        else if ((month == 12 && day > 15) || (month == 1 && day < 5)) {event = "Christmas";return "https://www.allrecipes.com/recipes/187/holidays-and-events/christmas/";}
        else if (month == 2 && day < 15) {event = "Valentines_Day";return "https://www.allrecipes.com/recipes/199/holidays-and-events/valentines-day/";}
        else if ((month == 4 && day > 20 ) ||(month == 5 && day < 15)) {event = "Mothers_Day";return "https://www.allrecipes.com/recipes/16516/holidays-and-events/mothers-day/dinner/";}
        else {return "https://www.allrecipes.com/recipes/78/breakfast-and-brunch/";}

        //System.out.println(updatedURL);
    }

    @Override
    public ArrayList<SearchResult> findResults( ArrayList<Ingredient> ingredients, String buttonName) {return null;}

    public void setResultPath(String resultPath){ this.resultPath = resultPath;}
    public void setTitlePath(String titlePath){ this.titlePath = titlePath;}
    public void setLinkPath(String linkPath){ this.linkPath = linkPath;}
    public void setDescriptionPath(String descriptionPath){this.descriptionPath = descriptionPath;}
    public void setIngredientPath(String ingredientPath){ this.ingredientPath = ingredientPath; }
    public void setTotalTimePath(String totalTimePath){this.totalTimePath = totalTimePath;}
    public void setNutritionFactsPath(String nutritionFactsPath){this.nutritionFactsPath = nutritionFactsPath;}


    @Override
    public void initializeRecipes() {

    }

}
