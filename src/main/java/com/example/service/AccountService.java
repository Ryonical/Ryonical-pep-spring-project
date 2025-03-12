package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService implements AccountRepository
{
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository2){
        accountRepository = accountRepository2;
    }


    @Autowired
    public Account getAccount(Account account)
    {

    }
    
    @Autowired
    public Account insertAccount(Account account)
    {

    }

    @Autowired
    public Account loginAccount(Account account)
    {

    }
}
