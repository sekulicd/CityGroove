package com.sekulicd.citygroove.core.application.city;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CityLikeDto {
    @NotBlank(message = "userName is mandatory")
    String userName;
    @NotBlank(message = "cityName is mandatory")
    String cityName;
}
