package com.eviro.assessment.grad001.johnmootsi.repository;

import com.eviro.assessment.grad001.johnmootsi.model.AccountProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountProfileRepository extends JpaRepository<AccountProfile, Long> {
    Optional<AccountProfile> findByAccountHolderNameAndAccountHolderSurname(String accountHolderName, String accountHolderSurname);
}
