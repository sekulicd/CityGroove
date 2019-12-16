package com.sekulicd.citygroove.core.application.city;
import com.sekulicd.citygroove.core.domain.city.City;
import com.sekulicd.citygroove.core.domain.identity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CityDomainMapper {

    City convert(CityDto cityDto){
        City city = new City();
        city.setName(cityDto.getName());
        city.setDescription(cityDto.getDescription());
        city.setPopulation(cityDto.getPopulation());
        return city;
    }

    List<CityUserResponse> convert(List<User> users){
        List<CityUserResponse> response = users
                .stream()
                .map(user -> new CityUserResponse(user.getUserName(), user.getLikedCities()))
                .collect(Collectors.toList());
        return response;
    }

    CityResponse convert(ArrayList<City> cities){
        return new CityResponse(cities);
    }

}
