package com.progresssoft.dto;

import com.progresssoft.validation.CurrencyCode;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.sql.Timestamp;

public class DealRequestDTO {
    @NotEmpty(message = "From currency ISO code is required")
    @CurrencyCode
    private String fromCurrencyIsoCode;

    @NotEmpty(message = "To currency ISO code is required")
    @CurrencyCode
    private String toCurrencyIsoCode;

    @NotNull(message = "Deal timestamp is required")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp dealTimestamp;

    @NotNull(message = "Deal amount is required")
    @Positive(message = "Deal amount must be greater than 0")
    private Double dealAmount;

    public DealRequestDTO(String fromCurrencyIsoCode, String toCurrencyIsoCode, @NotNull(message = "Deal timestamp is required") Timestamp dealTimestamp, @NotNull(message = "Deal amount is required") Double dealAmount) {
        this.fromCurrencyIsoCode = fromCurrencyIsoCode;
        this.toCurrencyIsoCode = toCurrencyIsoCode;
        this.dealTimestamp = dealTimestamp;
        this.dealAmount = dealAmount;
    }

    public DealRequestDTO() {
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
