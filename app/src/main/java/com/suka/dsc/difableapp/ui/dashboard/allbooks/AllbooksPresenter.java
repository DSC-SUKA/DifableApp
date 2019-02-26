package com.suka.dsc.difableapp.ui.dashboard.allbooks;

import android.util.Log;

import com.suka.dsc.difableapp.model.BookCategoriesData;
import com.suka.dsc.difableapp.model.BookCategoriesModel;
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
        Call<BookCategoriesModel> allbookModelCall = mApiInterface.getAllbookCategories();
        allbookModelCall.enqueue(new Callback<BookCategoriesModel>() {
            @Override
            public void onResponse(Call<BookCategoriesModel> call, Response<BookCategoriesModel> response) {
                Log.d("Retrofit Get", "Book categories request count: " + response.body().getmAllbookItems().size());
                List<BookCategoriesData> bookCategoriesDataList = response.body().getmAllbookItems();
                mView.hideLoading();
                mView.showData(bookCategoriesDataList);
            }

            @Override
            public void onFailure(Call<BookCategoriesModel> call, Throwable t) {
                Log.e("Retrofit Get", "Failure: " + t.toString());
            }
        });
    }
}
