package com.example.wjednymmiejscu.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.wjednymmiejscu.R;
import com.example.wjednymmiejscu.adapters.CurrencyAdapter;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="MainActivityTAG";
    private MainViewModel mainViewModel;
    private RecyclerView currencyRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainViewModel = new ViewModelProvider(this, new MainViewModelProvider()).get(MainViewModel.class);

        currencyRecyclerView = findViewById(R.id.main_currency_recyclerView);
        currencyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        currencyRecyclerView.setAdapter(new CurrencyAdapter(mainViewModel.getCurrencyList().getValue()));
    }
}