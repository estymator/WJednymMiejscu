package com.example.wjednymmiejscu.ui.currency;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wjednymmiejscu.R;
import com.example.wjednymmiejscu.model.MarketStatsResponse;
import com.example.wjednymmiejscu.model.Ticker;

public class CurrencyActivity extends AppCompatActivity {
    private final String TAG = "CurrencyActivityTAG";
    private CurrencyViewModel currencyViewModel;
    private ImageView currencyIconImageView;
    private TextView nameTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        currencyViewModel = new ViewModelProvider(this, new CurrencyViewModelFactory()).get(CurrencyViewModel.class);
        if(currencyViewModel.getTicker()==null){
            Ticker ticker = (Ticker) getIntent().getSerializableExtra("currency");
            currencyViewModel.setTicker(ticker);
        }
        currencyViewModel.loadMarketData();
        currencyIconImageView = (ImageView) findViewById(R.id.currency_icon_imageView);

        nameTextView = (TextView) findViewById(R.id.currency_name_textView);
        nameTextView.setText(currencyViewModel.getTicker().getMarket().getCode());

        currencyViewModel.getMarketStatsResult().observe(this, new Observer<MarketStatsResponse>() {
            @Override
            public void onChanged(MarketStatsResponse response) {
                String currencyCode=currencyViewModel.getTicker().getMarket().getCode().split("-")[0].toLowerCase();
                int imageId=getResources().getIdentifier(currencyCode,"drawable", getPackageName());
                currencyIconImageView.setImageResource(imageId);
            }
        });

    }




}