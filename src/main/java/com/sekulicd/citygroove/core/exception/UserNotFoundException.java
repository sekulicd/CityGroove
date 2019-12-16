package com.sekulicd.citygroove.core.exception;

public class UserNotFoundException extends CustomException{

    public UserNotFoundException(String userName){
        super(String.format("user with username %s not found", userName));
    }
}
