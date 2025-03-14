package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

import org.springframework.data.jpa.repository.JpaRepository;

@Service
public class AccountService
{
    @Autowired
    private AccountRepository accountRepository;


    public Account findByUsername(String username)
    {
        return accountRepository.findByUsername(username);
    }
    
    public Account insertAccount(Account account)
    {
        if(account.getUsername() != "" && account.getPassword().length() >= 4 && accountRepository.findByUsername(account.getUsername()) == null)
        {
            return accountRepository.insertAccount(account);
        }
        return null;
    }

    public Account loginAccount(String username, String password)
    {
        return accountRepository.getAccountByUsernameAndPassword(username, password);
    }
}
