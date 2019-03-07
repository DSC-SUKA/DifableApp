package com.suka.dsc.difableapp.ui.dashboard.allbooks;

import com.suka.dsc.difableapp.model.BookCategoriesData;
import com.suka.dsc.difableapp.model.CategoryResponses;

import java.util.List;

public interface AllbooksView {
    void showData(CategoryResponses data);
    void showLoading();
    void hideLoading();
}
