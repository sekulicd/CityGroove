package com.sekulicd.citygroove.core.application.identity;

import com.sekulicd.citygroove.core.domain.identity.User;
import com.sekulicd.citygroove.core.exception.CustomException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.List;

public interface UserService{
    void login(UserDto userDto) throws CustomException;
    void register(UserDto userDto) throws CustomException;
    List<User> findAll();
    UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException;
}
