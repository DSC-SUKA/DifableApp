package com.suka.dsc.difableapp.ui.allbooksextend;

import android.util.Log;

import com.suka.dsc.difableapp.model.BookData;
import com.suka.dsc.difableapp.model.BookModel;
import com.suka.dsc.difableapp.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllbooksExtendPresenter {
    private AllbooksExtendView mView;
    private ApiInterface mApiInterface;

    public AllbooksExtendPresenter(AllbooksExtendView view, ApiInterface apiInterface) {
        this.mView = view;
        this.mApiInterface = apiInterface;
    }

    public void getAllbooks(String idCategory){
        mView.showLoading();
        Call<BookModel> bookModelCall = mApiInterface.getAllbook(idCategory);
        bookModelCall.enqueue(new Callback<BookModel>() {
            @Override
            public void onResponse(Call<BookModel> call, Response<BookModel> response) {
                Log.d("Retrofit Get", "Book categories request count: " + response.body().getmBookData().size());
                List<BookData> bookDataList = response.body().getmBookData();
                mView.hideLoading();
                mView.showData(bookDataList);

            }

            @Override
            public void onFailure(Call<BookModel> call, Throwable t) {
                Log.e("Retrofit Get", "Failure: " + t.toString());
            }
        });
    }
}
