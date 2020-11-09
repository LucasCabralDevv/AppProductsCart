package com.lucascabral.appdesafionexaas.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lucascabral.appdesafionexaas.R;
import com.lucascabral.appdesafionexaas.adapter.AdapterProducts;
import com.lucascabral.appdesafionexaas.api.RetrofitClient;
import com.lucascabral.appdesafionexaas.model.ProductResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsActivity extends AppCompatActivity {

    private RecyclerView recyclerViewProducts;
    private final AdapterProducts adapterProducts = new AdapterProducts();

    TextView productsCountTextView;
    TextView productsTotalTextView;
    TextView productsSubTotalTextView;
    TextView productsShippingTextView;
    TextView productsTaxTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);


        toolbarConfig();
        initComponents();
        recyclerViewConfig();
        productRequest();
    }

    private void productRequest() {
        Call<List<ProductResponse>> call = RetrofitClient.getInstance()
                .getApi().recuperarDados();

        call.enqueue(new Callback<List<ProductResponse>>() {
            @Override
            public void onResponse(Call<List<ProductResponse>> call, Response<List<ProductResponse>> response) {

                if (response.isSuccessful()) {

                    final List<ProductResponse> listProducts = response.body();
                    adapterProducts.setListProducts(listProducts);
                    recyclerViewProducts.setAdapter(adapterProducts);

                    assert listProducts != null;
                    summationConfig(listProducts);

                    setItemsCount(listProducts);
                }
            }

            @Override
            public void onFailure(Call<List<ProductResponse>> call, Throwable t) {

            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void setItemsCount(List<ProductResponse> listProducts) {
        int i = listProducts.size();
        Log.d("iValue", String.valueOf(i));
        productsCountTextView.setText(i + " Items in your cart");
    }

    @SuppressLint("SetTextI18n")
    public void summationConfig(List<ProductResponse> listProdutos) {

        //Soma os preços e configura componente o Subtotal
        Double subTotal = 0.0;
        for (ProductResponse produtoPrice : listProdutos) {
            subTotal += produtoPrice.getPrice();
        }
        productsSubTotalTextView.setText("$" + subTotal);

        //Soma o frete e configura componente o Shipping
        Double shipping = 0.0;
        for (ProductResponse produtoShipping : listProdutos) {
            shipping += produtoShipping.getShipping();
        }
        productsShippingTextView.setText("$" + shipping);

        //Soma os impostos e configura componente o Tax
        Double tax = 0.0;
        for (ProductResponse produtoTax : listProdutos) {
            tax += produtoTax.getTax();
        }
        productsTaxTextView.setText("$" + tax);

        //Encontra o Total e configura componente o Total
        double total = subTotal + shipping + tax;
        productsTotalTextView.setText("$" + total);
    }

    public void recyclerViewConfig() {

        RecyclerView.LayoutManager layoutManagerProdutos = new LinearLayoutManager(getApplicationContext());
        recyclerViewProducts.setLayoutManager(layoutManagerProdutos);
        recyclerViewProducts.setHasFixedSize(true);
        recyclerViewProducts.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
    }

    public void initComponents() {

        recyclerViewProducts = findViewById(R.id.recyclerViewProdutos);
        productsCountTextView = findViewById(R.id.productsCountTextView);
        productsTotalTextView = findViewById(R.id.productsTotalTextView);
        productsSubTotalTextView = findViewById(R.id.productsSubTotalTextView);
        productsShippingTextView = findViewById(R.id.productsShippingTextView);
        productsTaxTextView = findViewById(R.id.productsTaxTextView);
    }

    private void toolbarConfig() {
        getSupportActionBar().setTitle("Cart");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);

        return super.onCreateOptionsMenu(menu);
    }
}