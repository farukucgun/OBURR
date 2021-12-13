package oburr.user;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tester {

    static JFrame frame = new JFrame( );
    static JTextField userName = new JTextField( 15);
    static JPasswordField userPassword = new JPasswordField(15);
    static JPasswordField userPassword2 = new JPasswordField(15);
    static JLabel name = new JLabel("User Name:");
    static JLabel password = new JLabel("Password:");
    static JLabel password2 = new JLabel("Password again:");
    static JButton button = new JButton();
    static DBCaller connector = new DBCaller();



    public static void login(){

        class LoginListener implements ActionListener{
            public void actionPerformed( ActionEvent e ){
                String query = "SELECT * FROM OS2g280ES9.oburr_userInfo where user_name = ";
                query = query + "'" + userName.getText() + "' AND user_password = '" + userPassword.getText() + "'";
                if( connector.checkDB( query ) ){
                    System.out.println( "Successfully login");
                }
                else{
                    //error message: account does not exist
                }
            }
        }

        JPanel panel = new JPanel();

        panel.add(name);
        panel.add(userName);
        panel.add(password);
        panel.add(userPassword);
        ActionListener l = new LoginListener();
        button.addActionListener( l );
        button.setText("LOGIN");
        panel.add(button);

        frame.add(panel);
    }

    public static void register(){

        class RegisterListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                String query = "SELECT * FROM OS2g280ES9.oburr_userInfo where user_name = ";
                query = query + "'" + userName.getText() + "'";
                if ( !connector.checkDB( query ) ){
                    if (checkPassword()) {
                        connector.insertUser(new User(userName.getText(), userPassword.getText()));
                    }
                    else{
                        //error message: passwords does not match
                    }
                }
                else{
                    System.out.println("error message: username is already used");
                    //error message: username is already used
                }
            }
        }

        JPanel panel = new JPanel();

        panel.add(name);
        panel.add(userName);
        panel.add(password);
        panel.add(userPassword);
        panel.add(password2);
        panel.add(userPassword2);
        ActionListener l = new RegisterListener();
        button.addActionListener( l );
        button.setText("REGISTER");
        panel.add(button);

        frame.add(panel);
    }


    public static boolean checkPassword(){
        if( userPassword.getText().equals( userPassword2.getText() ) ){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {


        frame.setSize( 400,400);
        frame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );

        login();
        //register();
        frame.setVisible(true);


    }

}
