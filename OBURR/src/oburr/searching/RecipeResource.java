package oburr.searching;

import oburr.user.User;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Scanner;

public class RecipeResource extends OnlySearchableResource{

    public RecipeResource(String platformName, String defaultLink, boolean ingredientSearchAvailable,
                          User user,
                          String searchKeyWord, String resultPath, String titlePath, String linkPath,
                          String descriptionPath, String ingredientPath,
                          String totalTimePath, String nutritionFactsPath){

        super(platformName, defaultLink, ingredientSearchAvailable,
                user,
                searchKeyWord, resultPath, titlePath, linkPath,
                descriptionPath, ingredientPath,
                totalTimePath, nutritionFactsPath);

        setSearchKeyWord(searchKeyWord);
        setResultPath(resultPath);
        setTitlePath(titlePath);
        setLinkPath(linkPath);
        setIngredientPath(ingredientPath);
        setDescriptionPath(descriptionPath);
        setTotalTimePath(totalTimePath);
        setNutritionFactsPath(nutritionFactsPath);

    }

    public String updatedURL(String baseURL, String search){

        if(search == null || search.equals("")){
            return baseURL;
        }

        String updatedURL = baseURL;
        Scanner reader = new Scanner(search);

        reader.useDelimiter("[^A-Za-zİğ]");

        while(reader.hasNext()){

            updatedURL += reader.next() + "-";

        }

        return updatedURL;
    }

    public String changePage(String url){

        if(url.charAt(url.length() - 1 ) == '-'){
            url += "/p/2";
        }
        else if(Character.isDigit(url.charAt(url.length() - 1))){

            int pageNumber = url.charAt(url.length() - 1);
            url = url.substring(0,url.length() - 1 ) + (pageNumber+1);
        }

        return url;
    }

    public ArrayList<SearchResult> findResults(ArrayList<Ingredient> ingredients, String search) {

        createSearchResultList();
        createRecipesList();

        searchURL = new String(defaultLink);

        if(ingredientSearchAvailable){
            excludeAllergens();
        }

        includeIngredients(ingredients, search);


        String url = updatedURL(searchURL,search);

        try {

            int resultCount = 0;

            Document doc = Jsoup.connect(url).get();
            Elements resultCards = doc.select(resultPath);

            do{

                Element result = resultCards.get(resultCount);

                String resultURL = result.select(linkPath).attr("abs:href");
                String imageURL = result.select("img").attr("abs:src");


                String recipeTitle = doc.select(titlePath).get(resultCount).text();

                searchResults.add(new SearchResult( platformName, recipeTitle, resultURL, imageURL));
                resultCount++;

                if((resultCount == resultCards.size() && resultCards.size() >= 5) && searchResults.size() < 15){
                    resultCount = 0;
                    doc = Jsoup.connect(changePage(url)).get();
                    resultCards = doc.select(resultPath);
                }

            }
            while ( resultCount < resultCards.size() && searchResults.size() < maxResultSize);

            return searchResults;
        }
        catch(Exception exception){
            exception.printStackTrace();

        }
        return null;
    }



}
