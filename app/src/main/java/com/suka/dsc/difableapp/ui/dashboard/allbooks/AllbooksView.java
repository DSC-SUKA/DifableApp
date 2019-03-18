package com.suka.dsc.difableapp.ui.dashboard.allbooks;

import com.suka.dsc.difableapp.model.ResponseCategory;

public interface AllbooksView {
    void showData(ResponseCategory data);
    void showLoading();
    void hideLoading();
}
