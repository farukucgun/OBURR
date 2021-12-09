/**
 *@CenkOlcay
 *java version 16.0.1
 */
package oburr.user;

public class User implements Editable{

    private String name;
    private String password;
    private char dietType;
    private boolean isEditable;

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
    public void setIsEditable(boolean isEditable){
        this.isEditable = isEditable;
    }

    public String getName(){ return name;}
    public char getDietType(){ return dietType;}
    public boolean getIsEditable(){ return isEditable;}


    public boolean check(String password){
        return (this.password.equals(password));
    }


}
