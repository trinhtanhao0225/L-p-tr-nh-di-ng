package com.example.hienthidanhsachvoilistview;

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
    ListView listView;
    ArrayList<String> arrayList;
    EditText editText1;
    Button btnNhap;
    Button btnCapNhat, btnXoa;
    int vitri = -1;


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

        //ánh xạ
        listView = (ListView) findViewById(R.id.listview1);
        btnNhap = findViewById(R.id.btnNhap);
        btnCapNhat = (Button) findViewById(R.id.btnCapNhat);
        btnXoa = findViewById(R.id.btnXoa);
        editText1 = findViewById(R.id.edtText);

//Thêm dữ liệu vào List
        arrayList = new ArrayList<>();
        arrayList.add("Java");
        arrayList.add("C#");
        arrayList.add("PHP");
        arrayList.add("Kotlin");
        arrayList.add("Dart");


        //Tạo ArrayAdapter
        ArrayAdapter adapter = new ArrayAdapter(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                arrayList
        );

        listView.setAdapter(adapter);

        //bắt sự kiện click nhanh trên từng dòng của
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //code yêu cầu
                //i: trả về vị trí click chuột trên ListView -> iban đầu =0
                //Toast.makeText(MainActivity.this, ""+position, Toast.LENGTH_SHORT).show();
                editText1.setText(arrayList.get(position));
                vitri = position;
            }
        });

        ////bắt sự kiện click giữ trên từng dòng của Listview
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //code yêu cầu
                //i: trả về vị trí click chuột trên ListView -> i ban đầu =0
                Toast.makeText(MainActivity.this, "Bạn đang nhấn giữ "+ position + "-" + arrayList.get(position) ,
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText1.getText().toString();
                arrayList.add(name);
                adapter.notifyDataSetChanged();
                vitri = arrayList.size()-1;
            }
        });

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.set(vitri, editText1.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Xóa item
                if(vitri >=0){
                    arrayList.remove(vitri);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}