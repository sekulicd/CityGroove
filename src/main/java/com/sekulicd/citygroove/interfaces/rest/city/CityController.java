package com.sekulicd.citygroove.interfaces.rest.city;

import com.sekulicd.citygroove.core.application.city.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping(value = "/public/city", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> findAll(@RequestParam(name = "sortBy", defaultValue = "createdAt") String sortBy){
        CityResponse response = cityService.findAll(sortBy);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/city", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> insertNewCity(@Valid @RequestBody CityDto cityDto){
        cityService.insertCity(cityDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/city/like", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addToFavouriteList(@Valid @RequestBody CityLikeDto cityLikeDto){
        cityService.addToFavouritesList(cityLikeDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/city/like", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> removeToFavouriteList(@Valid @RequestBody CityLikeDto cityLikeDto){
        cityService.removeFromFavouritesList(cityLikeDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/public/likes", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> getCityLikes(){
        List<CityLikesResponse>  response = cityService.getCityLikes();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
