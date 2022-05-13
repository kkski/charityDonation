package pl.coderslab.charity.services;

import pl.coderslab.charity.model.User;
import pl.coderslab.charity.model.UserDto;

public interface UserService {

        User findByUsername(String username);

        void saveUser(UserDto user);
    }

