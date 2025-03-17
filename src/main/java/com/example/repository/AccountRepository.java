package com.example.repository;

import com.example.entity.Account;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>
{

    public Account findByUsername(String username);
    public Account findByUsernameAndPassword(String username, String password);
    public Account findById(int id);


}
