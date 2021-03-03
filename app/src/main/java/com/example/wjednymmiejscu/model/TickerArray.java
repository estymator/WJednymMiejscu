package com.example.wjednymmiejscu.model;

import java.util.ArrayList;

/**
 * status - status of response
 * items - list of martket objects
 */
public class TickerArray {
    private String status;
    private ArrayList<Ticker> items;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Ticker> getItems() {
        return items;
    }

    public void setItems(ArrayList<Ticker> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "TickerArray{" +
                "status='" + status + '\'' +
                ", items=" + items +
                '}';
    }
}
