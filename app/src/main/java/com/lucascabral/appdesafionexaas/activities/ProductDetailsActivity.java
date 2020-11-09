package com.lucascabral.appdesafionexaas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lucascabral.appdesafionexaas.R;

public class ProductDetailsActivity extends AppCompatActivity {

    private ImageView imageViewPhotoDet;
    private TextView textViewNameDet;
    private TextView textViewPriceDet;
    private TextView textViewStockDet;
    private TextView textViewDescriptionDet;

    String baseImageUrl = "https://raw.githubusercontent.com/myfreecomm/desafio-mobile-android/master/";

    private String photo;
    private String name;
    private int stock;
    private double price;
    private String description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        toolbarConfig();
        initComponents();
        getIntents();
        componentsConfig();

    }

    public void toolbarConfig() {

        getSupportActionBar().setTitle("Product details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    public void componentsConfig() {

        Glide.with(getApplicationContext()).load(photo).into(imageViewPhotoDet);
        textViewNameDet.setText(name);
        setStock();
        setPrice();
        textViewDescriptionDet.setText(description);

    }

    public void getIntents() {

        photo = getIntent().getStringExtra("photo");
        name = getIntent().getStringExtra("name");
        description = getIntent().getStringExtra("description");

    }

    @SuppressLint("SetTextI18n")
    public void setPrice() {
        price = getIntent().getDoubleExtra("price", price);
        textViewPriceDet.setText("$" + price);
    }

    @SuppressLint("SetTextI18n")
    public void setStock() {

        stock = getIntent().getIntExtra("stock", stock);

        int stockProduto = stock;

        if (stock == 1) {
            textViewStockDet.setText("only 1 left in stock");
        }
        if (stockProduto > 1) {
            textViewStockDet.setText("in stock");
        } else {
            textViewStockDet.setText("out of stock");
        }

    }

    public void initComponents() {
        imageViewPhotoDet = findViewById(R.id.detailsPhotoImageView);
        textViewNameDet = findViewById(R.id.detailsNameTextView);
        textViewPriceDet = findViewById(R.id.detailsPriceTextView);
        textViewStockDet = findViewById(R.id.detailsStockTextView);
        textViewDescriptionDet = findViewById(R.id.detailsDescriptionTextView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detalhes, menu);
        // SearchView mSearchView = (SearchView) menu.findItem(R.id.me).getActionView();
        return super.onCreateOptionsMenu(menu);
    }
}