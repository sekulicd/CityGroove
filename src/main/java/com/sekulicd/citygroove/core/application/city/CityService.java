package com.sekulicd.citygroove.core.application.city;

import com.sekulicd.citygroove.core.exception.CustomException;

import java.util.List;

public interface CityService {
   void insertCity(CityDto cityDto) throws CustomException;
   void addToFavouritesList(CityLikeDto cityLikeDto) throws CustomException;
   void removeFromFavouritesList(CityLikeDto cityLikeDto) throws CustomException;
   List<CityLikesResponse> getCityLikes() throws CustomException;
   CityResponse findAll(String sortByParam) throws CustomException;
}
