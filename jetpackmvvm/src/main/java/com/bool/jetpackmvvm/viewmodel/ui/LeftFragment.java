package com.bool.jetpackmvvm.viewmodel.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.bool.jetpackmvvm.R;
import com.bool.jetpackmvvm.viewmodel.model.ShareViewModel;

public class LeftFragment extends Fragment {

    private ShareViewModel model;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_left_tv, container, false);
        //设置数据
        model = new ViewModelProvider(getActivity()).get(ShareViewModel.class);
        model.setText("我是右边");


      /*  Bundle bundle = getArguments();
        if(bundle!=null){
            String userName = MainFragmentArgs.fromBundle(bundle).getUserNmae();
            int age = MainFragmentArgs.fromBundle(bundle).getAge();
            Toast.makeText(getActivity(), "名字为"+userName+"年龄为"+age, Toast.LENGTH_SHORT).show();
        }*/


        view.findViewById(R.id.tv_result_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //does not have a NavController set

              Navigation.findNavController(v).navigate(R.id.action_leftFragment_to_rightFragment);
            }
        });
        return view;
    }
}
