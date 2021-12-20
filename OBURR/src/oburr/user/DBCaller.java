/**
 *@CenkOlcay
 *java version 16.0.1
 */
package oburr.user;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import java.sql.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import oburr.searching.OburrRecipe;
import oburr.searching.Ingredient;

import javax.sql.rowset.serial.SerialBlob;

public class DBCaller {
    /**
     * a class responsible for fetching and updating data in mySQL database
     */

    public static final String connectionString = "jdbc:mysql://remotemysql.com:3306/OS2g280ES9";
    public static final String connectionUserName = "OS2g280ES9";
    public static final String connectionPassword = "jzh25eAN6S";

    public static final String[] tableNames = {"oburr_userInfo", "oburr_userLikesAndDislikes", "oburr_userRecipes"};

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    /**
     * creates the connection and statement
     */
    public void setConnection() {
        try {
            connection = DriverManager.getConnection(connectionString, connectionUserName, connectionPassword);


            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }

    public void uploadRecipe(OburrRecipe oburrRecipe, String extension) {

        try {
            connection = DriverManager.getConnection(connectionString, connectionUserName, connectionPassword);


            //convert image to bytes
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(oburrRecipe.getRecipeImage(), extension, baos);


            String query = "INSERT INTO oburr_userRecipes (recipeTitle,user_name,user_id, recipeSteps," +
                    "recipeIngredients,recipeImage,recipeTimeInfo,recipeTotalTime," +
                    "recipeNutritionFacts, recipeCalories) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?)";

            preparedStatement = connection.prepareStatement(query);

            PreparedStatement pres = preparedStatement;

            Blob blob = new SerialBlob(baos.toByteArray());

            String ingredients = "";

            //convert ingredients list to string
            ArrayList<Ingredient> recipeIngredients = oburrRecipe.getRecipeIngredients();

            for (int i = 0; i < recipeIngredients.size(); i++) {

                ingredients += recipeIngredients.get(i) + ",";
            }

            ingredients = ingredients.substring(0, ingredients.length() - 1);

            pres.setString(1, oburrRecipe.getRecipeName());
            pres.setString(2, oburrRecipe.getPublisher());
            pres.setInt(3, oburrRecipe.getPublisherId());
            pres.setString(4, oburrRecipe.getRecipeSteps());
            pres.setString(5, ingredients);
            pres.setBlob(6, blob);
            pres.setString(7, oburrRecipe.getTimeInfo());
            pres.setInt(8, oburrRecipe.getTotalTime());
            pres.setString(9, oburrRecipe.getNutritionFacts());
            pres.setInt(10, oburrRecipe.getCalories());

            pres.execute();
            pres.close();
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    /**
     * makes the insert and update queries in DB
     *
     * @param query
     */
    public void updateDB(String query) {

        setConnection();
        if (statement != null) {
            try {
                statement.execute(query);
                statement.close();
                connection.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }

    }


    public boolean checkDB(String query) {
        setConnection();

        if (statement != null) {
            try {
                ResultSet resultSet = statement.executeQuery(query);
                return resultSet.isBeforeFirst();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return false;
    }


    /**
     * inserts a new user row in DB
     *
     * @param user
     */
    public void insertUser(User user) {
        String query = "INSERT INTO oburr_userInfo (user_name,user_password) " +
                "VALUES( '" + user.getName() + "', '" + user.getPassword() + "')";

        updateDB(query);
    }

    public void insertUserLikesAndDislikes(User user) {
        String query = "INSERT INTO oburr_userLikesAndDislikes(oburr_userLikes,oburr_userDislikes, oburr_userAllergies, user_id, dietType)" +
                "VALUES( '', '', ''," + user.getUserId() + ",'')";

        updateDB(query);
    }

    /**
     * updates the name of an user in DB
     *
     * @param user
     */
    public void updateName(User user) {
        String query = "UPDATE oburr_userInfo SET user_name = '" + user.getName()
                + "' WHERE user_id =" + user.getUserId();

        updateDB(query);
    }

    /**
     * updates the password of an user
     *
     * @param user
     */
    public void updatePassword(User user) {
        String query = "UPDATE oburr_userInfo SET user_password = '" + user.getPassword()
                + "' WHERE user_id =" + user.getUserId();

        updateDB(query);
    }

    /**
     * get the userId of a user
     *
     * @param user
     * @return
     */
    public int returnId(User user) {
        try {
            String query = "SELECT oburr_userInfo.user_id FROM OS2g280ES9.oburr_userInfo WHERE user_name = '" + user.getName() + "'";
            setConnection();
            ResultSet rs = statement.executeQuery(query);
            rs.first();
            return rs.getInt(1);
        } catch (SQLException e) {
        }
        return -1;
    }

    public String returnDietType(User user) {
        String s = "";
        try {
            String query = "SELECT oburr_userLikesAndDislikes.dietType FROM OS2g280ES9.oburr_userLikesAndDislikes WHERE user_id =" + user.getUserId();
            setConnection();
            ResultSet rs = statement.executeQuery(query);
            rs.first();
            return rs.getString(1);
        } catch (SQLException e) {
            System.err.println(e);
        }
        return s;
    }

    public String returnAllergens(User user) {
        String s = "";
        try {
            String query = "SELECT oburr_userLikesAndDislikes.oburr_userAllergies FROM OS2g280ES9.oburr_userLikesAndDislikes WHERE user_id =" + user.getUserId();
            setConnection();
            ResultSet rs = statement.executeQuery(query);
            rs.first();
            return rs.getString(1);
        } catch (SQLException e) {
            System.err.println(e);
        }
        return s;
    }

    public String returnDislikes(User user) {
        String s = "";
        try {
            String query = "SELECT oburr_userLikesAndDislikes.oburr_userDislikes FROM OS2g280ES9.oburr_userLikesAndDislikes WHERE user_id =" + user.getUserId();
            setConnection();
            ResultSet rs = statement.executeQuery(query);
            rs.first();
            return rs.getString(1);
        } catch (SQLException e) {
            System.err.println(e);
        }
        return s;
    }

    public String returnLikes(User user) {
        String s = "";
        try {
            String query = "SELECT oburr_userLikesAndDislikes.oburr_userLikes FROM OS2g280ES9.oburr_userLikesAndDislikes WHERE user_id =" + user.getUserId();
            setConnection();
            ResultSet rs = statement.executeQuery(query);
            rs.first();
            return rs.getString(1);
        } catch (SQLException e) {
            System.err.println(e);
        }
        return s;
    }

    public void updateAllergens(User user) {
        String query = "UPDATE oburr_userLikesAndDislikes SET oburr_userAllergies = '" +
                user.getAllergenIngredientsAsString()
                + "' WHERE user_id = " + user.getUserId();

        updateDB(query);
    }

    public void updateLiked(User user) {
        String query = "UPDATE oburr_userLikesAndDislikes SET oburr_userLikes = '" +
                user.getLikedIngredientsAsString()
                + "' WHERE user_id = " + user.getUserId();

        updateDB(query);
    }

    public void updateDisliked(User user) {

        String query = "UPDATE oburr_userLikesAndDislikes SET oburr_userDislikes = '" +
                user.getDislikedIngredientsAsString()
                + "' WHERE user_id = " + user.getUserId();

        updateDB(query);
    }

    public void updateDietType(User user) {
        String query = "UPDATE oburr_userLikesAndDislikes SET dietType = '" +
                user.getDietType()
                + "' WHERE user_id = " + user.getUserId();

        updateDB(query);
    }
}
