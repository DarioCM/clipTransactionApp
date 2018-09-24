package com.company.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * properties of the json object Transaction
 *
 * @author Darío Castañeda
 */
public class TransactionDTO implements Serializable {


    private  String transactionID;
    private  Double amount;
    private  String description;
    private  Date date;
    private  Integer user_id;

    public TransactionDTO(){
    }

    public TransactionDTO(String transactionID, Double amount, String description, Date date, Integer user_id) {
        this.transactionID = transactionID;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
                "transactionID='" + transactionID + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", user_id=" + user_id +
                '}';
    }

    public String getTransactionID() {
        return transactionID;
    }

    public Double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
