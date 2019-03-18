package com.suka.dsc.difableapp.ui.allbooksextend;

import android.util.Log;

import com.suka.dsc.difableapp.model.ResponseAudio;
import com.suka.dsc.difableapp.service.ApiInterface;

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

    public void getAllbooks(String category){
        mView.showLoading();
        Call<ResponseAudio> bookModelCall = mApiInterface.getAllbooksByCat(category);
        bookModelCall.enqueue(new Callback<ResponseAudio>() {
            @Override
            public void onResponse(Call<ResponseAudio> call, Response<ResponseAudio> response) {
                Log.d("Retrofit Get", "Book request count: " + response.body().getData().size());
                mView.hideLoading();
                mView.showData(response.body());

            }

            @Override
            public void onFailure(Call<ResponseAudio> call, Throwable t) {
                Log.e("Retrofit Get", "Failure: " + t.toString());
            }
        });
    }
}
