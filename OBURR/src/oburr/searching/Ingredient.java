package oburr.searching;

public class Ingredient {

    private String name;
    private String quantity;
    private String detail;

    public Ingredient(String description){
        analyzeDescription(description);
    }

    public void setName(String name){

        this.name = name;
    }

    public void analyzeDescription(String description){



    }

    public void setQuantity(String quantity){
        this.quantity = quantity;
    }

    public void setDetail(String detail){
        this.detail = detail;
    }

    public String getName(){ return name;}
    public String getQuantity(){ return quantity;}
    public String getDetail(){ return detail;}

}
