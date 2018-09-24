package com.company;

/**
 * Testing class
 *
 * @author Darío Castañeda
 */
public class TestTransacction {

    public static void main(String[] args) {

        Transacction transacction = new Transacction();
        // necesitamos escapar la cadena
        String json = transacction.add(1, "{ \"amount\": 1.23, \"description\": \"Joes Tacos\", \"date\":\"2018-12-30\", \"user_id\": 345 }");
        System.out.println(">>>>>>>>> " + json);

        String resultShow = transacction.show(345 , "3bb57e8a-1001-4282-9aab-3e6ddc771d3c");
        System.out.println("resultShow " + resultShow);

        String resultList = transacction.list(345 );
        System.out.println("resultList " + resultList);

        String sum = transacction.sum(345 );
        System.out.println("sum " + sum);

        // { "amount": 1.23, "description": "Joes Tacos", "date":"2018-12-30", "user_id": 345 }


    }



}
