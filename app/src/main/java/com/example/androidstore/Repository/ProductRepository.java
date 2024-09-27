package com.example.androidstore.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidstore.Model.Product;
import com.example.androidstore.Network.ProductsApiService;
import com.example.androidstore.Network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductRepository {
    private final ProductsApiService apiService;

    public  ProductRepository() {
        Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        apiService = retrofit.create(ProductsApiService.class);
    }

    public LiveData<List<Product>> getProducts() {
        MutableLiveData<List<Product>> productData = new MutableLiveData<>();

        apiService.getProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, @NonNull Response<List<Product>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    productData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, @NonNull Throwable t) {
                productData.setValue(null);
            }
        });
        return  productData;
    }
}
