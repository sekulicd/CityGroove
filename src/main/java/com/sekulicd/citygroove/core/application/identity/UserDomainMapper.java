package com.sekulicd.citygroove.core.application.identity;

import com.sekulicd.citygroove.core.domain.identity.User;
import org.springframework.stereotype.Component;

@Component
class UserDomainMapper {

    User convert(UserDto userDto){
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        return user;
    }
}
