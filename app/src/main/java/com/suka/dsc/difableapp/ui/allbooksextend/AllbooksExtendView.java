package com.suka.dsc.difableapp.ui.allbooksextend;

import com.suka.dsc.difableapp.model.ResponseAudio;

public interface AllbooksExtendView {
    void showData(ResponseAudio data);
    void showLoading();
    void hideLoading();
}
