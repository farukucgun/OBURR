/**
 *@CenkOlcay
 *java version 16.0.1
 */
package oburr.user;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class DBCaller {

    public static final String connectionString = "jdbc:mysql://remotemysql.com:3306/OS2g280ES9";
    public static final String connectionUserName = "OS2g280ES9";
    public static final String connectionPassword = "jzh25eAN6S";

    public static final String[] tableNames = {"oburr_userInfo","oburr_userLikesAndDislikes"};

    private Connection connection;
    private Statement statement;

    public void setConnection(){
        try {
            connection = DriverManager.getConnection(connectionString, connectionUserName, connectionPassword);
            statement = connection.createStatement();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }

    }

    public void updateDB(String query){

        setConnection();
        if(statement != null) {
            try {
                statement.executeQuery(query);
                statement.close();
                connection.close();
            }
            catch(SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }

    }

    public boolean checkDB(String query){
        setConnection();

        if(statement != null){
            try {
                ResultSet resultSet = statement.executeQuery(query);
                return resultSet.isBeforeFirst();
            }
            catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }

        return false;
    }


    public void insertUser(User user){
        String query = "INSERT INTO oburr_userInfo (user_name,user_password) " +
                "VALUES(" + user.getName() + ", " +  user.getPassword() + ")";

        updateDB(query);

        query = "INSERT INTO oburr_userLikesAndDislikes(user_id,oburr_userLikes,oburr_userDislikes)" +
         "VALUES(" + user.getUserId() + "," + user.getLikedIngredients() + "," +user.getDisLikedIngredients() + ")";

        updateDB(query);
    }

    public void updateName(User user){
        String query  = "UPDATE oburr_userInfo SET user_name = '" + user.getName()
                + "' WHERE user_id =" + user.getUserId();

        updateDB(query);
    }

    public void updatePassword(User user){
        String query  = "UPDATE oburr_userInfo SET user_password = '" + user.getPassword()
                + "' WHERE user_id =" + user.getUserId();

        updateDB(query);
    }

    public void updateLiked(User user){
        String query = "UPDATE oburr_userLikesAndDislikes SET oburr_userLikes = '" +
                user.getLikedIngredients()
                + "' WHERE user_id = " + user.getUserId();
    }
    public void updateDisliked(User user){

    String query = "UPDATE oburr_userLikesAndDislikes SET oburr_userDislikes = '" +
            user.getDisLikedIngredients()
            + "' WHERE user_id = " + user.getUserId();

    updateDB(query);
    }
}
