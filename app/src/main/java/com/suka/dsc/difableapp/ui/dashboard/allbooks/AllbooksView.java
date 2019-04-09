package com.suka.dsc.difableapp.ui.dashboard.allbooks;

import com.suka.dsc.difableapp.model.AudioResponsesData;
import com.suka.dsc.difableapp.model.ResponseCategory;

import java.util.List;

public interface AllbooksView {
    void showData(List<AudioResponsesData> data);
    void showLoading();
    void hideLoading();
}
