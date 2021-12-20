package oburr.searching.sorting;

import oburr.searching.webSearching.SearchResult;

import java.util.Comparator;

public class SortByName implements Comparator<SearchResult>{

    public int compare(SearchResult firstResult, SearchResult secondResult){
        return firstResult.getRecipeTitle().compareTo(secondResult.getRecipeTitle());
    }

}
