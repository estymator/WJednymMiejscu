package com.example.wjednymmiejscu.model;

/**
 * Class with basic informations from exchange
 * max - transaction with highest rate
 * min - transaction with lowest rate
 * last -
 * bid - best buying rate
 * ask - best selling rate
 * vwap -weighted average from last 24h
 * average - weighted average from 3 last best selling bids
 * volume
 */
public class Ticker {
    private String currency1, currency2;
    private Double max, min, last, bid, ask, vwap, average, volume;


    public void setCurrency1(String currency1) {
        this.currency1 = currency1;
    }

    public void setCurrency2(String currency2) {
        this.currency2 = currency2;
    }

    public String getCurrency1() {
        return currency1;
    }

    public String getCurrency2() {
        return currency2;
    }

    public Double getMax() {
        return max;
    }

    public Double getMin() {
        return min;
    }

    public Double getLast() {
        return last;
    }

    public Double getBid() {
        return bid;
    }

    public Double getAsk() {
        return ask;
    }

    public Double getVwap() {
        return vwap;
    }

    public Double getAverage() {
        return average;
    }

    public Double getVolume() {
        return volume;
    }

    public Ticker(String currency1, String currency2, Double max, Double min, Double last, Double bid, Double ask, Double vwap, Double average, Double volume) {
        this.currency1 = currency1;
        this.currency2 = currency2;
        this.max = max;
        this.min = min;
        this.last = last;
        this.bid = bid;
        this.ask = ask;
        this.vwap = vwap;
        this.average = average;
        this.volume = volume;
    }
}
