package com.example.wjednymmiejscu.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.wjednymmiejscu.R;
import com.example.wjednymmiejscu.adapters.CurrencyAdapter;
import com.example.wjednymmiejscu.model.TickerArray;

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
        currencyRecyclerView.setAdapter(mainViewModel.getCurrencyAdapter());
        mainViewModel.getCurrencyList().observe(this, new Observer<TickerArray>() {
            @Override
            public void onChanged(TickerArray tickerArray) {
                Log.v(TAG,"OnChanged"+tickerArray.getItems().size());
                mainViewModel.getCurrencyAdapterDataSource().clear();
                mainViewModel.getCurrencyAdapterDataSource().putAll(tickerArray.getItems());
                mainViewModel.getCurrencyAdapter().notifyDataSetChanged();
            }
        });

        mainViewModel.loadRates();
    }
}