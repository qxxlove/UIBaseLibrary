package com.bool.jetpackmvvm.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.bool.jetpackmvvm.room.database.MyDatabase;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {

    private MyDatabase myDatabase;
    private LiveData<List<Student>> listLiveData;

    public StudentViewModel(@NonNull Application application) {
        super(application);
        //对数据库进行初始化
        myDatabase = MyDatabase.getInstance(application);
        //获取学生数据
        listLiveData = myDatabase.studentDao().getLiveStudentList();
    }

    public  LiveData<List<Student>> getListLiveData(){
        return  listLiveData;
    }
}
