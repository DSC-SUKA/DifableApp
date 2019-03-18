package com.suka.dsc.difableapp.ui.authentication.login;

import com.suka.dsc.difableapp.model.UserData;
import com.suka.dsc.difableapp.model.UserLoginDetail;

public interface LoginView {
    void showLoading();
    void hideLoading();
    void onSuccessLogin(UserLoginDetail userLoginDetail);
    void onErrorLogin(String errorMessage);
    void onResponseUserData(UserData userData);
}