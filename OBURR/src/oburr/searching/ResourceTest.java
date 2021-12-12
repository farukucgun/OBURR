package oburr.searching;

import oburr.user.User;

public class ResourceTest {

    public static void main(String[] args){

        User user = new User();

        Resource allRecipes = new SearchableResource("AllRecipes,","https://www.allrecipes.com/search/results/?",
                user, );



    }



}
