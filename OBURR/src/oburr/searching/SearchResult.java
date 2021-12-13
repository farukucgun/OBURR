package oburr.searching;

public class SearchResult {

    private String resultTitle, resource, url, imageURL;


    public SearchResult(String resultTitle, String resource, String url, String imageURL){
        setResultTitle(resultTitle);
        setResource(resource);
        setUrl(url);
        setImageURL(imageURL);
    }

    public void setResultTitle(String resultTitle){
        this.resultTitle = resultTitle;
    }

    public void setResource(String resource){
        this.resource = resource;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public void setImageURL(String imageURL){
        this.imageURL = imageURL;
    }

    public String getResultTitle(){ return resultTitle;}
    public String getResource(){return resource;}
    public String getUrl(){ return url;}
    public String getImageURL(){ return imageURL;}

}
