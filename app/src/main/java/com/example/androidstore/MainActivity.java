package com.example.androidstore;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidstore.Adapter.ProductAdapter;
import com.example.androidstore.Model.Product;
import com.example.androidstore.viewmodel.ProductViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductAdapter(this);
        recyclerView.setAdapter(adapter);

        ProductViewModel productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        productViewModel.getProducts().observe(this, products -> {
            if(products != null) {
                adapter.setProductList(products);
            }
        });
    }
}