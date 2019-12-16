package com.sekulicd.citygroove.core.application.city;
import com.sekulicd.citygroove.core.domain.city.City;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
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

    List<CityLikesResponse> convert(List<City> cities){
        List<CityLikesResponse> response = cities
                .stream()
                .sorted(Comparator.comparing(City::getFavouriteCount).reversed())
                .map(city -> new CityLikesResponse(city.getName(), city.getFavouriteCount()))
                .collect(Collectors.toList());
        return response;
    }

    CityResponse convert(ArrayList<City> cities){
        return new CityResponse(cities);
    }

}
