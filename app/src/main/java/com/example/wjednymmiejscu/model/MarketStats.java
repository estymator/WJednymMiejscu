package com.example.wjednymmiejscu.model;

public class MarketStats {
    private String m;
    private Double h,l,v,r24h;

    public MarketStats() {
    }

    public MarketStats(String m, Double h, Double l, Double v, Double r24h) {
        this.m = m;
        this.h = h;
        this.l = l;
        this.v = v;
        this.r24h = r24h;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public Double getH() {
        return h;
    }

    public void setH(Double h) {
        this.h = h;
    }

    public Double getL() {
        return l;
    }

    public void setL(Double l) {
        this.l = l;
    }

    public Double getV() {
        return v;
    }

    public void setV(Double v) {
        this.v = v;
    }

    public Double getR24h() {
        return r24h;
    }

    public void setR24h(Double r24h) {
        this.r24h = r24h;
    }
}
