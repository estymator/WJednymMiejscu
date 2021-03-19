package com.example.wjednymmiejscu.adapters;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wjednymmiejscu.R;
import com.example.wjednymmiejscu.model.Ticker;
import com.example.wjednymmiejscu.ui.currency.CurrencyActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.ViewHolder> {
    private static final String TAG = "CurrencyAdapterTAG";
    public HashMap<String, Ticker> dataSet;
    public String[] markets;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView codeTextView, priceTextView;
        private final ImageView iconImageView;
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

        public ImageView getIconImageView() {
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
        if(dataSet.size()>0){

            String marketCode = markets[position];
            Ticker bufor = dataSet.get(marketCode);
            holder.getCodeTextView().setText(bufor.getMarket().getCode().split("-")[0]);
            holder.getPriceTextView().setText(bufor.getRate().toString());
            String code = bufor.getMarket().getCode().split("-")[0].toLowerCase();
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), CurrencyActivity.class);
                    intent.putExtra("currency", bufor);
                    view.getContext().startActivity(intent);
                }
            });
            try {
                int id = R.drawable.class.getField(code).getInt(null);
                holder.getIconImageView().setImageResource(id);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getItemCount() {
        return markets.length;
    }

}
