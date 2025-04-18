package com.example.retrofit.api;

import com.example.retrofit.dto.Category;
import com.example.retrofit.dto.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET("categories") // Endpoint của API
    Call<List<Category>> getCategories();

    @GET("productsByCategory") // Endpoint mới
    Call<List<Product>> getProductsByCategory(@Query("categoryName") String categoryName); // Trả về danh sách sản phẩm
}