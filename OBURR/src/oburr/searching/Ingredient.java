package oburr.searching;

public class Ingredient {

    private String description;

    public Ingredient(String description){
        setDescription(description);
    }


    public void setDescription(String description){
        this.description = description;
    }

    public String toString(){ return description;}

}
