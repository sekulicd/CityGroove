package com.sekulicd.citygroove.core.exception;

public class UserAlreadyExistException extends CustomException{
    public UserAlreadyExistException(String userName){
        super(String.format("user with username %s already exists", userName));
    }
}
