package com.sekulicd.citygroove.core.domain.city;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CityRepository extends PagingAndSortingRepository<City, Long> {
    City findByName(String name);
}
