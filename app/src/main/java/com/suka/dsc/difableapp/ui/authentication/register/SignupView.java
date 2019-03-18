package com.suka.dsc.difableapp.ui.authentication.register;

import com.suka.dsc.difableapp.model.ResponseImageUpload;
import com.suka.dsc.difableapp.model.ResponseSignup;
import com.suka.dsc.difableapp.model.UserData;

public interface SignupView {
    void showLoading();
    void hideLoading();
    void onSuccessRegister(UserData userData);
    void onErrorRegister(String errorMessage);
    void onSuccessUploadImage(ResponseImageUpload responseImageUpload);
    void onErrorUploadImage(String message);
}
