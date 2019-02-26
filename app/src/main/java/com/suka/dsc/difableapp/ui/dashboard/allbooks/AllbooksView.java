package com.suka.dsc.difableapp.ui.dashboard.allbooks;

import com.suka.dsc.difableapp.model.BookCategoriesData;

import java.util.List;

public interface AllbooksView {
    void showData(List<BookCategoriesData> data);
    void showLoading();
    void hideLoading();
}
