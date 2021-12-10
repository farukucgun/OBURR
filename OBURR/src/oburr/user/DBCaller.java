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

    public static final String connectionString = "";
    public static final String connectionUserName = "";
    public static final String connectionPassword = "";

    public static final String[] tableNames = {};

    private Connection connection;

    public void setConnection(){
        try {
            connection = DriverManager.getConnection(connectionString, connectionUserName, connectionPassword);
        }
        catch(SQLException sqlException){

        }

    }
    public void insertUser(User user){}
    public void updatePassword(User user){}
    public void updateName(User user){}
    public void updateEmail(User user){}
    public void updateID(User user){}
    public void updateLiked(User user){}
    public void updateDisliked(User user){}


}
