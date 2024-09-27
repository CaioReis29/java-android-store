package com.example.androidstore.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidstore.Model.Product;
import com.example.androidstore.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_PRODUCT = 1;
    private static final int VIEW_TYPE_SHIMMER = 2;

    private final Context context;
    private List<Product> productList;
    private boolean isLoading = true;

    public ProductAdapter(Context context) {
        this.context = context;
        this.productList = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_PRODUCT) {
            View view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
            return new ProductViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.shimmer_item, parent, false);
            return new ShimmerViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_PRODUCT) {
            Product product = productList.get(position);
            ProductViewHolder productHolder = (ProductViewHolder) holder;
            productHolder.title.setText(product.getTitle());
            String formattedPrice = String.format(new Locale("pt", "BR"), "R$ %.2f", product.getPrice());
            productHolder.price.setText(formattedPrice);
            Glide.with(context).load(product.getImage()).into(productHolder.image);
        } else {
            ShimmerViewHolder shimmerHolder = (ShimmerViewHolder) holder;
            shimmerHolder.shimmerLayout.startShimmer();
        }
    }

    @Override
    public int getItemCount() {
        return isLoading ? 10 : productList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return isLoading ? VIEW_TYPE_SHIMMER : VIEW_TYPE_PRODUCT;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setProductList(List<Product> products) {
        this.productList = products;
        this.isLoading = false;
        notifyDataSetChanged();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView title, price;
        ImageView image;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTextView);
            price = itemView.findViewById(R.id.priceTextView);
            image = itemView.findViewById(R.id.productImageView);
        }
    }

    public static class ShimmerViewHolder extends RecyclerView.ViewHolder {
        ShimmerFrameLayout shimmerLayout;

        public ShimmerViewHolder(@NonNull View itemView) {
            super(itemView);
            shimmerLayout = itemView.findViewById(R.id.shimmerLayout);
        }
    }
}
