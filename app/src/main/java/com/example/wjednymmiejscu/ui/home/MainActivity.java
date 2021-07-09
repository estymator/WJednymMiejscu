package com.example.wjednymmiejscu.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wjednymmiejscu.R;
import com.example.wjednymmiejscu.adapters.CurrencyAdapter;
import com.example.wjednymmiejscu.model.TickerArray;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="MainActivityTAG";
    private MainViewModel mainViewModel;
    private RecyclerView currencyRecyclerView;
    private EditText amountEditText;
    private TextView calcResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainViewModel = new ViewModelProvider(this, new MainViewModelProvider()).get(MainViewModel.class);
        mainViewModel.instantiateCalculatorValues();
        currencyRecyclerView = findViewById(R.id.main_currency_recyclerView);
        currencyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        currencyRecyclerView.setAdapter(mainViewModel.getCurrencyAdapter());

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.main_firstCurrencies_fragmentView,MainCalculatorFragment.newInstance(1), null)
                    .commit();
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.main_secondCurrencies_fragmentView,MainCalculatorFragment.newInstance(2), null)
                    .commit();

        }

        amountEditText =(EditText) findViewById(R.id.main_amountToCalc_editText);
        amountEditText.setText("1");
        amountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    mainViewModel.setCalculatorAmount(Double.valueOf(editable.toString()));
                    mainViewModel.calculate();
                }catch(NumberFormatException e){

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        calcResultTextView = (TextView) findViewById(R.id.main_calcResult_textView);
        calcResultTextView.setText("Wybierz waluty");


        mainViewModel.getCurrencyList().observe(this, new Observer<TickerArray>() {
            @Override
            public void onChanged(TickerArray tickerArray) {
                mainViewModel.getCurrencyAdapterDataSource().clear();
                mainViewModel.getCurrencyAdapterDataSource().putAll(tickerArray.getItems());
                mainViewModel.getCurrencyAdapter().notifyDataSetChanged();

            }
        });
        mainViewModel.getCalculatorResult().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                String firstCurrency = mainViewModel.getCalculatorAmount()+" "+ mainViewModel.getCalculatorFirstRowCurr().getValue()+" to ";
                // String result = aDouble.toString().split("\\.")[0].concat(".").concat(aDouble.toString().split("\\.")[1].substring(0,2)); // Result normalization
                String secondCurrency = " "+mainViewModel.getCalculatorSecondRowCurr().getValue();
                calcResultTextView.setText(firstCurrency+aDouble+secondCurrency);
            }
        });

        mainViewModel.loadRates();

    }
}