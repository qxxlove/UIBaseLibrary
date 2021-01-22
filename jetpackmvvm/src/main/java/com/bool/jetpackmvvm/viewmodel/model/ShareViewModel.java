package com.bool.jetpackmvvm.viewmodel.model;

import androidx.lifecycle.ViewModel;

public class ShareViewModel extends ViewModel {


    private  String text = "";

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
