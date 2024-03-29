package com.example.wjednymmiejscu.network;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.wjednymmiejscu.model.OrderBook;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderBookRequests {
    private static final String TAG = "OrderBookRequest";
    MutableLiveData<OrderBook> orderBookResult;
    private BitBayAPI bitBayAPI;

    public void loadOrderBook(String market, MutableLiveData<OrderBook> orderBookResult){
        bitBayAPI = RetrofitServiceGenerator.createService(BitBayAPI.class);
        this.orderBookResult=orderBookResult;
        try{
            Call<OrderBook> getOrderBook = bitBayAPI.getOrderBook(market);
            getOrderBook.enqueue(new Callback<OrderBook>(){
                @Override
                public void onResponse(Call<OrderBook> call, Response<OrderBook> response) {
                    if(response.isSuccessful()){
                        if(response.body()==null){
                            getOrderBookFailure("Response body is null");
                            return;
                        }
                        if(response.body().getStatus().equals("Ok")) {
                            OrderBook result = new OrderBook();
                            try {
                                result = (OrderBook) response.body();
                                result.setCode(market);
                            } catch (Throwable t) {
                                getOrderBookFailure(t.getMessage());
                            }
                            getOrderBookSuccess(result);
                        }else if(response.body().getStatus().equals("Fail")){
                            getOrderBookFailure(response.body().getErrors());
                        }else
                        {
                            getOrderBookFailure("Bład pobrania danych");
                        }
                    }else{
                        Gson gson = new Gson();
                        BitBayNetworkError errorBody = gson.fromJson(response.errorBody().charStream(), BitBayNetworkError.class);
                        String errors =  String.join(",",errorBody.getErrors());
                        Log.v(TAG,errors);
                        getOrderBookFailure(errors);
                    }
                }
                @Override
                public void onFailure(Call<OrderBook> call, Throwable t) {
                    getOrderBookFailure(t.getMessage());
                }
            });
        }catch (Exception e)
        {
            Log.v(TAG,e.getMessage());
            getOrderBookFailure(e.getMessage());
        }
    }

    void getOrderBookSuccess(OrderBook orderBook)
    {
        this.orderBookResult.setValue(orderBook);
    }

    void getOrderBookFailure(String messege){
        String[] mess = {messege};
        this.orderBookResult.setValue(new OrderBook(mess));
    }
    void getOrderBookFailure(String[] messege){
        this.orderBookResult.setValue(new OrderBook(messege));
    }
}
