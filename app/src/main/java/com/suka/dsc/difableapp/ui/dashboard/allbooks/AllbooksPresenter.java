package com.suka.dsc.difableapp.ui.dashboard.allbooks;

import android.util.Log;

import com.suka.dsc.difableapp.model.AudioResponses;
import com.suka.dsc.difableapp.model.AudioResponsesData;
import com.suka.dsc.difableapp.model.BookCategoriesData;
import com.suka.dsc.difableapp.model.BookCategoriesModel;
import com.suka.dsc.difableapp.model.CategoryResponses;
import com.suka.dsc.difableapp.service.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllbooksPresenter {
    private AllbooksView mView;
    private ApiInterface mApiInterface;

    public AllbooksPresenter(AllbooksView view, ApiInterface apiInterface) {
        this.mView = view;
        this.mApiInterface = apiInterface;
    }

    public void getCategoriesList(){
        mView.showLoading();
        Call<CategoryResponses> allbookModelCall = mApiInterface.getAllCategories();
        allbookModelCall.enqueue(new Callback<CategoryResponses>() {
            @Override
            public void onResponse(Call<CategoryResponses> call, Response<CategoryResponses> response) {
                Log.d("Retrofit Get", "categories count " + response.body().getData().size());
                mView.hideLoading();
                mView.showData(response.body());

            }

            @Override
            public void onFailure(Call<CategoryResponses> call, Throwable t) {
                Log.e("Retrofit Get", "Failure: " + t.toString());
            }
        });
    }
}
