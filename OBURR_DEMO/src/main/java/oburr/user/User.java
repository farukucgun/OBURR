/**
 *@CenkOlcay
 *java version 16.0.1
 */
package oburr.user;

import oburr.searching.*;

import java.util.ArrayList;

public class User{

    private String name;
    private String password;
    private int userId;
    private String dietType;

    private ArrayList<Ingredient> dislikedIngredients = new ArrayList<Ingredient>();
    private ArrayList<Ingredient> likedIngredients = new ArrayList<Ingredient>();
    private ArrayList<Ingredient> allergenIngredients = new ArrayList<Ingredient>();
    private ArrayList<Ingredient> bannedIngredients = new ArrayList<Ingredient>();

    public User( String name, String password ){
        this.name = name;
        this.password = password;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setDietType(String dietType){
        this.dietType = dietType;
    }
    public void setUserId(int userId){this.userId = userId;}

    public void setDislikedIngredients(ArrayList<Ingredient> dislikedIngredients){
        this.dislikedIngredients = dislikedIngredients;
    }

    public void setLikedIngredients(ArrayList<Ingredient> likedIngredients){
        this.likedIngredients = likedIngredients;
    }
    public void setAllergenIngredients( ArrayList<Ingredient> allergenIngredients ){
        this.allergenIngredients = allergenIngredients;
    }
    public void setBannedIngredients( ArrayList<Ingredient> bannedIngredients ){
        this.bannedIngredients = bannedIngredients;
    }


    public void updateName(String name){
        this.name = name;
        DBCaller dbCaller = new DBCaller();
        dbCaller.updateName(this);
    }

    public void updatePassword(String password){

        this.password = password;
        DBCaller dbCaller = new DBCaller();
        dbCaller.updatePassword(this);

    }
    public void updateAllergenItems( Ingredient i ){
        allergenIngredients.add( i );
    }
    public void updateLikedItems( Ingredient i ){
        likedIngredients.add( i );
    }
    public void updateDislikedItems( Ingredient i ){
        dislikedIngredients.add( i );
    }
    public void deleteAllergenItem( String str ){
        allergenIngredients.removeIf(i -> i.toString().equals(str));
    }
    public void deleteLikedItem( String str ){
        likedIngredients.removeIf(i -> i.toString().equals(str));
    }
    public void deleteDislikedItem( String str ){
        dislikedIngredients.removeIf(i -> i.toString().equals(str));

    }
    public void updateDiet(){}


    public String getName(){ return name;}
    public int getUserId(){ return userId;}
    public String getPassword(){ return password;}
    public String getDietType(){ return dietType;}
    public ArrayList<Ingredient> getDisLikedIngredients(){ return dislikedIngredients;}
    public ArrayList<Ingredient> getLikedIngredients(){ return likedIngredients;}
    public ArrayList<Ingredient> getAllergies(){
        return allergenIngredients;
    }
    public ArrayList<Ingredient> getBannedIngredients(){
        return bannedIngredients;
    }

    public String getAllergenIngredientsAsString(){
        String s = "";
        for( Ingredient i : allergenIngredients ){
            s = s + i.toString() + ",";
        }
        return s;
    }
    public String getLikedIngredientsAsString(){
        String s = "";
        for( Ingredient i : likedIngredients ){
            s = s + i.toString() + ",";
        }
        return s;
    }
    public String getDislikedIngredientsAsString(){
        String s = "";
        for( Ingredient i : dislikedIngredients ){
            s = s + i.toString() + ",";
        }
        return s;
    }




}