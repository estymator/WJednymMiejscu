package com.example.wjednymmiejscu.network;

public class BitBayNetworkError {
    private String status;
    private String [] errors;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String[] getErrors() {
        return errors;
    }

    public void setErrors(String[] errors) {
        this.errors = errors;
    }
}
