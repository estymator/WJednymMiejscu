package com.example.wjednymmiejscu.model;

import androidx.annotation.Nullable;

public class MarketStatsResponse {
    private String status;
    private MarketStats stats;
    private String code;

    @Nullable
    private String[] errors;

    public MarketStatsResponse() {

    }

    public MarketStatsResponse(String status, MarketStats stats) {
        this.status = status;
        this.stats = stats;
    }

    public MarketStatsResponse(@Nullable String[] errors) {
        this.errors = errors;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MarketStats getStats() {
        return stats;
    }

    public void setStats(MarketStats stats) {
        this.stats = stats;
    }

    @Nullable
    public String[] getErrors() {
        return errors;
    }

    public void setErrors(@Nullable String[] errors) {
        this.errors = errors;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
