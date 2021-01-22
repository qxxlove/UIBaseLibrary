package com.bool.jetpackmvvm.livedata;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {

    private MutableLiveData<String> content;

    @Override
    protected void onCleared() {
        super.onCleared();
        //释放资源
        content=  null;
    }

    public LiveData<String> getContent(){
        if(content == null){
            content = new MutableLiveData<>();
        }
        return  content;
    }


}
