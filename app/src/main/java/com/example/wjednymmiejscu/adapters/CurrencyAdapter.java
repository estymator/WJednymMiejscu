package com.example.wjednymmiejscu.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
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
        private final TextView codeTextView, priceTextView, rateChangeTextView;
        private final ImageView iconImageView;
        private final ConstraintLayout adapterLayout;
        public ViewHolder(View view){
            super(view);
            adapterLayout=view.findViewById(R.id.currencyItem_constraintLayout);
            codeTextView = view.findViewById(R.id.currencyItem_code);
            priceTextView = view.findViewById(R.id.currencyItem_price);
            iconImageView = view.findViewById(R.id.currencyItem_icon);
            rateChangeTextView = view.findViewById(R.id.currencyItem_rateChange_textView);
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

        public TextView getRateChangeTextView() {
            return rateChangeTextView;
        }

        public ConstraintLayout getAdapterLayout() {
            return adapterLayout;
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

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(dataSet.size()>0){

            String marketCode = markets[position];
            Ticker bufor = dataSet.get(marketCode);
            double rateChange =0;
            try{
                rateChange=((bufor.getRate()-bufor.getPreviousRate())/bufor.getRate())*100;


            }catch (Exception e){
                Log.e(TAG, "rateChange calc error "+ e.getMessage());
            }
            try{
                if(rateChange>=0){
                    holder.getRateChangeTextView().setBackgroundColor(Color.parseColor("#3ecc25"));
                }else{
                    holder.getRateChangeTextView().setBackgroundColor(Color.parseColor("#c92926"));
                }
            }catch(Exception e){
                Log.e(TAG,"change background error "+e.getMessage());
            }

            holder.getCodeTextView().setText(bufor.getMarket().getCode().split("-")[0]);
            holder.getPriceTextView().setText(bufor.getRate().toString());
            holder.getRateChangeTextView().setText(String.format("%04.2f ",rateChange));
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
