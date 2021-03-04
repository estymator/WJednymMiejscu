package com.example.wjednymmiejscu.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * status - status of response
 * items - list of martket objects
 */
public class TickerArray {
    private String status;
    private HashMap<String, Ticker> items;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "{" +
                "status='" + status + '\'' +
                ", items=" + items +
                '}';
    }
}
