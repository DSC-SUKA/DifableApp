package com.suka.dsc.difableapp.ui.dashboard.mybook;


import android.util.Log;

import com.suka.dsc.difableapp.model.ResponseAudio;
import com.suka.dsc.difableapp.service.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyBookPresenter {
    private MyBookView mView;
    private ApiInterface mApiInterface;

    public MyBookPresenter(MyBookView mView, ApiInterface mApiInterface) {
        this.mView = mView;
        this.mApiInterface = mApiInterface;
    }

    public void getMyBookList(String userId){
        mView.showLoading();
        Call<ResponseAudio> audioResponsesCall = mApiInterface.getMyBook(userId);
        audioResponsesCall.enqueue(new Callback<ResponseAudio>() {
            @Override
            public void onResponse(Call<ResponseAudio> call, Response<ResponseAudio> response) {
                Log.d("Retrofit Get", "My book count: " + response.body().getData().size());
                mView.hideLoading();
                mView.showData(response.body().getData());

            }

            @Override
            public void onFailure(Call<ResponseAudio> call, Throwable t) {
                Log.e("Retrofit Get", "Failure: " + t.toString());
            }
        });
    }
}
