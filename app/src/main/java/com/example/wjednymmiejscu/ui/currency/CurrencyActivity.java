package com.example.wjednymmiejscu.ui.currency;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;

import com.example.wjednymmiejscu.R;
import com.example.wjednymmiejscu.model.Ticker;

public class CurrencyActivity extends AppCompatActivity {
    private final String TAG = "CurrencyActivityTAG";
    private CurrencyViewModel currencyViewModel;
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
        nameTextView = findViewById(R.id.currency_name_textView);
        nameTextView.setText(currencyViewModel.getTicker().getMarket().getFirst().getCurrency());
    }
}