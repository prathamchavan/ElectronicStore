package com.electronic.store.Electronic.Store.exceptions;

public class BadApiRequest extends RuntimeException{

    public BadApiRequest(String message)
    {
        super(message);
    }

    public BadApiRequest(){
        super("Bad Request!!");
    }



}
