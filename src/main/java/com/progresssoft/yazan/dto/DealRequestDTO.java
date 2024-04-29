package com.progresssoft.yazan.dto;

import com.progresssoft.yazan.validation.CurrencyCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public class DealRequestDTO {
    @NotBlank
    private String dealUniqueId;

    @NotBlank(message = "From currency ISO code is required")
    @CurrencyCode
    private String fromCurrencyIsoCode;

    @NotBlank(message = "To currency ISO code is required")
    @CurrencyCode
    private String toCurrencyIsoCode;

    @NotNull(message = "Deal timestamp is required")
    private Timestamp dealTimestamp;

    @NotNull(message = "Deal amount is required")
    private Double dealAmount;

    public String getDealUniqueId() {
        return dealUniqueId;
    }

    public void setDealUniqueId(String dealUniqueId) {
        this.dealUniqueId = dealUniqueId;
    }

    public String getFromCurrencyIsoCode() {
        return fromCurrencyIsoCode;
    }

    public void setFromCurrencyIsoCode(String fromCurrencyIsoCode) {
        this.fromCurrencyIsoCode = fromCurrencyIsoCode;
    }

    public String getToCurrencyIsoCode() {
        return toCurrencyIsoCode;
    }

    public void setToCurrencyIsoCode(String toCurrencyIsoCode) {
        this.toCurrencyIsoCode = toCurrencyIsoCode;
    }

    public Timestamp getDealTimestamp() {
        return dealTimestamp;
    }

    public void setDealTimestamp(Timestamp dealTimestamp) {
        this.dealTimestamp = dealTimestamp;
    }

    public Double getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(Double dealAmount) {
        this.dealAmount = dealAmount;
    }
}
