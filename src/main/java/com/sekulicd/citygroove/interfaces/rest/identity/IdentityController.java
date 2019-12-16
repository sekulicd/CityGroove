package com.sekulicd.citygroove.interfaces.rest.identity;

import com.sekulicd.citygroove.core.application.identity.AuthenticationResponse;
import com.sekulicd.citygroove.core.application.identity.UserDto;
import com.sekulicd.citygroove.core.application.identity.UserService;
import com.sekulicd.citygroove.core.domain.identity.User;
import com.sekulicd.citygroove.core.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class IdentityController {

    private final UserService userService;

    private final JwtProvider jwtTokenProvider;

    @RequestMapping(method = RequestMethod.GET)
    public String hello(){
        return "hello";
    }

    @PostMapping(value = "/public/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> register(@Valid @RequestBody UserDto userDto){
        userService.register(userDto);
        String jwt = jwtTokenProvider.createToken(userDto.getUserName());
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setJwt(jwt);
        return  ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping(value = "/public/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> login(@Valid @RequestBody UserDto userDto) throws Exception{
        userService.login(userDto);
        String jwt = jwtTokenProvider.createToken(userDto.getUserName());
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setJwt(jwt);
        return  ResponseEntity.ok(authenticationResponse);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/public/users")
    public ResponseEntity<?> listUsers(){
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
