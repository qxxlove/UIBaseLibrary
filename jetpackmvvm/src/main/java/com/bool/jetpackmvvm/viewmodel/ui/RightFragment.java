package com.bool.jetpackmvvm.viewmodel.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.bool.jetpackmvvm.R;
import com.bool.jetpackmvvm.viewmodel.model.ShareViewModel;


public class RightFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_right_tv, container, false);
        ShareViewModel model = new ViewModelProvider(getActivity()).get(ShareViewModel.class);
        //取出数据
        String text = model.getText();
        //显示在UI上
        TextView textView = view.findViewById(R.id.tv_result_right);
        textView.setText(text);
        initClick(view);
        return view;
    }

    private void initClick(View view) {
        view.findViewById(R.id.tv_result_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getActivity().startActivity(new Intent(getActivity(),TimerActivity.class));
            }
        });

        view.findViewById(R.id.tv_global).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //does not have a NavController set
                Navigation.findNavController(v).navigate(R.id.action_global_mainFragment);
            }
        });
    }
}
