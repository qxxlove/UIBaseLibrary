package com.bool.uibaselibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.bool.uibaselibrary.adapter.SeeMoreAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewExpendActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_expend2);
        initView();
    }




    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_main);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("哈萨ki" + i);
        }
        SeeMoreAdapter seeMoreAdapter = new SeeMoreAdapter(list);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setAdapter(seeMoreAdapter);


        seeMoreAdapter.setOnITEMClickListener(new SeeMoreAdapter.OnItemClick() {
            @Override
            public void onItemClick(int position) {
                int mPosition = position + 1;
                Toast.makeText(getBaseContext(),
                        "点击了第"+ mPosition +"个哈萨ki",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
