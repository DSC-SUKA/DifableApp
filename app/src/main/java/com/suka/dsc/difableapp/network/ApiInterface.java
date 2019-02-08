package com.suka.dsc.difableapp.network;

import com.suka.dsc.difableapp.model.AllbookCategoriesModel;
import com.suka.dsc.difableapp.model.BookModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("allbook_categories")
    Call<AllbookCategoriesModel> getAllbookCategories();

    @GET("lookup_allbook")
    Call<BookModel> getAllbook(@Query("id") String idCategory);
}
