package com.example.androidstore.Network;

import com.example.androidstore.Model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductsApiService {
    @GET("products")
    Call<List<Product>> getProducts();
}
