package com.example.wjednymmiejscu.model;

public class Offer {
    private Double ra,ca,sa,pa,co;

    public Offer() {
    }

    public Offer(Double ra, Double ca, Double sa, Double pa, Double co) {
        this.ra = ra;
        this.ca = ca;
        this.sa = sa;
        this.pa = pa;
        this.co = co;
    }

    public Double getRa() {
        return ra;
    }

    public void setRa(Double ra) {
        this.ra = ra;
    }

    public Double getCa() {
        return ca;
    }

    public void setCa(Double ca) {
        this.ca = ca;
    }

    public Double getSa() {
        return sa;
    }

    public void setSa(Double sa) {
        this.sa = sa;
    }

    public Double getPa() {
        return pa;
    }

    public void setPa(Double pa) {
        this.pa = pa;
    }

    public Double getCo() {
        return co;
    }

    public void setCo(Double co) {
        this.co = co;
    }
}
