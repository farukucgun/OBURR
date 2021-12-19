package oburr.searching;

import java.util.Comparator;

public class SortByTime implements Comparator<SearchResult> {

    public int compare(SearchResult firstResult, SearchResult secondResult){
        return firstResult.getRecipe().getTotalTime() - secondResult.getRecipe().getTotalTime();
    }

}
