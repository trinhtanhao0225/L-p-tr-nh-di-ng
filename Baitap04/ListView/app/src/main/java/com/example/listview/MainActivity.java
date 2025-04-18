package com.example.listview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Khai báo
    ListView listView;
    ArrayList<MonHoc> arrayList;
    MonHocAdapter adapter;
    EditText editText1;
    Button btnNhap, btnCapNhat;

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

        // Ánh xạ
        AnhXa();

        // Tạo Adapter
        adapter = new MonHocAdapter(MainActivity.this, R.layout.row_monhoc, arrayList);

        // Truyền dữ liệu từ adapter ra listView
        listView.setAdapter(adapter);

        // Bắt sự kiện click nhanh trên từng dòng của ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "Vị trí: " + i, Toast.LENGTH_SHORT).show();
            }
        });

        // Bắt sự kiện click giữ trên từng dòng của ListView
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "Bạn đang nhấn giữ " + i + "-" + arrayList.get(i).getName(),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private void AnhXa() {
        listView = findViewById(R.id.listview1);
        // Thêm dữ liệu vào List
        arrayList = new ArrayList<>();
        arrayList.add(new MonHoc("Java", "Java 1", R.drawable.java1));
        arrayList.add(new MonHoc("C#", "C# 1", R.drawable.c1));
        arrayList.add(new MonHoc("PHP", "PHP 1", R.drawable.php1));
        arrayList.add(new MonHoc("Kotlin", "Kotlin 1", R.drawable.kotlin1));
        arrayList.add(new MonHoc("Dart", "Dart 1", R.drawable.dart1));
    }
}