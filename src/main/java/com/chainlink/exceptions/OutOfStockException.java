package com.chainlink.exceptions;

public class OutOfStockException extends Exception {
    public OutOfStockException(String msg){
        super(msg);
    }
}
