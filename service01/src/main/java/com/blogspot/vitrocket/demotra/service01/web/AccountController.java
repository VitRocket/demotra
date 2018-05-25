package com.blogspot.vitrocket.demotra.service01.web;

import com.blogspot.vitrocket.demotra.service01.form.AccountForm;
import com.blogspot.vitrocket.demotra.service01.model.Account;
import com.blogspot.vitrocket.demotra.service01.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/account")
@Scope("session")
public class AccountController {

    @Autowired
    private final AccountService accountService;

    @GetMapping("/")
    public String getUsers(Model model) {
        List<Account> accounts = accountService.getAllAccounts();
        model.addAttribute("accounts", accounts);
        model.addAttribute("controller", this.toString());
        model.addAttribute("service", accountService.toString());
        return "account";
    }

    @GetMapping("/add")
    public String getUserForm(Model model) {
        AccountForm accountForm = new AccountForm();
        model.addAttribute("accountForm", accountForm);
        return "accountform";
    }

    @PostMapping("/add")
    public String saveAccount(Model model, @ModelAttribute("accountForm") AccountForm accountForm) {
        String name = accountForm.getName();
        if (name != null && !name.isEmpty()) {
            Account account = new Account();
            account.setId(accountForm.getId());
            account.setName(accountForm.getName());
            account.setEmail(accountForm.getEmail());
            accountService.addAccount(account);
            return "redirect:/account/";
        }

        model.addAttribute("errorMessage", "Невозможно сохраниеть");
        return "accountform";
    }

    @GetMapping("/edit/{id}")
    public String deleteAccount(Model model, @PathVariable("id") Integer id) {
        Account accountForm = accountService.getAccount(id);
        model.addAttribute("accountForm", accountForm);
        System.out.println(accountForm.toString());
        return "accounteditform";
    }

    @PostMapping("/edit")
    public String saveEditAccount(Model model, @ModelAttribute("accountForm") AccountForm accountForm) {
        String name = accountForm.getName();
        System.out.println(accountForm);
        if (name != null && !name.isEmpty()) {
            Account account = new Account();
            account.setId(accountForm.getId());
            account.setName(accountForm.getName());
            account.setEmail(accountForm.getEmail());
            accountService.addAccount(account);
            return "redirect:/account/";
        }
        model.addAttribute("errorMessage", "Невозможно сохраниеть");
        return "accountform";
    }

}
