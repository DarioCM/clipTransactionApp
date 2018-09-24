package com.company.model;


import com.company.bean.Result;
import com.company.bean.TransactionDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Methods implementation
 * I created a interface ITransactionDAO, this implementaccion constains all the methods
 * than we need, Independent of persistence type.
 * If we select hazelcast, hibernate, a file ... etc all of them have to implement the interface ITransactionDAO
 * @author Darío Castañeda
 */
public class TransactionFile implements ITransactionDAO {

    static Result r = new Result();

    /**
     * Init
     * static will be the first thing than load the class; Loading and charge all the data in the JSON file
    **/
    static {
        dataLoad();
    }

    /**
     * Adds a TransactionDTO object to the data list
     * @param userId Integer the userID
     * @param transactionJSON String json value
     * @return the json representation of the value added
     */
    @Override
    public String add(Integer userId, String transactionJSON) {
        dataLoad();
        Gson gson = new Gson();
        TransactionDTO transactionDTO = gson.fromJson( transactionJSON, TransactionDTO.class );
        transactionDTO.setTransactionID(getID_UUID());
        // MongoManager.insert(transactionDTO);
        // IOManager.insert(transactionDTO);
        if(r!=null) {
            r.getTodos().add(transactionDTO);
        } else {
            r = new Result();
            List<TransactionDTO> transactions = new ArrayList<TransactionDTO>();
            transactions.add(transactionDTO);
            r.setTodos(transactions);
        }
        save(); // save all the data
        return gson.toJson(transactionDTO);
    }

    /**
     * Searches on the data list, the transaction associated with the user
     * @param userId Integer the userID
     * @param transactionId String the id of the transaction
     * @return the json representation of the transaction found
     */
    @Override
    public String show(Integer userId, String transactionId) {
        dataLoad();
        Gson gson = new Gson();
        for (TransactionDTO t : r.getTodos()) {
            if(t.getUser_id().equals(userId) && t.getTransactionID().equals(transactionId))
                return gson.toJson(t);
        }
        return "Transaction not found";
    }

    /**
     * Searches on the data list, all the transactions associated with the user
     * @param userId Integer the userID
     * @return the json representation of the transactions found
     */
    @Override
    public String list(final Integer userId){
        dataLoad();
        StringBuilder sbr = new StringBuilder();
        Gson gson = new Gson();
        List<TransactionDTO> resultList = new ArrayList<TransactionDTO>();
        for (TransactionDTO t : r.getTodos()) {
            if(t.getUser_id().equals(userId)){
                resultList.add(t);
                sbr.append(gson.toJson(t));
                sbr.append("\n");
            }
        }
        return sbr.toString();
    }

    /**
     * From all user transactions, add the amounts
     * @param userId Integer the userID
     * @return the sum of the amounts
     */
    @Override
    public String sum(final Integer userId){
        dataLoad();
        Gson gson = new Gson();
        BufferedReader br = null;
        Double sumD = 0D;
        for (TransactionDTO t : r.getTodos()) {
            if(t.getUser_id().equals(userId)) {
                sumD = Double.sum(sumD,t.getAmount());
            }
        }
        return "{\"user_id\":" + userId + ", \"sum:\":" + String.format( "%.2f", sumD ) + "}";
    }

    /**
     * This method is executed when we leave the application with the quit command
     * Its not necessary but with this we guarantee data integrity
     */
    public void save(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String strJson = gson.toJson(r);
        FileWriter writer = null;
        try {
            writer = new FileWriter("src/main/resources/file1.txt");
            writer.write(strJson);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * when we add a transaction we need to save the data in the file
     * @param transactionDTO data transaction
     * @return true if we save the data false if something went wrong
     */
    public boolean savetransactionDTO(final TransactionDTO transactionDTO){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String strJson = gson.toJson(transactionDTO);
        FileWriter writer = null;
        try {
            writer = new FileWriter("src/main/resources/file1.txt");
            writer.write(strJson);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * We load the data that is in the file
     */
    public static void dataLoad(){
        Gson gson = new Gson();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("src/main/resources/file1.txt"));
            Result result = gson.fromJson(br, Result.class);
            r = result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    // the document does not specify how to generate the id
    private String getID_UUID(){
        //generate random UUIDs
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}
