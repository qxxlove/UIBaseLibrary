package com.bool.jetpackmvvm.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bool.jetpackmvvm.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);



        /**
         *  navigate 的参数可以是一个 destination（这里就是 fragment 在导航图 nav_graph 中的 id），
         *          也可以是 action 的 id
         * */
        //方法一

        view.findViewById(R.id.btnToSecondFragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**这种传法数据安全，也可以直接用Bundle,但是不安全，但是下面还报错着呢*/
              /*  Bundle bundle = new MainFragmentArgs.Builder().
                        setUserNmae("Shaoyi")
                        .setAge(20)
                        .build().toBundle();*/
                Navigation.findNavController(v)
                        .navigate(R.id.action_mainFragment_to_leftFragment);
            }
        });

        //方法二
        view.findViewById(R.id.btnToRightFragment).setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_rightFragment)
        );

        view.findViewById(R.id.btnToActivity).setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.testActivity)
        );


        return view;
    }
}