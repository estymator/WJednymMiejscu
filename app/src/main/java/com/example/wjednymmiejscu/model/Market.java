package com.example.wjednymmiejscu.model;

import java.io.Serializable;

/**
 * code - code of market XXX-XXX
 * first,second - informations about currencies in given market
 */
public class Market implements Serializable {
    private String code;
    private CurrencyInfo first, second;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CurrencyInfo getFirst() {
        return first;
    }

    public void setFirst(CurrencyInfo first) {
        this.first = first;
    }

    public CurrencyInfo getSecond() {
        return second;
    }

    public void setSecond(CurrencyInfo second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "Market{" +
                "code='" + code + '\'' +
                ", first=" + first +
                ", second=" + second +
                '}';
    }
}
