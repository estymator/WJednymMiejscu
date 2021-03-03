package com.example.wjednymmiejscu.model;


/**
 * time - unix representation of time
 * highestBid - currently best buy offer
 * lowestAsk - currently best sell offer
 * rate - rate of last transaction
 * previousRate - rate of penultimate transaction
 */
public class Ticker {
    private long time;
    Double highestBid, lowestASK, rate, previousRate;
    private Market market;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Double getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(Double highestBid) {
        this.highestBid = highestBid;
    }

    public Double getLowestASK() {
        return lowestASK;
    }

    public void setLowestASK(Double lowestASK) {
        this.lowestASK = lowestASK;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getPreviousRate() {
        return previousRate;
    }

    public void setPreviousRate(Double previousRate) {
        this.previousRate = previousRate;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    @Override
    public String toString() {
        return "Ticker{" +
                "time=" + time +
                ", highestBid=" + highestBid +
                ", lowestASK=" + lowestASK +
                ", rate=" + rate +
                ", previousRate=" + previousRate +
                ", market=" + market +
                '}';
    }
}
