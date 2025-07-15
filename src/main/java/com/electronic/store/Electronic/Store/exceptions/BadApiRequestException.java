package com.electronic.store.Electronic.Store.exceptions;

public class BadApiRequestException extends RuntimeException{

    public BadApiRequestException(String message)
    {
        super(message);
    }

    public BadApiRequestException(){
        super("Bad Request!!");
    }



}
