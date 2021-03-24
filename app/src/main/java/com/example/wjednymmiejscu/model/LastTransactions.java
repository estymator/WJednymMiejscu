package com.example.wjednymmiejscu.model;

import androidx.annotation.Nullable;

import java.util.HashMap;

public class LastTransactions {
    private String code, status;
    private HashMap<Integer, Transaction> transactionHashMap;

    @Nullable
    private String[] errors;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public HashMap<Integer, Transaction> getTransactionHashMap() {
        return transactionHashMap;
    }

    public void setTransactionHashMap(HashMap<Integer, Transaction> transactionHashMap) {
        this.transactionHashMap = transactionHashMap;
    }

    public LastTransactions(@Nullable String[] errors) {
        this.errors = errors;
    }

    @Nullable
    public String[] getErrors() {
        return errors;
    }

    public void setErrors(@Nullable String[] errors) {
        this.errors = errors;
    }

    public LastTransactions() {
    }
}
