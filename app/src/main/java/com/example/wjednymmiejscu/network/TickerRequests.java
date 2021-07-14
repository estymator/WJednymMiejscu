package com.example.wjednymmiejscu.network;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.wjednymmiejscu.model.TickerArray;
import com.google.gson.Gson;

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
                        if(response.body()==null){
                            getTickerFailure("response body is null");
                            return;
                        }
                        if(response.body().getStatus().equals("Ok")) {
                            TickerArray result = new TickerArray();
                            try {
                                result = (TickerArray) response.body();
                            } catch (Throwable t) {
                                getTickerFailure(t.getMessage());
                            }
                            getTickerSuccess(result);
                        }else if(response.body().getStatus().equals("Fail")){
                            getTickerFailure(response.body().getErrors());
                        }else
                        {
                            getTickerFailure("Bład pobrania danych");
                        }
                    }else{
                        Gson gson = new Gson();
                        BitBayNetworkError errorBody = gson.fromJson(response.errorBody().charStream(), BitBayNetworkError.class);
                        String errors =  String.join(",",errorBody.getErrors());;
                        Log.v(TAG,errors);
                        getTickerFailure(errors);
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
            getTickerFailure(e.getMessage());
        }
    }

    private void getTickerSuccess(TickerArray result){
        this.tickerList.setValue(result);
    }

    private void getTickerFailure(String mess){
        String[] messages = {mess};
        tickerList.setValue(new TickerArray(messages));
    }
    private void getTickerFailure(String[] mess){
        tickerList.setValue(new TickerArray(mess));
    }
}
