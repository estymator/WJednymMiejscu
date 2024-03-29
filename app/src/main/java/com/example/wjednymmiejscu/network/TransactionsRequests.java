package com.example.wjednymmiejscu.network;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.wjednymmiejscu.model.LastTransactions;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionsRequests {
    private static final String TAG = "lastTransactionRequest";
    MutableLiveData<LastTransactions> lastTransactionResult;
    private BitBayAPI bitBayAPI;

    public void loadLastTransaction(String market, MutableLiveData<LastTransactions> lastTransactionResult){
        bitBayAPI = RetrofitServiceGenerator.createService(BitBayAPI.class);
        this.lastTransactionResult=lastTransactionResult;
        try{
            Call<LastTransactions> getLastTransactions = bitBayAPI.getLastTransactions(market);
            getLastTransactions.enqueue(new Callback<LastTransactions>(){
                @Override
                public void onResponse(Call<LastTransactions> call, Response<LastTransactions> response) {
                    if(response.isSuccessful()){
                        if(response.body()==null){
                            getLastTransactionsFailure("Response body is null");
                            return;
                        }
                        if(response.body().getStatus().equals("Ok")) {
                            LastTransactions result = new LastTransactions();
                            try {
                                result = (LastTransactions) response.body();
                                result.setCode(market);
                            } catch (Throwable t) {
                                getLastTransactionsFailure(t.getMessage());
                            }
                            getLastTransactionsSuccess(result);
                        }else if(response.body().getStatus().equals("Fail")){
                            getLastTransactionsFailure(response.body().getErrors());
                        }else
                        {
                            getLastTransactionsFailure("Bład pobrania danych");
                        }
                    }else{
                        Gson gson = new Gson();
                        BitBayNetworkError errorBody = gson.fromJson(response.errorBody().charStream(), BitBayNetworkError.class);
                        String errors =  String.join(",",errorBody.getErrors());;
                        Log.v(TAG,errors);
                        getLastTransactionsFailure(errors);
                    }
                }
                @Override
                public void onFailure(Call<LastTransactions> call, Throwable t) {
                    getLastTransactionsFailure(t.getMessage());
                }
            });
        }catch (Exception e)
        {
            Log.v(TAG,e.getMessage());
            getLastTransactionsFailure(e.getMessage());
        }
    }

    void getLastTransactionsSuccess(LastTransactions lastTransactions)
    {
        this.lastTransactionResult.setValue(lastTransactions);
    }

    void getLastTransactionsFailure(String messege){
        String[] mess = {messege};
        this.lastTransactionResult.setValue(new LastTransactions(mess));
    }
    void getLastTransactionsFailure(String[] messege){
        this.lastTransactionResult.setValue(new LastTransactions(messege));
    }
}
