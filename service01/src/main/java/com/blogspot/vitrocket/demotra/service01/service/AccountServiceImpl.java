package com.blogspot.vitrocket.demotra.service01.service;

import com.blogspot.vitrocket.demotra.service01.model.Account;
import com.blogspot.vitrocket.demotra.service01.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Component
@RequiredArgsConstructor
//@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@SessionScope
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

    @Override
    public String getInfo() {
        return this.getClass().getSimpleName() + ": " + "@" + Integer.toHexString(this.hashCode());
    }

    @Override
    public void save(Account account) throws Exception {
        if (accountRepository.existsById(account.getId())) {
            try {
                accountRepository.save(account);
            } catch (StaleObjectStateException e) {
                throw new Exception(e.getMessage());
            }
        } else {
            throw new Exception("Невозможно сделать изменения, аккаунт был удалён");
        }


    }
}
