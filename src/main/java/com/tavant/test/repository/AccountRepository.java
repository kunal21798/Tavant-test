package com.tavant.test.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tavant.test.model.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

}
