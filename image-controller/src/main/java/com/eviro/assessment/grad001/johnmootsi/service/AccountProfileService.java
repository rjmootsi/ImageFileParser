package com.eviro.assessment.grad001.johnmootsi.service;

import com.eviro.assessment.grad001.johnmootsi.model.AccountProfile;
import com.eviro.assessment.grad001.johnmootsi.repository.AccountProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AccountProfileService {

    @Autowired
    private AccountProfileRepository accountProfileRepository;
    public void saveAccountProfile(AccountProfile accountProfile) {
        // database operations to save the account profile
        accountProfileRepository.save(accountProfile);
    }

    public String getImageFilePathFromDatabase(String name, String surname) {
        // database operations to retrieve the image file path
        Optional<AccountProfile> accountProfileOptional = accountProfileRepository.findByAccountHolderNameAndAccountHolderSurname(name, surname);

        if (accountProfileOptional.isPresent()) {
            AccountProfile accountProfile = accountProfileOptional.get();
            return accountProfile.getHttpImageLink();
        } else {
            throw new NoSuchElementException("No account profile found with the given name and surname");
        }
    }
}

