package com.example.restservice.services;

// create UserService
// create UserServiceImpl
// create UserServices

import com.example.restservice.accessingdatamongodb.CarrotUser;
import com.example.restservice.accessingdatamongodb.CarrotUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class CarrotUserService {

    @Autowired
    CarrotUsersRepository carrotUsersRepository;

    public void createUser(String password, String email) {


        carrotUsersRepository.save(
                new CarrotUser(password, email));
    }

    public CarrotUser getUserByEmail(String email) {
        return carrotUsersRepository.findByEmail(email);
    }

    public CarrotUser authenticateUser(String email, String password) {
        CarrotUser carrotUser = carrotUsersRepository.findByEmail(email);
        if (carrotUser != null && BCrypt.checkpw(password, carrotUser.getPassword())) {
            return carrotUser;
        }
        return null;
    }
}
