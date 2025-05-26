package com.ncell.app.service;

import com.ncell.app.dao.AuthenticationDao;
import com.ncell.app.model.Number;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class AuthenticatorService {

    private final AuthenticationDao authenticationDao;

    public AuthenticatorService(AuthenticationDao authenticationDao) {
        this.authenticationDao = authenticationDao;
    }

    public boolean validateNumber(long number){
        boolean isValid = false;
        Optional<Number> numberOpt = authenticationDao.getNumber(number);
        if(numberOpt.isPresent()){
            isValid = true;
        }

        return isValid;
    }

    public void sentOTP(long number){
        Random random = new Random();
        int randomOTP = 100000 + random.nextInt(900000);
        authenticationDao.saveOTP(number, randomOTP);
    }

    public boolean validateOTP(long number, int otp){
        boolean isValid = false;
        Optional<Number> numberOpt = authenticationDao.getNumber(number);
    }
}
