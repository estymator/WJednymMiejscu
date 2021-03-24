package com.example.wjednymmiejscu.model;

import androidx.annotation.Nullable;

public class OrderBook {
    private String status;
    private Offer[] sell, buy;
    private Long timestamp, seqNuo;

    @Nullable
    private String[] errors;

    public OrderBook() {
    }

    public OrderBook(String status, Offer[] sell, Offer[] buy, Long timestamp, Long seqNuo) {
        this.status=status;
        this.sell = sell;
        this.buy = buy;
        this.timestamp = timestamp;
        this.seqNuo = seqNuo;
    }

    public OrderBook(@Nullable String[] error) {
        this.errors = errors;

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Offer[] getSell() {
        return sell;
    }

    public void setSell(Offer[] sell) {
        this.sell = sell;
    }

    public Offer[] getBuy() {
        return buy;
    }

    public void setBuy(Offer[] buy) {
        this.buy = buy;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getSeqNuo() {
        return seqNuo;
    }

    public void setSeqNuo(Long seqNuo) {
        this.seqNuo = seqNuo;
    }

    @Nullable
    public String[] getErrors() {
        return errors;
    }

    public void setErrors(@Nullable String[] error) {
        this.errors = errors;
    }
}
