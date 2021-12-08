/**
 *@CenkOlcay
 *java version 16.0.1
 */
package oburr.user;

public class User {

    private String name;
    private String password;
    private char dietType;

    public void setName(String name){
        this.name = name;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setDietType(char dietType){
        this.dietType = dietType;
    }

    public void updatePassword(){}
    public void updateName(){}
    public void updateLikedItems(){}
    public void updateDislikedItems(){}
    public void updateDiet(){}


}
