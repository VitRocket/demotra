package com.blogspot.vitrocket.demotra.service01.form;

import com.blogspot.vitrocket.demotra.service01.model.Account;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AccountForm {

    private Integer id;
    private String name;
    private String email;
    private Integer version;

    public static AccountForm accountToAccountForm(Account account) {
        return new AccountForm(account.getId(), account.getName(), account.getEmail(), account.getVersion());
    }

    public static Account formToAccount(AccountForm accountForm) {
        return new Account(accountForm.getId(), accountForm.getName(), accountForm.getEmail(), accountForm.getVersion());
    }
}
