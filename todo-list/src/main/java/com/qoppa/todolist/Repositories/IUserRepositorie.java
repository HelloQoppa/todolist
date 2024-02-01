package com.qoppa.todolist.Repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qoppa.todolist.models.UserModel;

public interface IUserRepositorie extends JpaRepository<UserModel, UUID> {
    UserModel findByUsername(String username);

}
