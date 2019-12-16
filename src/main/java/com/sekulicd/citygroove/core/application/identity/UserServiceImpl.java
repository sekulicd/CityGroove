package com.sekulicd.citygroove.core.application.identity;

import com.sekulicd.citygroove.core.domain.identity.User;
import com.sekulicd.citygroove.core.domain.identity.UserRepository;
import com.sekulicd.citygroove.core.exception.BadPasswordException;
import com.sekulicd.citygroove.core.exception.CustomException;
import com.sekulicd.citygroove.core.exception.UserAlreadyExistException;
import com.sekulicd.citygroove.core.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserDomainMapper userDomainMapper;

    @Override
    public void register(UserDto userDto) throws CustomException{
        User user = userRepository.findByUserName(userDomainMapper.convert(userDto).getUserName());
        if (user != null) {
            throw new UserAlreadyExistException(userDto.getUserName());
        }
        userRepository.save(userDomainMapper.convert(userDto));
    }

    @Override
    public void login(UserDto userDto) throws CustomException {
        User user = userRepository.findByUserName(userDomainMapper.convert(userDto).getUserName());
        if (user == null) {
            throw new UserNotFoundException(userDto.getUserName());
        }
        if (!user.getPassword().equals(userDto.getPassword())) {
            throw new BadPasswordException();
        }
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);

        if (user == null) {
            throw new UsernameNotFoundException("User '" + userName + "' not found");
        }

        return org.springframework.security.core.userdetails.User//
                .withUsername(userName)
                .password(user.getPassword())
                .authorities(new ArrayList<>())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
