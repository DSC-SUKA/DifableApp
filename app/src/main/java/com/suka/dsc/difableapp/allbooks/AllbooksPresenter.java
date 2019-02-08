package com.suka.dsc.difableapp.allbooks;

import android.util.Log;

import com.suka.dsc.difableapp.model.AllbookCategoriesData;
import com.suka.dsc.difableapp.model.AllbookCategoriesModel;
import com.suka.dsc.difableapp.network.ApiInterface;

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

    public void getAllbooksCategoriesList(){
        mView.showLoading();
        Call<AllbookCategoriesModel> allbookModelCall = mApiInterface.getAllbookCategories();
        allbookModelCall.enqueue(new Callback<AllbookCategoriesModel>() {
            @Override
            public void onResponse(Call<AllbookCategoriesModel> call, Response<AllbookCategoriesModel> response) {
                Log.d("Retrofit Get", "Book categories request count: " + response.body().getmAllbookItems().size());
                List<AllbookCategoriesData> allbookCategoriesDataList = response.body().getmAllbookItems();
                mView.hideLoading();
                mView.showData(allbookCategoriesDataList);
            }

            @Override
            public void onFailure(Call<AllbookCategoriesModel> call, Throwable t) {
                Log.e("Retrofit Get", "Failure: " + t.toString());
            }
        });
    }
}
