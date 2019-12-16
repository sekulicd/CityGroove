package com.sekulicd.citygroove.core.application.city;

import com.sekulicd.citygroove.core.domain.city.City;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CityResponse {
    List<City> cities;
}
