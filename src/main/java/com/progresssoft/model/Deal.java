package com.progresssoft.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Timestamp;

@Entity
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dealUniqueId;
    private String fromCurrencyIsoCode;
    private String toCurrencyIsoCode;
    private Timestamp dealTimestamp;
    private Double dealAmount;

    public Deal() {
    }

    public Deal(Long dealUniqueId, String fromCurrencyIsoCode, String toCurrencyIsoCode, Timestamp dealTimestamp, Double dealAmount) {
        this.dealUniqueId = dealUniqueId;
        this.fromCurrencyIsoCode = fromCurrencyIsoCode;
        this.toCurrencyIsoCode = toCurrencyIsoCode;
        this.dealTimestamp = dealTimestamp;
        this.dealAmount = dealAmount;
    }

    public Long getdealUniqueId() {
        return dealUniqueId;
    }

    public void setdealUniqueId(Long id) {
        this.dealUniqueId = id;
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

    @Override
    public String toString() {
        return "Deal{" +
                "dealUniqueId=" + dealUniqueId +
                ", fromCurrencyIsoCode='" + fromCurrencyIsoCode + '\'' +
                ", toCurrencyIsoCode='" + toCurrencyIsoCode + '\'' +
                ", dealTimestamp=" + dealTimestamp +
                ", dealAmount=" + dealAmount +
                '}';
    }
}