package com.suka.dsc.difableapp.ui.authentication.register;

import android.util.Log;

import com.suka.dsc.difableapp.model.ResponseImageUpload;
import com.suka.dsc.difableapp.model.ResponseLogin;
import com.suka.dsc.difableapp.model.ResponseSignup;
import com.suka.dsc.difableapp.service.ApiClient;
import com.suka.dsc.difableapp.service.ApiInterface;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupPresenter {
    private ApiInterface mApiInterface;
    private SignupView mView;

    public SignupPresenter(SignupView mView) {
        this.mView = mView;
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    void uploadPhoto(String imagePath) {
        mView.showLoading();
        File image = new File(imagePath);
        final RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), image);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", image.getName(), requestBody);

        Call<ResponseImageUpload> call = mApiInterface.uploadImage(body);
        call.enqueue(new Callback<ResponseImageUpload>() {
            @Override
            public void onResponse(Call<ResponseImageUpload> call, Response<ResponseImageUpload> response) {
                mView.hideLoading();
                if (response.body().getStatus()) {
                    Log.d("Photo Data : ", response.body().getData());
                    mView.onSuccessUploadImage(response.body());
                } else {
                    Log.d("Upload Image Message: ", response.body().getMessage());
                    mView.onErrorUploadImage(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseImageUpload> call, Throwable throwable) {
                Log.d("Upload Image Failure: ", throwable.toString());
                mView.hideLoading();
            }
        });
    }

    void doSignUp(String email, String password, String phoneNumber, String name, String imageUrl, String role) {
        mView.showLoading();

        Call<ResponseSignup> call = mApiInterface.signup(email, password, phoneNumber, name, imageUrl, role);
        call.enqueue(new Callback<ResponseSignup>() {
            @Override
            public void onResponse(Call<ResponseSignup> call, Response<ResponseSignup> response) {
                mView.hideLoading();
                if (response.body().getStatus()) {
                    mView.onSuccessRegister(response.body().getUserData());
                    Log.d("Registered User ID: ", response.body().getUserData().getUserId());
                } else {
                    mView.onErrorRegister(response.body().getMessage());
                    Log.d("Signup Message", response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseSignup> call, Throwable throwable) {
                Log.d("SignupFailure : ", throwable.toString());
                mView.hideLoading();
            }
        });
    }


}
