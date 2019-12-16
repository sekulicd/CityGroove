package com.sekulicd.citygroove.core.exception;

public class CityAlreadyExistException extends CustomException{
    public CityAlreadyExistException(String name){
        super(String.format("city with name %s already exists", name));
    }
}