package com.example.wjednymmiejscu.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wjednymmiejscu.R;

import java.util.ArrayList;

public class CalculatorAdapter extends RecyclerView.Adapter<CalculatorAdapter.ViewHolder> {
    private static final String TAG = "CalculatorAdapterTAG";
    private String[] dataSet;
    private MutableLiveData<String> currency;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final ImageView imageView;
        private final TextView textView;
        public ViewHolder(View view){
            super(view);
            textView = view.findViewById(R.id.calculatorItem_textView);
            imageView = view.findViewById(R.id.calculatorItem_ImageView);
        }

        public ImageView getImageView() {
            return imageView;
        }

        public TextView getTextView() {
            return textView;
        }
    }

    public CalculatorAdapter(String[] dataSet, MutableLiveData<String> currency){
        this.dataSet=dataSet;
        this.currency=currency;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.calculator_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView = view.findViewById(R.id.calculatorItem_textView);
                currency.setValue(textView.getText().toString());
            }
        });
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return dataSet.length;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String code = dataSet[position];
        holder.getTextView().setText(code);
        try {
            holder.getImageView().setImageResource(R.drawable.class.getField(code.toLowerCase()).getInt(null));//TODO what if not find image
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
