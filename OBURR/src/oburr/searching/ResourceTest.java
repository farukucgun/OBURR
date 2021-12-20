package oburr.searching;

import oburr.searching.webSearching.AllRecipesResource;
import oburr.searching.webSearching.FoodNetworkResource;
import oburr.searching.webSearching.MyRecipesResource;
import oburr.searching.webSearching.Resource;
import oburr.user.User;

import  java.util.ArrayList;

import java.util.Scanner;

import javax.swing.JFileChooser;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class ResourceTest {

    public static void main(String[] args) {

        User user = new User("faruk", "enes");

        ArrayList<Ingredient> allergies = new ArrayList<Ingredient>();
        allergies.add(new Ingredient("garlic"));
        allergies.add(new Ingredient("mustard"));

        ArrayList<Ingredient> likes = new ArrayList<Ingredient>();
        likes.add(new Ingredient("onion"));
        likes.add(new Ingredient("carrot"));

        ArrayList<Ingredient> dislikes = new ArrayList<Ingredient>();
        dislikes.add(new Ingredient("ham"));

        user.setAllergies(allergies);
        user.setLikedIngredients(likes);
        user.setDislikedIngredients(dislikes);

        Resource allRecipes = new AllRecipesResource(
                "AllRecipes.com",
                "https://www.allrecipes.com/search/results/?", true,
                user, "search=",
                ".card__recipe.card__facetedSearchResult", ".card__title", ".manual-link-behavior",
                ".paragraph > p", ".ingredients-item-name",
                "body > div.docked-sharebar-content-container > div > main > div.recipe-container.two-col-container > div.content.two-col-main-content.karma-content-container > div.recipe-content.two-col-content.karma-main-column > div.two-col-content-wrapper > div.recipe-content-container > div.lead-content-wrapper.two-col-style > div.lead-content-aside-wrapper > div > section > div:nth-child(1) > div:nth-child(3) > div.recipe-meta-item-body",
                ".recipe-nutrition-section.partial > .section-body"
        );

        Resource myRecipes = new MyRecipesResource(
                "MyRecipes.com",
                "https://www.myrecipes.com/search?q=", false,
                user, "",
                "div.search-result", ".linkHoverStyle.search-result-title-link-text", ".search-result-title-link",
                ".paragraph > p", ".elementFont__body.ingredients-item-name",
                "body > div.docked-sharebar-content-container > div > main > div.recipe-container.two-col-container > div.content.two-col-main-content.karma-content-container > div.recipe-content.two-col-content.karma-main-column > div.two-col-content-wrapper > div.recipe-content-container > div.lead-content-wrapper.two-col-style > div.lead-content-aside-wrapper > div > section > div:nth-child(1) > div:nth-child(3) > div.recipe-meta-item-body.elementFont__subtitle",
                ".recipeNutritionSectionBlock > .section-body"
        );

        Resource foodNetwork = new FoodNetworkResource(
                "foodnetwork.com",
                "https://www.foodnetwork.com/search/", false,
                user, "", ".m-MediaBlock.o-ResultCard__m-MediaBlock > .m-MediaBlock__m-MediaWrap ",
                ".m-MediaBlock.o-ResultCard__m-MediaBlock > .m-MediaBlock__m-TextWrap > .m-MediaBlock__a-Headline > [href] > .m-MediaBlock__a-HeadlineText",
                "a", "li.o-Method__m-Step", ".o-Ingredients__a-Ingredient--CheckboxLabel",
                ".recipeInfo > .o-RecipeInfo > .o-RecipeInfo__m-Level > li > .m-RecipeInfo__a-Description--Total.o-RecipeInfo__a-Description",
                ""
        );

        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

        String str = "potato,onion";
        Scanner reader = new Scanner(str);

        reader.useDelimiter("[^A-Za-z]");

        while(reader.hasNext()){
            ingredients.add(new Ingredient(reader.next()));
        }

        allRecipes.findResults(ingredients,"");

        OburrRecipe obR = null;
        String ext = "";

        JFileChooser jfc = new JFileChooser();

        if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {


            try {
                ext = jfc.getSelectedFile().getAbsolutePath();

                ext = ext.substring(ext.lastIndexOf('.') + 1);

                BufferedImage bI = ImageIO.read(jfc.getSelectedFile());

                obR = new OburrRecipe("Faruk'un Recipe", "Enes", 35,
                        allergies, "Eskişehir Osmangazi", " Zaman alır", 34,
                        "Kalorisiz ", 245
                        , user, bI);

            }
            catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }


        OburrResource oR = new OburrResource();
        oR.uploadOburrRecipe(obR,ext);

        ArrayList<OburrRecipe> o = oR.bringRecipes(0);

        for(OburrRecipe oburrRecipe: o){
            System.out.println(oburrRecipe.getRecipeName());
        }

        }

    }


