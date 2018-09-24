package com.company.bean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Main pojo for mapping json to objects and persist data
 *
 * @author Darío Castañeda
 */
@Generated("org.jsonschema2pojo")
public class Result {
    @SerializedName("transactions")
    @Expose
    private List<TransactionDTO> transactions = new ArrayList<TransactionDTO>();
    /**
     *
     * @return The todos
     */
    public List<TransactionDTO> getTodos() {
        return transactions;
    }
    /**
     *
     * @param todos
     * The todos
     */
    public void setTodos(List<TransactionDTO> transactions) {
        this.transactions = transactions;
    }
}