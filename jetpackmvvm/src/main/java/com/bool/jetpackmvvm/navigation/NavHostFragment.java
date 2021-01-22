package com.bool.jetpackmvvm.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bool.jetpackmvvm.R;
import com.bool.jetpackmvvm.viewmodel.model.ShareViewModel;

public class NavHostFragment extends Fragment {

    private ShareViewModel model;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_left_tv, container, false);
        //设置数据
        model = new ViewModelProvider(getActivity()).get(ShareViewModel.class);
        model.setText("小鑫你好啊");

        view.findViewById(R.id.tv_result_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //does not have a NavController set
              // Navigation.findNavController(v).navigate(R.id.fragment_right);
            }
        });
        return view;
    }
}
