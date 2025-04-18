package com.example.viewflipper_cricleindicator;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import me.relex.circleindicator.CircleIndicator;

import com.example.viewflipper_cricleindicator.adapter.ImageSliderAdapter;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private CircleIndicator dotsIndicator;
    private ImageSliderAdapter adapter;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        dotsIndicator = findViewById(R.id.dotsIndicator);

        apiService = RetrofitClient.getClient().create(ApiService.class);
        loadImages();
    }

    private void loadImages() {
        apiService.loadImageSlider(1).enqueue(new Callback<MessageModel>() {
            @Override
            public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ImagesSlider> imagesList = response.body().getResult();
                    adapter = new ImageSliderAdapter(MainActivity.this, imagesList);
                    viewPager.setAdapter(adapter);
                    dotsIndicator.setViewPager(viewPager);
                }
            }

            @Override
            public void onFailure(Call<MessageModel> call, Throwable t) {
                Log.e("API Error", "Lỗi kết nối API: " + t.getMessage());
            }
        });
    }
}

