package com.suka.dsc.difableapp.ui.dashboard.request;

import android.util.Log;

import com.suka.dsc.difableapp.model.ResponseAudioRequest;
import com.suka.dsc.difableapp.model.UserData;
import com.suka.dsc.difableapp.service.ApiClient;
import com.suka.dsc.difableapp.service.ApiInterface;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestPresenter {
    private ApiInterface mApiInterface;
    private RequestView mView;

    public RequestPresenter(RequestView mView) {
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        this.mView = mView;
    }

    void sendRequest(String audioPath, UserData userData){
        File audio = new File(audioPath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), audio);
        MultipartBody.Part audioBody = MultipartBody.Part.createFormData("audio", audio.getName(), requestBody);

        RequestBody userId = RequestBody.create(MultipartBody.FORM, userData.getUserId());
        RequestBody userImageUrl = RequestBody.create(MultipartBody.FORM, userData.getUserPhotoUrl());
        RequestBody userName = RequestBody.create(MultipartBody.FORM, userData.getUserName());

        Call<ResponseAudioRequest> call = mApiInterface.requestUpload(audioBody, userId, userImageUrl, userName);
        call.enqueue(new Callback<ResponseAudioRequest>() {
            @Override
            public void onResponse(Call<ResponseAudioRequest> call, Response<ResponseAudioRequest> response) {
                Log.d("RequestSent, audioUrl", response.body().getMessage());
                mView.onSuccessSendAudio("Request Sent");
            }

            @Override
            public void onFailure(Call<ResponseAudioRequest> call, Throwable t) {
                Log.d("RequestError, Detail", t.toString());
                mView.onErrorSendAudio("Request not sent, try again...");
            }
        });

    }
}
