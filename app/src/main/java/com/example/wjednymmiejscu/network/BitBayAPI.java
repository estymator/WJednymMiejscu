package com.example.wjednymmiejscu.network;

import com.example.wjednymmiejscu.model.LastTransactions;
import com.example.wjednymmiejscu.model.MarketStatsResponse;
import com.example.wjednymmiejscu.model.OrderBook;
import com.example.wjednymmiejscu.model.Ticker;
import com.example.wjednymmiejscu.model.TickerArray;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BitBayAPI {
    /**
     * get all markets stats in ticker
     */
    @GET("rest/trading/ticker")
    Call<TickerArray> getTickerData();

    @GET("rest/trading/orderbook-limited/{market}/10")
    Call<OrderBook> getOrderBook(
            @Path("market") String market
    );

    @GET("rest/trading/stats/{market}")
    Call<MarketStatsResponse> getMarketStats(
            @Path("market") String market
    );

    @GET("rest/trading/transactions/{market}")
    Call<LastTransactions> getLastTransactions(
            @Path("market") String market
    );
}
