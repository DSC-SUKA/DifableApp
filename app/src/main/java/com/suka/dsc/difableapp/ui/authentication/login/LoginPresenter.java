package com.suka.dsc.difableapp.ui.authentication.login;

import android.util.Log;

import com.suka.dsc.difableapp.model.ResponseLogin;
import com.suka.dsc.difableapp.model.ResponseUserData;
import com.suka.dsc.difableapp.service.ApiClient;
import com.suka.dsc.difableapp.service.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter  {
    private LoginView mView;
    private ApiInterface mApiInterface;

    public LoginPresenter(LoginView mView) {
        this.mView = mView;
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    void doLogin(String email, String password){
        mView.showLoading();
        Call<ResponseLogin> call = mApiInterface.login(email, password);
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (response.body().getStatusLogin()){
                    mView.onSuccessLogin(response.body().getData());
                } else {
                    mView.onErrorLogin(response.body().getMessage());
                    mView.hideLoading();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                mView.hideLoading();
                Log.d("Login Failure: ", t.toString());
            }
        });
    }

    void getUserData(String userId){
        Call<ResponseUserData> call = mApiInterface.getUserData(userId);
        call.enqueue(new Callback<ResponseUserData>() {
            @Override
            public void onResponse(Call<ResponseUserData> call, Response<ResponseUserData> response) {
                mView.hideLoading();
                mView.onResponseUserData(response.body().getData());
            }

            @Override
            public void onFailure(Call<ResponseUserData> call, Throwable t) {
                mView.hideLoading();
                Log.d("Get User Data Failure: ", t.toString());
            }
        });
    }

}
