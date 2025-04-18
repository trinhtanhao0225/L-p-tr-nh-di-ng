package com.example.vidu2loadmoredatatrenrecyclerview;

import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<String> rowsArrayList = new ArrayList<>();
    boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);
        populateData();
        initAdapter();
        inintScrollListener();
    }

    private void inintScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager)  recyclerView.getLayoutManager();

                if (!isLoading){
                    //kiem tra nguoi dung cuon toi danh sach cuoi cung chua
                    if(linearLayoutManager != null
                    && linearLayoutManager.findLastCompletelyVisibleItemPosition() ==  rowsArrayList.size() -1 ){
                        loadMore();
                        isLoading = true;
                    }
                }
            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    private void loadMore() {
        //them phan tu null
        rowsArrayList.add(null);
        //hien thi loading
        recyclerViewAdapter.notifyItemInserted(rowsArrayList.size()-1);
        Handler handler = new Handler();
        //doi 2 giay
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                rowsArrayList.remove(rowsArrayList.size()-1);

                //xoa loading ra khoi danh sach
                int scrollPosition = rowsArrayList.size();
                recyclerViewAdapter.notifyItemRemoved(scrollPosition);

                //tao them 10 phan tu moi
                int currentSize = scrollPosition;
                int nextLimit = currentSize + 10;

                while (currentSize -1 < nextLimit){
                    rowsArrayList.add("Item " + currentSize);
                    currentSize++;
                }

                recyclerViewAdapter.notifyDataSetChanged();

                //tranh goi loading lien tuc
                isLoading = false;
            }
        }, 2000);
    }

    private void initAdapter() {
        recyclerViewAdapter = new RecyclerViewAdapter(rowsArrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void populateData() {
        int i = 0;
        while (i < 10){
            rowsArrayList.add("Item "+ i);
            i++;
        }
    }
}