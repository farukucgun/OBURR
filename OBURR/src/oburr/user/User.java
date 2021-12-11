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
    private int userId;
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
    public void setUserId(int userId){this.userId = userId;}

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


    public void updateName(String name){}

    public void updatePassword(String password){
        check(password);

        if(isEditable){
            this.name = name;
            DBCaller dbCaller = new DBCaller();
            dbCaller.updatePassword(this);
        }

        lock();
    }

    public void updateLikedItems(){
        DBCaller dbCaller = new DBCaller();
        dbCaller.updateLiked(this);

    }
    public void updateDislikedItems(){
        DBCaller dbCaller = new DBCaller();
        dbCaller.updateDisliked(this);
    }
    public void updateDiet(){}
    public void setIsEditable(boolean isEditable){
        this.isEditable = isEditable;
    }

    public String getName(){ return name;}
    public int getUserId(){ return userId;}
    public String getPassword(){ return password;}
    public char getDietType(){ return dietType;}
    public boolean getIsEditable(){ return isEditable;}
    public ArrayList<Ingredient> getDisLikedIngredients(){ return dislikedIngredients;}
    public ArrayList<Ingredient> getLikedIngredients(){ return likedIngredients;}

    public boolean check(String password){
        return (this.password.equals(password));
    }


}
