package com.company.model;

/**
 * interface with the general methods to be implemented
 *
 * @author Darío Castañeda
 */
public interface ITransactionDAO {

 String add(final Integer userId, final String transactionJSON);

 String show(final Integer userId, final String transactionId);

 String list(final Integer userId);

 String sum(final Integer userId);

}
