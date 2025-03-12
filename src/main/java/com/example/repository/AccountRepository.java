package com.example.repository;

import com.example.entity.Account;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface AccountRepository {

    public Account getAccount(Account account);
    public Account insertAccount(Account account);
    public Account loginAccount(Account account);


}
