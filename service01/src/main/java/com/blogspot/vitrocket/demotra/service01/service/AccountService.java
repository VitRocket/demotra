package com.blogspot.vitrocket.demotra.service01.service;

import com.blogspot.vitrocket.demotra.service01.model.Account;

import java.util.List;

public interface AccountService {

    List<Account> getAllAccounts();

    Account addAccount(Account account) throws Exception;

    void deleteAccount(Integer id);

    Account getAccount(Integer id);

    String getInfo();

    void save(Account account) throws Exception;
}
