package com.example.wjednymmiejscu.network;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.wjednymmiejscu.model.Ticker;
import com.example.wjednymmiejscu.model.TickerArray;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TickerRequests {
    private final static String TAG="TikcerRequestsTAG";
    private BitBayAPI bitBayAPI;
    private MutableLiveData<ArrayList<TickerArray>> tickerList;

    public void loadTicker(ArrayList<TickerArray> tickersList){
        this.tickerList=tickerList;
        bitBayAPI = RetrofitServiceGenerator.createService(BitBayAPI.class);
        try{
            Call<TickerArray> getCurrencyCall = bitBayAPI.getTickerData();
            getCurrencyCall.enqueue(new Callback<TickerArray>(){
                @Override
                public void onResponse(Call<TickerArray> call, Response<TickerArray> response) {
                    if(response.isSuccessful()){

                        if(response.body()!=null) {
                            TickerArray result= new TickerArray();
                            try{
                                result =(TickerArray) response.body();
                            }catch (Throwable t){
                                getTickerFailure(t.getMessage());
                            }
                            getTicketSuccess(result);
                        }else
                        {
                            getTickerFailure("Bład pobrania danych");
                        }
                    }else{
                        Log.v(TAG,"ResponseSuccessful not 200");
                        Gson gson = new Gson();
                        NetworkError errorBody = gson.fromJson(response.errorBody().charStream(), NetworkError.class);
                        String message = errorBody.getMessage();
                        Log.v(TAG,message);
                        getTickerFailure(message);
                    }
                }
                @Override
                public void onFailure(Call<TickerArray> call, Throwable t) {
                    Log.v(TAG,"onFailure");
                    getTickerFailure(t.getMessage());
                }
            });
        }catch (Exception e)
        {
            Log.v(TAG,e.getMessage());
        }
    }

    private void getTicketSuccess(TickerArray result){
        this.tickerList.getValue().add(result);
    }

    private void getTickerFailure(String mess){
        Log.v(TAG, mess);
    }
}
