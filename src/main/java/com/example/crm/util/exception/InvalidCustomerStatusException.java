 package com.example.crm.util.exception;
 
 /**
  * InvalidCustomerStatusException
  */
 public class InvalidCustomerStatusException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = -6254906356143759391L;

    public InvalidCustomerStatusException() {
       super("Invalid customer status");
   }
     
 }