package oburr.user;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tester {

    static DBCaller connector = new DBCaller();



    public static boolean login( String userName, String userPassword){
        String query = "SELECT * FROM OS2g280ES9.oburr_userInfo where user_name = ";
        query = query + "'" + userName + "' AND user_password = '" + userPassword + "'";
        if( connector.checkDB( query ) ){
            return true;
        }
        else{
            //error message: account does not exist
            return false;
        }
    }

    public static boolean register( String userName, String userPassword, String userPassword2 ){

        String query = "SELECT * FROM OS2g280ES9.oburr_userInfo where user_name = ";
        query = query + "'" + userName + "'";
        if ( !connector.checkDB( query ) ){
            connector.insertUser(new User(userName, userPassword ) );
            return true;

        }
        else{
            //error message: username is already used
            return false;
        }

    }


    public static void main(String[] args) {




    }

}
