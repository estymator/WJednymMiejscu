package com.example.wjednymmiejscu.model;

import java.io.Serializable;

/**
 * currency - currency code XXX
 * minOffer - lowest amount available to operate
 * scale - rate precision
 */
public class CurrencyInfo implements Serializable {
    private String currency;
    private Double minOffer;
    private int scale;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getMinOffer() {
        return minOffer;
    }

    public void setMinOffer(Double minOffer) {
        this.minOffer = minOffer;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    @Override
    public String toString() {
        return "CurrencyInfo{" +
                "currency='" + currency + '\'' +
                ", minOffer=" + minOffer +
                ", scale=" + scale +
                '}';
    }
}
