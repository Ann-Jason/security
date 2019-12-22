package com.dksy.seciruty.controller;

import com.dksy.seciruty.entity.User;
import com.dksy.seciruty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails user){
        return user;
    }


    @GetMapping()
    public ResponseEntity<List<User>> queryUserList(){
        return ResponseEntity.ok(userService.queryUserList());

    }


    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody User user){

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> queryUserById(@PathVariable Integer id){
        return ResponseEntity.ok(new User());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){

        return ResponseEntity.ok().build();
    }

    @PutMapping()
    public ResponseEntity<Void> save(@RequestBody User user){

        return ResponseEntity.ok().build();
    }



}
