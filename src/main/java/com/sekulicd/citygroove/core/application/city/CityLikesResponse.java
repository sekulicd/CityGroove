package com.sekulicd.citygroove.core.application.city;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CityLikesResponse {
    String city;
    int favouriteCount;
}
