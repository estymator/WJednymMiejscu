package com.example.wjednymmiejscu.model;

public class Transaction {
    private String id,ty; //id, transaction type
    private Double t,a,r; // timestamp, amount, rate

    public Transaction(){};

    public Transaction(String id, String ty, Double t, Double a, Double r) {
        this.id = id;
        this.ty = ty;
        this.t = t;
        this.a = a;
        this.r = r;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTy() {
        return ty;
    }

    public void setTy(String ty) {
        this.ty = ty;
    }

    public Double getT() {
        return t;
    }

    public void setT(Double t) {
        this.t = t;
    }

    public Double getA() {
        return a;
    }

    public void setA(Double a) {
        this.a = a;
    }

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }
}
