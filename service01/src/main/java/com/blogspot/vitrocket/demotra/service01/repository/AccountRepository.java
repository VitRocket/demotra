package com.blogspot.vitrocket.demotra.service01.repository;

import com.blogspot.vitrocket.demotra.service01.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

}
