package com.sekulicd.citygroove.core.application.city;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CityDto {
    @NotBlank(message = "name is mandatory")
    String name;
    @NotBlank(message = "description is mandatory")
    String description;
    @NotNull(message = "population is mandatory")
    Integer population;
}
