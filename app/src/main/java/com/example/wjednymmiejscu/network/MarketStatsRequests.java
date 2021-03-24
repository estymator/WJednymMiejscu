package com.example.wjednymmiejscu.network;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.wjednymmiejscu.model.MarketStats;
import com.example.wjednymmiejscu.model.MarketStatsResponse;
import com.example.wjednymmiejscu.model.OrderBook;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarketStatsRequests {
    private static final String TAG = "MarketStatsReqTAG";
    private MutableLiveData<MarketStatsResponse> marketStatsResult;
    private BitBayAPI bitBayAPI;

    public void loadMarketStats(String market, MutableLiveData<MarketStatsResponse> marketStatsResult){
        bitBayAPI = RetrofitServiceGenerator.createService(BitBayAPI.class);
        this.marketStatsResult=marketStatsResult;
        try{
            Call<MarketStatsResponse> getMarketStats = bitBayAPI.getMarketStats(market);
            getMarketStats.enqueue(new Callback<MarketStatsResponse>(){
                @Override
                public void onResponse(Call<MarketStatsResponse> call, Response<MarketStatsResponse> response) {
                    if(response.isSuccessful()){
                        if(response.body()==null){
                            getMarketStatsFailure("Response body is null");
                            return;
                        }
                        if(response.body().getStatus().equals("Ok")) {
                            MarketStatsResponse result = new MarketStatsResponse();
                            try {
                                result = (MarketStatsResponse) response.body();
                            } catch (Throwable t) {
                                getMarketStatsFailure(t.getMessage());
                            }
                            getMarketStatsSuccess(result);
                        }else if(response.body().getStatus().equals("Fail")){
                            getMarketStatsFailure(response.body().getErrors());
                        }else
                        {
                            getMarketStatsFailure("Bład pobrania danych");
                        }
                    }else{
                        Gson gson = new Gson();
                        NetworkError errorBody = gson.fromJson(response.errorBody().charStream(), NetworkError.class);
                        String message = errorBody.getMessage();
                        Log.v(TAG,message);
                        getMarketStatsFailure(message);
                    }
                }
                @Override
                public void onFailure(Call<MarketStatsResponse> call, Throwable t) {
                    getMarketStatsFailure(t.getMessage());
                }
            });
        }catch (Exception e)
        {
            Log.v(TAG,e.getMessage());
            getMarketStatsFailure(e.getMessage());
        }
    }

    void getMarketStatsSuccess(MarketStatsResponse response)
    {
        this.marketStatsResult.setValue(response);
    }

    void getMarketStatsFailure(String messege){
        String[] mess = {messege};
        this.marketStatsResult.setValue(new MarketStatsResponse(mess));
    }
    void getMarketStatsFailure(String[] messege){
        this.marketStatsResult.setValue(new MarketStatsResponse(messege));
    }
}
