package com.suka.dsc.difableapp.allbooks;

import com.suka.dsc.difableapp.model.AllbookCategoriesData;

import java.util.List;

public interface AllbooksView {
    void showData(List<AllbookCategoriesData> data);
    void showLoading();
    void hideLoading();
}
