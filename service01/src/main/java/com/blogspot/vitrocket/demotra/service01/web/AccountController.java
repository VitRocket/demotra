package com.blogspot.vitrocket.demotra.service01.web;

import com.blogspot.vitrocket.demotra.service01.form.AccountForm;
import com.blogspot.vitrocket.demotra.service01.model.Account;
import com.blogspot.vitrocket.demotra.service01.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/account")
//@Scope("session")
public class AccountController {

    @Autowired
    private final AccountService accountService;

    @GetMapping("/")
    public String getUsers(Model model) {
        List<Account> accounts = accountService.getAllAccounts();
        model.addAttribute("accounts", accounts);
        model.addAttribute("controller", getInfo());
        model.addAttribute("service", accountService.getInfo());
        return "account";
    }

    @GetMapping("/add")
    public String getUserForm(Model model) {
        AccountForm accountForm = new AccountForm();
        model.addAttribute("accountForm", accountForm);
        return "accountform";
    }

    @PostMapping("/add")
    public String saveAccount(Model model, @ModelAttribute("accountForm") AccountForm accountForm) throws Exception {
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
        AccountForm accountForm = AccountForm.accountToAccountForm(accountService.getAccount(id));
        model.addAttribute("accountForm", accountForm);
        return "accounteditform";
    }

    @PostMapping("/edit")
    public String saveEditAccount(Model model, @ModelAttribute("newAccountForm") AccountForm accountForm) {
        String name = accountForm.getName();

        System.out.println("accountForm: " + accountForm.toString());

        System.out.println(accountForm);

        if (name != null && !name.isEmpty()) {
            Account account = AccountForm.formToAccount(accountForm);
            try {
                //accountService.addAccount(account);
                accountService.save(account);
            } catch (Exception e) {
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                model.addAttribute("errorMessage", "Ошибка аккаунт был обновлён кем то другим," +
                        " при необходимости измените данные и попробуйте ещё раз.");

                try {//Костыль
                    accountForm = AccountForm.accountToAccountForm(accountService.getAccount(account.getId()));
                } catch (Exception e1) {
                    return "redirect:/account/";
                }


                model.addAttribute("accountForm", accountForm);
                return "accounteditform";
            }
            return "redirect:/account/";
        }

        model.addAttribute("errorMessage", "Невозможно сохраниеть");
        return "accountform";
    }

    public String getInfo() {
        return this.getClass().getSimpleName() + ": " + "@" + Integer.toHexString(this.hashCode());
    }

}
