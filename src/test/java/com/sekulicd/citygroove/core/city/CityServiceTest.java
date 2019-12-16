package com.sekulicd.citygroove.core.city;

import com.sekulicd.citygroove.core.application.city.CityDomainMapper;
import com.sekulicd.citygroove.core.application.city.CityLikeDto;
import com.sekulicd.citygroove.core.application.city.CityServiceImpl;
import com.sekulicd.citygroove.core.domain.city.City;
import com.sekulicd.citygroove.core.domain.city.CityRepository;
import com.sekulicd.citygroove.core.domain.identity.User;
import com.sekulicd.citygroove.core.domain.identity.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
public class CityServiceTest {

    @MockBean
    CityRepository cityRepository;
    @MockBean
    UserRepository userRepository;
    @MockBean
    CityDomainMapper cityDomainMapper;

    CityServiceImpl cityService;
    City city;
    User user;

    @Before
    public void init(){
        cityService = new CityServiceImpl(cityRepository, userRepository, cityDomainMapper);
        city = new City();
        city.setName("podgorica");
        city.setFavouriteCount(0);

        user = new User();
        user.setUserName("dusan");
        Set<City> cities = new HashSet();
        cities.add(city);
        user.setLikedCities(cities);
    }

    @Test
    public void Given_CityFavouriteCountGreaterThenZero_When_RemoveFromFavouritesList_Then_UserSaveShouldBeInvoked(){
        //Given
        city.setFavouriteCount(1);

        //When
        Mockito.when(cityRepository.findByName("podgorica")).thenReturn(
                city
        );

        Mockito.when(userRepository.findByUserName("dusan")).thenReturn(
                user
        );

        CityLikeDto cityLikeDto = new CityLikeDto();
        cityLikeDto.setCityName(city.getName());
        cityLikeDto.setUserName(user.getUserName());
        cityService.removeFromFavouritesList(cityLikeDto);

        //Then
        Mockito.verify(userRepository).save(user);
    }

    @Test
    public void Given_CityFavouriteCountEqualsOne_When_AddToFavouritesList_Then_UserSaveShouldBeInvoked(){
        //Given
        city.setFavouriteCount(1);

        //When
        Mockito.when(cityRepository.findByName("podgorica")).thenReturn(
                city
        );

        Mockito.when(userRepository.findByUserName("dusan")).thenReturn(
                user
        );

        Mockito.when(userRepository.save(user)).thenReturn(
                user
        );

        CityLikeDto cityLikeDto = new CityLikeDto();
        cityLikeDto.setCityName(city.getName());
        cityLikeDto.setUserName(user.getUserName());
        cityService.addToFavouritesList(cityLikeDto);

        //Then
        Mockito.verify(userRepository).save(user);
    }

}