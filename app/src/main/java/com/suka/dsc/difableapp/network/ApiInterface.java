package com.suka.dsc.difableapp.network;

import com.suka.dsc.difableapp.model.BookCategoriesModel;
import com.suka.dsc.difableapp.model.BookModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("allbook_categories")
    Call<BookCategoriesModel> getAllbookCategories();

    @GET("lookup_allbook")
    Call<BookModel> getAllbook(@Query("id") String idCategory);
}
