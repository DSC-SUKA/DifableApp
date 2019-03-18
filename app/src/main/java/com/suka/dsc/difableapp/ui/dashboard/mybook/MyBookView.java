package com.suka.dsc.difableapp.ui.dashboard.mybook;

import com.suka.dsc.difableapp.model.AudioResponsesData;

import java.util.List;

public interface MyBookView {
    void showData(List<AudioResponsesData> data);
    void showLoading();
    void hideLoading();
}
