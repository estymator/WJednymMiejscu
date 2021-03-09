package com.example.wjednymmiejscu.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wjednymmiejscu.R;
import com.example.wjednymmiejscu.model.Ticker;

import java.util.ArrayList;
import java.util.HashMap;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.ViewHolder> {
    private static final String TAG = "CurrencyAdapterTAG";
    public HashMap<String, Ticker> dataSet;
    public String[] markets;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView codeTextView, priceTextView;
        private final AppCompatImageView iconImageView;
        public ViewHolder(View view){
            super(view);
            codeTextView = view.findViewById(R.id.currencyItem_code);
            priceTextView = view.findViewById(R.id.currencyItem_price);
            iconImageView = view.findViewById(R.id.currencyItem_icon);
        }

        public TextView getCodeTextView() {
            return codeTextView;
        }

        public TextView getPriceTextView() {
            return priceTextView;
        }

        public AppCompatImageView getIconImageView() {
            return iconImageView;
        }
    }

    public CurrencyAdapter(HashMap<String, Ticker> dataSet, String[] markets){
        this.dataSet=dataSet;
        this.markets=markets;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.currency_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.v(TAG,"Zapisuje"+dataSet.size()+" "+position);
        if(dataSet.size()>0){
            String marketCode = markets[position];
            Ticker bufor = dataSet.get(marketCode);
            Log.v(TAG,"Zapisuje"+bufor.getRate());
            holder.getCodeTextView().setText(bufor.getMarket().getCode());
            holder.getPriceTextView().setText(bufor.getRate().toString());
        }
    }

    @Override
    public int getItemCount() {
        return markets.length;
    }

    public void setDataSet(HashMap<String, Ticker> dataSet) {
        this.dataSet = dataSet;
    }
}
