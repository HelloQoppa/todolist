package com.qoppa.todolist.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qoppa.todolist.Repositories.IUserRepositorie;
import com.qoppa.todolist.models.UserModel;

import at.favre.lib.crypto.bcrypt.BCrypt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepositorie iUserRepositorie;

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody UserModel userModel) {
        var user = this.iUserRepositorie.findByUsername(userModel.getUsername());

        if (user != null) {

            return ResponseEntity.badRequest().body("Username already exists");
        }

        var bcryptHashString  = BCrypt.withDefaults()
                .hashToString(12, userModel.getPassword().toCharArray());

        userModel.setPassword(bcryptHashString );

        iUserRepositorie.save(userModel);
        return ResponseEntity.ok().body("Sucess");
    }

}
