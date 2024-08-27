package com.aptota.featuretoggle.service;

import com.aptota.featuretoggle.config.AppConfig;
import com.aptota.featuretoggle.model.CustomUserDetails;
import com.aptota.featuretoggle.model.Role;
import com.aptota.featuretoggle.model.User;
import com.aptota.featuretoggle.model.dto.RoleReqDto;
import com.aptota.featuretoggle.model.dto.UserReqDto;
import com.aptota.featuretoggle.model.dto.UserRespDto;
import com.aptota.featuretoggle.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final AppConfig appConfig;

    public UserService(UserRepository userRepository, AppConfig appConfig) {
        this.userRepository = userRepository;
        this.appConfig = appConfig;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(CustomUserDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("User not found with username: " + username));
    }


    public UserRespDto registerUser(UserReqDto userReqDto) {
        Optional<User> byUsername = userRepository.findByUsername(userReqDto.getUsername());
        if (byUsername.isPresent()) {
            throw new UsernameNotFoundException("Username already exists");
        }
        User saveUser = userRepository.save(convertUserReqDtoToUser(userReqDto));
       return convertUserToUserRespDto(saveUser);
    }

    public UserRespDto updateUser(Long id, UserReqDto userReqDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException("User not found with id: " + id);
        }
        User user = optionalUser.get();
        if(userReqDto.getFirstName()!= null){
            user.setFirstName(userReqDto.getFirstName());
        }
        if(userReqDto.getLastName()!= null){
            user.setLastName(userReqDto.getLastName());
        }
        if(userReqDto.getMobileNumber()!=null){
            user.setMobileNumber(userReqDto.getMobileNumber());
        }
        if(userReqDto.getPassword()!=null){
            user.setPassword(userReqDto.getPassword());
        }
        if(userReqDto.getEmail()!=null){
            user.setEmail(userReqDto.getEmail());
        }
        User save = userRepository.save(user);
        return convertUserToUserRespDto(save);
    }

    public UserRespDto findById(Long id) {
        return userRepository.findById(id)
                .map(this::convertUserToUserRespDto)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id : "+ id));

    }

    public List<UserRespDto> findAll(){
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertUserToUserRespDto)
                .toList();
    }

    public void deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException("User not found with id: " + id);
        }
        userRepository.delete(optionalUser.get());
    }

    public UserRespDto convertUserToUserRespDto(User user) {
        return UserRespDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .mobileNumber(user.getMobileNumber())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .roles(user.getRoles().stream().map(role -> RoleReqDto.builder().role(role.getRole()).build()).toList())
                .build();
    }

    public User convertUserReqDtoToUser(UserReqDto userReqDto) {
        User user = User.builder()
                .email(userReqDto.getEmail())
                .mobileNumber(userReqDto.getMobileNumber())
                .firstName(userReqDto.getFirstName())
                .lastName(userReqDto.getLastName())
                .username(userReqDto.getUsername())
                .password(appConfig.bCryptPasswordEncoder().encode(userReqDto.getPassword()))
                .build();
        List<RoleReqDto> roleDtoList = userReqDto.getRoles();
        List<Role> roles = roleDtoList.stream()
                .map(roleReqDto -> Role.builder()
                        .role(roleReqDto.getRole())
                        .user(user)
                        .build())
                .toList();
        user.setRoles(roles);
        return user;
    }


}
