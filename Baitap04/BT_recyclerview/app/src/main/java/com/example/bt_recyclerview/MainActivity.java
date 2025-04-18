package com.example.bt_recyclerview;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SongAdapter mSongAdapter;
    private List<SongModel> mSongs;

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

        AnhXa();
        //set adapter
        mSongAdapter = new SongAdapter(mSongs, this);
        recyclerView.setAdapter(mSongAdapter);

        //set layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,  LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    public void AnhXa(){
        recyclerView = findViewById(R.id.recyclerview);

        //tao du lieu
        mSongs = new ArrayList<>();
        mSongs.add(new SongModel("60696", "NẾU EM CÒN TỒN TẠI",
                "Khi anh bắt đầu 1 tình yêu Là lúc anh tự thay", "Trịnh Đình Quang"));

        mSongs.add(new SongModel("60701", "NGỐC",
                "Có rất nhiều những câu chuyện Em dấu riêng mình em biết", "Khắc Việt"));

        mSongs.add(new SongModel("60650", "HÃY TIN ANH LẦN NỮA",
                "Dẫu cho ta đã sai khi ở bên nhau Có yêu thương", "Thiên Dũng"));

        mSongs.add(new SongModel("60610", "CHUỖI NGÀY VẮNG EM",
                "Từ khi em bước ra đi cõi lòng anh ngập tràn bao", "Duy Cường"));

        mSongs.add(new SongModel("69656", "KHI NGƯỜI MÌNH YÊU KHÓC",
                "Nước mắt em đang rơi trên những ngón tay Nước mắt em", "Phạm Mạnh Quỳnh"));

        mSongs.add(new SongModel("60685", "MƠ",
                "Anh đã gặp em anh mơ được ấm anh mơ được gần", "Trịnh Thăng Bình"));

        mSongs.add(new SongModel("60752", "TÌNH YÊU CHẮP VÁ",
                "Muốn đi xa nơi yêu thương mình từng có Để không nghe", "Mr. Siro"));

        mSongs.add(new SongModel("69608", "CHỜ NGÀY HƯA TAN",
                "1 ngày mưa và em khuất xa nơi anh bóng dáng cũ", "Trung Đức"));

        mSongs.add(new SongModel("60603", "CÂU HỎI EM CHƯA TRẢ LỜI",
                "Cần nơi em 1 lời giải thích thật lòng Đừng lặng im", "Yuki Huy Nam"));

        mSongs.add(new SongModel("60720", "QUA ĐI LẶNG LẼ",
                "Đôi khi đến với nhau yêu thương chẳng được lâu nhưng khi", "Phan Mạnh Quỳnh"));

        mSongs.add(new SongModel("60856", "QUÊN ANH LÀ ĐIỀU EM KHÔNG THỂ - REMIX",
                "Cần thêm bao lâu để em quên đi niềm đau Cần thêm", "Thiên Ngôn"));
    }
}