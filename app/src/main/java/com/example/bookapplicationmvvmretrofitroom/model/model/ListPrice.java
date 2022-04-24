package com.example.bookapplicationmvvmretrofitroom.model.model;

import java.io.Serializable;

public class ListPrice implements Serializable {

        public double amount;
        public String currencyCode;
        public int amountInMicros;

    public ListPrice(double amount, String currencyCode, int amountInMicros) {
        this.amount = amount;
        this.currencyCode = currencyCode;
        this.amountInMicros = amountInMicros;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public int getAmountInMicros() {
        return amountInMicros;
    }

    public void setAmountInMicros(int amountInMicros) {
        this.amountInMicros = amountInMicros;
    }
}
