package com.lucascabral.appdesafionexaas.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lucascabral.appdesafionexaas.R;
import com.lucascabral.appdesafionexaas.activities.ProductDetailsActivity;
import com.lucascabral.appdesafionexaas.model.ProductResponse;

import java.util.List;

public class AdapterProducts extends RecyclerView.Adapter<AdapterProducts.ViewHolderProducts> {

    private List<ProductResponse> listProducts;
    private Context context;

    public AdapterProducts() {
    }

    public void setListProducts(List<ProductResponse> listProducts) {
        this.listProducts = listProducts;
    }

    @NonNull
    @Override
    public ViewHolderProducts onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemProduto = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_products, parent, false);
        return new ViewHolderProducts(itemProduto);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolderProducts holder, int position) {

        final ProductResponse productResponse = listProducts.get(position);

        Glide.with(holder.itemView.getContext()).load(productResponse.getImageUrl()).into(holder.imageViewProduct);
        holder.textViewName.setText(productResponse.getName());
        holder.textViewPrice.setText("$" + productResponse.getPrice().toString());

        setStock(holder, productResponse);

        // Evento de click para RecyclerView
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(),
                        ProductDetailsActivity.class);
                intent.putExtra("photo", productResponse.getImageUrl());
                intent.putExtra("name", productResponse.getName());
                intent.putExtra("stock", productResponse.getStock());
                intent.putExtra("price", productResponse.getPrice());
                intent.putExtra("description", productResponse.getDescription());

                holder.itemView.getContext().startActivity(intent);

            }
        });


    }

    @SuppressLint("SetTextI18n")
    public void setStock(@NonNull ViewHolderProducts holder, ProductResponse productResponse) {
        int stockProduct = productResponse.getStock();

        if (stockProduct == 1) {
            holder.textViewStock.setText("only 1 left in stock");
        }
        if (stockProduct > 1) {
            holder.textViewStock.setText("in stock");
        } else {
            holder.textViewStock.setText("out of stock");
        }
    }

    @Override
    public int getItemCount() {
        return listProducts.size();
    }

    public class ViewHolderProducts extends RecyclerView.ViewHolder {

        ImageView imageViewProduct;
        TextView textViewName;
        TextView textViewStock;
        TextView textViewPrice;


        public ViewHolderProducts(@NonNull View itemView) {
            super(itemView);

            imageViewProduct = itemView.findViewById(R.id.adapterPhotoImageView);
            textViewName = itemView.findViewById(R.id.detailsNameTextView);
            textViewPrice = itemView.findViewById(R.id.detailsPriceTextView);
            textViewStock = itemView.findViewById(R.id.detailsStockTextView);

        }
    }

}
