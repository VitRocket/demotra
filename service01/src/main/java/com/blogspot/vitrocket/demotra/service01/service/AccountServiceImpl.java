package com.blogspot.vitrocket.demotra.service01.service;

import com.blogspot.vitrocket.demotra.service01.model.Account;
import com.blogspot.vitrocket.demotra.service01.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Scope("session")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private final AccountRepository accountRepository;


    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account addAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void deleteAccount(Integer id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Account getAccount(Integer id) {
        return accountRepository.getOne(id);
    }
}
