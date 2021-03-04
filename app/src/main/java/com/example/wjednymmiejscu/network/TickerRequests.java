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
    private final static String TAG="TickerRequestsTAG";
    private BitBayAPI bitBayAPI;
    private MutableLiveData<TickerArray> tickerList;

    public void loadTicker(MutableLiveData<TickerArray> tickersList){
        this.tickerList=tickersList;
        bitBayAPI = RetrofitServiceGenerator.createService(BitBayAPI.class);
        try{
            Call<TickerArray> getCurrencyCall = bitBayAPI.getTickerData();
            getCurrencyCall.enqueue(new Callback<TickerArray>(){
                @Override
                public void onResponse(Call<TickerArray> call, Response<TickerArray> response) {
                    if(response.isSuccessful()){

                        if(response.body()!=null&&response.body().getStatus().equals("Ok")) {
                            TickerArray result = new TickerArray();
                            try {
                                result = (TickerArray) response.body();
                            } catch (Throwable t) {
                                getTicketFailure(t.getMessage());
                            }
                            getTicketSuccess(result);
                        }else if(response.body()!=null&&response.body().getStatus().equals("Fail")){
                            getTicketFailure(response.body().getErrors());
                        }else
                        {
                            getTicketFailure("Bład pobrania danych");
                        }
                    }else{
                        Log.v(TAG,"ResponseSuccessful not 200");
                        Gson gson = new Gson();
                        NetworkError errorBody = gson.fromJson(response.errorBody().charStream(), NetworkError.class);
                        String message = errorBody.getMessage();
                        Log.v(TAG,message);
                        getTicketFailure(message);
                    }
                }
                @Override
                public void onFailure(Call<TickerArray> call, Throwable t) {
                    Log.v(TAG,"onFailure");
                    getTicketFailure(t.getMessage());
                }
            });
        }catch (Exception e)
        {
            Log.v(TAG,e.getMessage());
        }
    }

    private void getTicketSuccess(TickerArray result){
        this.tickerList.setValue(result);
    }

    private void getTicketFailure(String mess){
        if(mess!=null){
            Log.v(TAG, mess);
        }
    }
}
