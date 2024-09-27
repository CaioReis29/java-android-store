package com.example.androidstore.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidstore.Model.Product;
import com.example.androidstore.Repository.ProductRepository;

import java.util.List;

public class ProductViewModel extends ViewModel {
    private final LiveData<List<Product>> products;

    public  ProductViewModel() {
        ProductRepository repository = new ProductRepository();
        products = repository.getProducts();
    }

    public  LiveData<List<Product>> getProducts() {
        return  products;
    }
}
