/**
 *@CenkOlcay
 *java version 16.0.1
 */

package oburr.searching;

import oburr.searching.Ingredient;
import oburr.searching.OburrRecipe;
import oburr.user.DBCaller;
import oburr.user.User;


import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class OburrResource {
    /**
     * defines Oburr Database as a Resource to get OburrRecipes
     */

    private ArrayList<OburrRecipe> recipes;
    private User user;

    public OburrResource(User user){

        setUser(user);
    }


    public void setUser(User user){
        this.user = user;
    }


    /**
     * fetches the oburrRecipes from DB
     * @return
     */
    public ArrayList<OburrRecipe> bringRecipes(int startIndex, int Id) {

        DBCaller dbCaller = new DBCaller();
        dbCaller.setConnection();

        String query = "SELECT * FROM  oburr_userRecipes LIMIT " + startIndex + ",4 ";

        if(Id != 0){
            query = query.substring(0,query.indexOf("LIMIT")-1) + " WHERE user_id = " + Id + " " + query.substring(query.indexOf("LIMIT"));
            System.out.println(query);
        }


        ArrayList<OburrRecipe> oburrRecipes = new ArrayList<OburrRecipe>();

        try {

            ResultSet rs = dbCaller.getStatement().executeQuery(query);

            while (rs.next()) {


                String recipeName = rs.getString("recipeTitle");
                int userId = rs.getInt("user_id");
                String userName = rs.getString("user_name");

                Blob blob = rs.getBlob("recipeImage");

                byte[] imageBytes = blob.getBytes(1,(int)blob.length());
                BufferedImage recipeImage = convertToBufferedImage(imageBytes);

                String recipeSteps = rs.getString("recipeSteps");

                String recipeIngredients = rs.getString("recipeIngredients");
                ArrayList<Ingredient> ingredients = createIngredients(recipeIngredients);

                String recipeNutritionFacts = rs.getString("recipeNutritionFacts");
                int recipeCalories = rs.getInt("recipeCalories");

                String recipeTimeInfo = rs.getString("recipeTimeInfo");
                int recipeTotalTime = rs.getInt("recipeTotalTime");

                oburrRecipes.add(new OburrRecipe(recipeName,userName,
                        userId,ingredients, recipeSteps,recipeTimeInfo,recipeTotalTime,
                        recipeNutritionFacts, recipeCalories, user, recipeImage));


            }

            dbCaller.getStatement().close();

            return oburrRecipes;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }

    public ArrayList<Ingredient> createIngredients(String ingredients){
        Scanner reader = new Scanner(ingredients);

        reader.useDelimiter("[,]");

        ArrayList<Ingredient> recipeIngredients = new ArrayList<Ingredient>();

        while(reader.hasNext()){

            recipeIngredients.add(new Ingredient(reader.next()));
        }

        return recipeIngredients;
    }

    public BufferedImage convertToBufferedImage(byte[] imageBytes){


        try {

            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
            return bufferedImage;

        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }

        return null;
    }


    public void uploadOburrRecipe(OburrRecipe oburrRecipe, String ext){
        DBCaller dbCaller = new DBCaller();

        dbCaller.uploadRecipe(oburrRecipe, ext);
    }



}
