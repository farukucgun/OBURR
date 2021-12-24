/**
 * @EnesBektas
 * java version 14.0.2
 */

package oburr.user;
import oburr.searching.Ingredient;
import oburr.searching.OburrRecipe;
import oburr.searching.OburrResource;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class UserUpdater {

    // Lists for banned ingredients related to diet types
    public static final String[] PROTEIN_BASED_BANS = { "sugar", "soda", "candy", "margarine", "bread", "cracker" };
    public static final String[] KETO_BANS = { "bean", "beer", "fruit", "lentil", "liquor", "mayonnaise", "pasta", "potato", "pudding", "rice", "steak", "sugar", "wine" };
    public static final String[] LOW_CARB_BANS = { "bread", "candy", "ice cream", "margarine", "pasta", "rice", "sugar" };
    public static final String[] GLUTEN_FREE_BANS = { "biscuit", "bread", "cake", "cereal", "cracker", "pasta", "pie" };
    public static final String[] VEGETARIAN_BANS = { "beef", "chicken", "duck", "gelatin", "haddock", "pork", "salmon", "shrimp", "trout", "tuna", "turkey", "veal" };
    public static final String[] VEGAN_BANS = { "beef", "butter", "cheese", "chicken", "cream", "duck", "egg", "gelatin", "haddock", "honey", "ice cream", "mayonnaise", "milk", "pork", "steak", "salmon", "shrimp", "trout", "tuna", "turkey", "veal" };
    public static final String[] PALEO_BANS = { "bean", "bread", "butter", "candy", "cheese", "lentil", "margarine", "milk", "pasta", "sugar", "wheat", "yogurt" };
    public static final String[] LACTOSE_FREE_BANS = { "butter", "buttermilk", "cheese", "cream", "ice cream", "kefir", "milk", "sour cream", "yogurt" };

    // DBCaller and User objects
    static DBCaller connector = new DBCaller();
    static User user;


    /**
     * This method is for Signing in an existing user
     * @param userName user name
     * @param userPassword password
     * @return true if successfully logged in, false otherwise
     */
    public static boolean login( String userName, String userPassword) {
        String query = "SELECT * FROM OS2g280ES9.oburr_userInfo where user_name = ";
        query = query + "'" + userName + "' AND user_password = '" + userPassword + "'";
        if( connector.checkDB( query ) ){
            user = new User( userName, userPassword );
            user.setUserId( connector.returnId( user ) );
            user.setDislikedIngredients( dislikesFromDatabase() );
            user.setLikedIngredients( likesFromDatabase() );
            user.setAllergenIngredients( allergensFromDatabase() );
            user.setBannedIngredients( bannedItemsFromDatabase() );
            return true;
        }
        else{
            // If account does not exist
            return false;
        }
    }

    /**
     * This method is for signing up a not-existing user
     * @param userName user name
     * @param userPassword password
     * @return true if successfully registered, false otherwise
     */
    public static boolean register( String userName, String userPassword ) {

        String query = "SELECT * FROM OS2g280ES9.oburr_userInfo where user_name = ";
        query = query + "'" + userName + "'";
        if ( !connector.checkDB( query ) ){
            user = new User( userName, userPassword );
            connector.insertUser( user );
            user.setUserId( connector.returnId( user ) );
            connector.insertUserLikesAndDislikes( user );
            return true;

        }
        else{
            // If username is already in uer
            return false;
        }

    }

    /**
     * Changes banned items according to diet type
     * @param dietType diet type
     */
    public static void changeBannedItems( String dietType ){
        initializeBannedItems( dietType );
    }

    /**
     * Changes dietType according to given string
     * @param dietType diet type
     */
    public static void changeDietType( String dietType ){
        user.setDietType( dietType );
    }

    /**
     * Resets diet type to default (no dietic preferences)
     */
    public static void removeDietType(){
        user.setDietType( "" );
    }

    /**
     * Updates diet type in the database
     */
    public static void updateDietTypeInfo(){
        connector.updateDietType( user );
    }

    /**
     * Adds allergen ingredient
     * @param item allergen ingredient
     */
    public static void addAllergenItem( String item ){
        user.updateAllergenItems( new Ingredient( item ) );
    }

    /**
     * Adds liked ingredient
     * @param item liked ingredient
     */
    public static void addLikedItem( String item ){
        user.updateLikedItems( new Ingredient( item ));
    }

    /**
     * Adds disliked ingredient
     * @param item disliked ingredient
     */
    public static void addDislikedItem( String item ){
        user.updateDislikedItems( new Ingredient( item ));
    }

    /**
     * Removes allergen ingredient with given name
     * @param item allergen ingredient
     */
    public static void removeAllergenItem( String item ){
        user.deleteAllergenItem( item );
    }

    /**
     * Removes liked ingredient with given name
     * @param item liked ingredient
     */
    public static void removeLikedItem( String item ){
        user.deleteLikedItem( item );
    }

    /**
     * Removes disliked ingredient with given name
     * @param item disliked ingredient
     */
    public static void removeDislikedItem( String item ){
        user.deleteDislikedItem( item );
    }

    /**
     * Updates database
     */
    public static void updateAllergenInfo(){
        connector.updateAllergens( user );
    }

    /**
     * Updates database
     */
    public static void updateLikedInfo(){
        connector.updateLiked( user );
    }

    /**
     * Updates database
     */
    public static void updateDislikedInfo(){
        connector.updateDisliked( user );
    }

    /**
     * Initializes banned ingredients according to diet type
     * @param dietType diet type
     * @return banned ingredient arraylist
     */
    public static ArrayList<Ingredient> initializeBannedItems( String dietType){
        ArrayList<Ingredient> tmp = new ArrayList<Ingredient>();
        if( dietType.equalsIgnoreCase( "Protein-based" ) ){
            for( String ingredient: PROTEIN_BASED_BANS){
                tmp.add( new Ingredient( ingredient ) );
            }
        }
        else if( dietType.equalsIgnoreCase( "keto" ) ){
            for( String ingredient: KETO_BANS){
                tmp.add( new Ingredient( ingredient ) );
            }
        }
        else if( dietType.equalsIgnoreCase( "low carb" ) ){
            for( String ingredient: LOW_CARB_BANS){
                tmp.add( new Ingredient( ingredient ) );
            }
        }
        else if( dietType.equalsIgnoreCase( "gluten free" ) ){
            for( String ingredient: GLUTEN_FREE_BANS){
                tmp.add( new Ingredient( ingredient ) );
            }
        }
        else if( dietType.equalsIgnoreCase( "vegetarian" ) ){
            for( String ingredient: VEGETARIAN_BANS){
                tmp.add( new Ingredient( ingredient ) );
            }
        }
        else if( dietType.equalsIgnoreCase( "vegan" ) ){
            for( String ingredient: VEGAN_BANS){
                tmp.add( new Ingredient( ingredient ) );
            }
        }
        else if( dietType.equalsIgnoreCase( "paleo" ) ){
            for( String ingredient: PALEO_BANS){
                tmp.add( new Ingredient( ingredient ) );
            }
        }
        else if( dietType.equalsIgnoreCase( "lactose free" ) ){
            for( String ingredient: LACTOSE_FREE_BANS){
                tmp.add( new Ingredient( ingredient ) );
            }
        }
        return tmp;
    }

    /**
     * Returns banned ingredients from database
     * @return banned ingredients
     */
    public static ArrayList<Ingredient> bannedItemsFromDatabase(){
        String dietType = connector.returnDietType( user );
        return initializeBannedItems( dietType );
    }

    /**
     * Returns dietType from database
     * @return dietType
     */
    public static String dietTypeFromDatabase(){
        return connector.returnDietType( user );
    }

    /**
     * Returns allergen ingredients from database
     * @return allergen ingredients
     */
    public static ArrayList<Ingredient> allergensFromDatabase(){
        String allergens = connector.returnAllergens( user );
        return changeStringIngredientsToArrayList( allergens );
    }

    /**
     * Returns liked ingredients from database
     * @return liked ingredients
     */
    public static ArrayList<Ingredient> likesFromDatabase(){
        String likes = connector.returnLikes( user );
        return changeStringIngredientsToArrayList( likes );
    }

    /**
     * Returns disliked ingredients from database
     * @return disliked ingredients
     */
    public static ArrayList<Ingredient> dislikesFromDatabase(){
        String dislikes = connector.returnDislikes( user );
        return changeStringIngredientsToArrayList( dislikes );
    }

    /**
     * Turns a string of ingredients into an ArrayList of ingredients
     * @param ingredients ingredients
     * @return ingredients ArrayList
     */
    public static ArrayList<Ingredient> changeStringIngredientsToArrayList( String ingredients ){
        ArrayList<Ingredient> tmp = new ArrayList<Ingredient>();
        int firstIndex = -1;
        int secondIndex = ingredients.indexOf( "," );
        while( secondIndex != -1 ){
            String tmpStr = ingredients.substring( firstIndex + 1, secondIndex );
            firstIndex = secondIndex;
            secondIndex = ingredients.indexOf( ",", firstIndex + 1 );
            tmp.add( new Ingredient( tmpStr ) );
        }
        return tmp;
    }

    /**
     * Returns password of the current user
     * @return password
     */
    public static String returnPassword(){
        return user.getPassword();
    }

    /**
     * Changes password of the current user
     * @param newPassword password
     */
    public static void changePassword( String newPassword ){
        user.updatePassword( newPassword );
    }

    /**
     * Changes username of the current user
     * @param newUserName username
     */
    public static void changeUserName( String newUserName ){
        user.updateName( newUserName );
    }

    /**
     * Creates a recipe object and uploads it to database
     * @param recipeName recipe name
     * @param calories recipe calories
     * @param time recipe time
     * @param ingredients recipe ingredients
     * @param steps recipe steps
     * @param extensionType extension type of the image
     * @param image image
     */
    public static void createOburrRecipe( String recipeName, int calories, int time, String ingredients, String steps, String extensionType, BufferedImage image ){
        ingredients = ingredients + ",";
        ArrayList<Ingredient> ingredientList = changeStringIngredientsToArrayList( ingredients );
        OburrRecipe recipe = new OburrRecipe( recipeName, user.getName(), user.getUserId(), ingredientList, steps, "", time, "", calories, user, image);
        OburrResource resource = new OburrResource(user);
        resource.uploadOburrRecipe( recipe, extensionType );
    }

    /**
     * Returns added recipes of the current user from database
     * @return recipe
     */
    public static ArrayList<OburrRecipe> returnAddedRecipes(){
        OburrResource resource = new OburrResource(user);
        return resource.bringRecipes(0, user.getUserId());
    }

    /**
     * Returns current user
     * @return user
     */
    public static User returnUser(){
        return user;
    }
}
