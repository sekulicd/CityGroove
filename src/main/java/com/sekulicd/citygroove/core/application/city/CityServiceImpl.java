package com.sekulicd.citygroove.core.application.city;

import com.sekulicd.citygroove.core.domain.city.City;
import com.sekulicd.citygroove.core.domain.city.CityRepository;
import com.sekulicd.citygroove.core.domain.identity.User;
import com.sekulicd.citygroove.core.domain.identity.UserRepository;
import com.sekulicd.citygroove.core.exception.CityAlreadyExistException;
import com.sekulicd.citygroove.core.exception.CityNotFoundException;
import com.sekulicd.citygroove.core.exception.CustomException;
import com.sekulicd.citygroove.core.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CityServiceImpl implements CityService{

    private final CityRepository cityRepository;
    private final UserRepository userRepository;
    private final CityDomainMapper cityDomainMapper;

    @Override
    public void insertCity(CityDto cityDto) throws CustomException {
        City city = cityRepository.findByName(cityDto.getName());
        if (city != null) {
            throw new CityAlreadyExistException(cityDto.getName());
        }
        city = cityDomainMapper.convert(cityDto);
        city.setCreatedAt(LocalDateTime.now());
        cityRepository.save(city);
    }

    @Override
    public void addToFavouritesList(CityLikeDto cityLikeDto) throws CustomException {
        City city = cityRepository.findByName(cityLikeDto.getCityName());
        if (city == null) {
            throw new CityNotFoundException(cityLikeDto.getCityName());
        }

        User user = userRepository.findByUserName(cityLikeDto.getUserName());
        if (user == null) {
            throw new UserNotFoundException(cityLikeDto.getUserName());
        }
        user.getLikedCities().add(city);
        city.setFavouriteCount(city.getFavouriteCount() + 1);
        userRepository.save(user);
    }

    @Override
    public void removeFromFavouritesList(CityLikeDto cityLikeDto) throws CustomException {
        City city = cityRepository.findByName(cityLikeDto.getCityName());
        if (city == null) {
            throw new CityNotFoundException(cityLikeDto.getCityName());
        }

        User user = userRepository.findByUserName(cityLikeDto.getUserName());
        if (user == null) {
            throw new UserNotFoundException(cityLikeDto.getUserName());
        }

        if (city.getFavouriteCount() > 0) {
            city.setFavouriteCount(city.getFavouriteCount() - 1);
        }else{
            return;
        }

        user.getLikedCities().remove(city);
        userRepository.save(user);
    }

    @Override
    public List<CityLikesResponse> getCityLikes() throws CustomException {
        List<City> cities = (List<City>) cityRepository.findAll(Sort.by("favouriteCount").descending());
        return cityDomainMapper.convert(cities);
    }

    @Override
    public CityResponse findAll(String sortByParam) throws CustomException{
        List<City> cities = (List<City>) cityRepository.findAll(Sort.by(sortByParam).descending());
        return cityDomainMapper.convert((ArrayList<City>) cities);
    }

}
