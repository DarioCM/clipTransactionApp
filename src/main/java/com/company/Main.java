package com.company;

import com.company.model.TransactionFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Main class, produce a menu and calls to the transaction class
 *
 * @author Darío Castañeda
 */
public class Main {

    public static void main(String[] args) {
        if(args.length == 0) {
            System.out.println("Clip ");
            System.out.println("Commands: ");
            System.out.println(" ADD TRANSACTION <user_id> add <transaction_json> ");
            System.out.println(" SHOW TRANSACTION <user_id> <transaction_id> ");
            System.out.println(" LIST TRANSACTIONS <user_id> list ");
            System.out.println(" SUM TRANSACTIONS <user_id> sum ");
            System.out.println(" EXIT quit ");
            BufferedReader br = null;
            TransactionFile transactionFile = new TransactionFile();
            String result = "";
            try {
                br = new BufferedReader(new InputStreamReader(System.in));
                while (true) {
                    System.out.print("Input: ");
                    String text = br.readLine();
                    if (text.equalsIgnoreCase("quit")) {
                        System.out.println("Exit.");
                        transactionFile.save();
                        break;
                    }
                    else if(text.contains("add")){
                        String[] splited = text.split("\\s+");
                        result = transactionFile.add(Integer.valueOf(splited[0]),text.substring(text.indexOf("{"),text.indexOf("}")+1));
                    }
                    else if(text.contains("list")){
                        String[] splited = text.split("\\s+");
                        result = transactionFile.list(Integer.valueOf(splited[0]));
                    }
                    else if(text.contains("sum")){
                        String[] splited = text.split("\\s+");
                        result = transactionFile.sum(Integer.valueOf(splited[0]));
                    }
                    else {
                        String[] splited = text.split("\\s+");
                        result = transactionFile.show(Integer.valueOf(splited[0]),splited[1]);
                    }

                    System.out.println(result);
                }

            } catch (Exception e) {
                e.printStackTrace();
                // TODO Exceptions
            } finally {
                /** closing the BufferedReader object */
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        // TODO Exceptions
                    }
                }
            }
        } else {
            //validation user_id args[0]
            TransactionFile transactionFile = new TransactionFile();
            String result = "";
            try {
                switch (args[1]) {
                    case "list":
                        result = transactionFile.list(Integer.valueOf(args[0]));
                        break;
                    case "add":
                        String str = String.join("" , args);
                        str = str.substring(str.indexOf("{"),str.indexOf("}")+1);
                        String aux = str.substring(str.indexOf("description:") + 12,str.indexOf("date:")-1);
                        if(aux.contains(" ")){
                            String sanitized = "\"" + aux + "\"";
                            str = str.replace(aux,sanitized);
                        }
                        result = transactionFile.add(Integer.valueOf(args[0]), str);
                        break;
                    case "sum":
                        result = transactionFile.sum(Integer.valueOf(args[0]));
                        break;
                    case "quit":
                        System.out.println("Exit.");
                        transactionFile.save();
                        break;
                    default:
                        result = transactionFile.show(Integer.valueOf(args[0]), args[1]);
                        break;
                }
                System.out.println(result);
            } catch (Exception e){
                e.printStackTrace();
                // TODO Exceptions
            }
        }
    }
}
