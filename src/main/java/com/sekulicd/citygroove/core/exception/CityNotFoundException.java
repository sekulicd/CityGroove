package com.sekulicd.citygroove.core.exception;

public class CityNotFoundException extends CustomException{
    public CityNotFoundException(String cityName){
        super(String.format("city with name %s not found", cityName));
    }
}
