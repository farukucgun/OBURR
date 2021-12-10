/**
 *@CenkOlcay
 *java version 16.0.1
 */
package oburr.user;

import java.util.ArrayList;
import oburr.searching.Ingredient;

public class User implements Editable{

    private String name;
    private String password;
    private char dietType;
    private boolean isEditable;

    private ArrayList<Ingredient> dislikedIngredients, likedIngredients;

    public void setName(String name){
        this.name = name;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setDietType(char dietType){
        this.dietType = dietType;
    }

    public void setDislikedIngredients(ArrayList<Ingredient> dislikedIngredients){
        this.dislikedIngredients = dislikedIngredients;
    }

    public void setLikedIngredients(ArrayList<Ingredient> likedIngredients){
        this.likedIngredients = likedIngredients;
    }


    public void lock(){
        isEditable = false;
    }

    public void unlock(){
        isEditable = true;
    }

    public void updatePassword(String password){
        check(password);

        if(isEditable){
            this.name = name;
            DBCaller dbCaller = new DBCaller();
        }

        lock();
    }
    public void updateName(String name){}
    public void updateLikedItems(){}
    public void updateDislikedItems(){}
    public void updateDiet(){}
    public void setIsEditable(boolean isEditable){
        this.isEditable = isEditable;
    }

    public String getName(){ return name;}
    public char getDietType(){ return dietType;}
    public boolean getIsEditable(){ return isEditable;}
    public ArrayList<Ingredient> getDisLikedIngredients(){ return dislikedIngredients;}
    public ArrayList<Ingredient> getLikedIngredients(){ return likedIngredients;}

    public boolean check(String password){
        return (this.password.equals(password));
    }


}
