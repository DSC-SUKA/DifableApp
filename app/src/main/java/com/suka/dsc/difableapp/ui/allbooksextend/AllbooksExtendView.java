package com.suka.dsc.difableapp.ui.allbooksextend;

import com.suka.dsc.difableapp.model.BookData;

import java.util.List;

public interface AllbooksExtendView {
    void showData(List<BookData> data);
    void showLoading();
    void hideLoading();
}
