package com.saad.baitalkhairat.model.wallet;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Transaction implements Serializable {

    @SerializedName("charge_type_label")
    private String chargeTypeLabel;

    @SerializedName("note")
    private String note;

    @SerializedName("amount")
    private String amount;

    @SerializedName("charge_type")
    private int chargeType;

    @SerializedName("payment_type")
    private int paymentType;

    @SerializedName("reference_id")
    private String referenceId;

    @SerializedName("payment_type_label")
    private String paymentTypeLabel;

    @SerializedName("transaction_type_label")
    private String transactionTypeLabel;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("id")
    private int id;

    @SerializedName("transaction_type")
    private int transactionType;

    public String getChargeTypeLabel() {
        return chargeTypeLabel;
    }

    public String getNote() {
        return note;
    }

    public String getAmount() {
        return amount;
    }

    public int getChargeType() {
        return chargeType;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public String getPaymentTypeLabel() {
        return paymentTypeLabel;
    }

    public String getTransactionTypeLabel() {
        return transactionTypeLabel;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public int getId() {
        return id;
    }

    public int getTransactionType() {
        return transactionType;
    }
}