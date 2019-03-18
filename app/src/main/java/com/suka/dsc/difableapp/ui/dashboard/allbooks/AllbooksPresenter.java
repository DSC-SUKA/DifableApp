package com.suka.dsc.difableapp.ui.dashboard.allbooks;

import android.util.Log;

import com.suka.dsc.difableapp.model.ResponseCategory;
import com.suka.dsc.difableapp.service.ApiInterface;

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
        Call<ResponseCategory> allbookModelCall = mApiInterface.getAllCategories();
        allbookModelCall.enqueue(new Callback<ResponseCategory>() {
            @Override
            public void onResponse(Call<ResponseCategory> call, Response<ResponseCategory> response) {
                Log.d("Retrofit Get", "categories count " + response.body().getData().size());
                mView.hideLoading();
                mView.showData(response.body());

            }

            @Override
            public void onFailure(Call<ResponseCategory> call, Throwable t) {
                Log.e("Retrofit Get", "Failure: " + t.toString());
            }
        });
    }
}
