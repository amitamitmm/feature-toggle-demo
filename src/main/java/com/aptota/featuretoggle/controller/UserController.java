package com.aptota.featuretoggle.controller;
import com.aptota.featuretoggle.model.dto.UserReqDto;
import com.aptota.featuretoggle.model.dto.UserRespDto;
import com.aptota.featuretoggle.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRespDto> register(@RequestBody UserReqDto userReqDto){
        return ResponseEntity.ok(userService.registerUser(userReqDto));
    }

    @GetMapping
    public ResponseEntity<List<UserRespDto>> getAllUsers(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRespDto> getUserById(@PathVariable("id") Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRespDto> updateUser(@PathVariable("id") Long id, @RequestBody UserReqDto userReqDto){
        return ResponseEntity.ok(userService.updateUser(id, userReqDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

}
