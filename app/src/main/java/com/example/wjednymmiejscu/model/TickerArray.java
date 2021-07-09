package com.example.wjednymmiejscu.model;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * status - status of response
 * items - list of market objects
 */
public class TickerArray {
    private String status;
    private HashMap<String, Ticker> items;
    @Nullable
    private String[] errors;

    public TickerArray(@Nullable String[] errors) {
        this.errors = errors;
    }

    public TickerArray() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public HashMap<String, Ticker> getItems() {
        return items;
    }

    public void setItems(HashMap<String, Ticker> items) {
        this.items = items;
    }

    @Nullable
    public String[] getErrors() {
        return errors;
    }

    public void setErrors(@Nullable String[] errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "{" +
                "status='" + status + '\'' +
                ", items=" + items +
                '}';
    }
}
