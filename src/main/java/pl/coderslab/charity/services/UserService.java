package pl.coderslab.charity.services;

import pl.coderslab.charity.model.User;
import pl.coderslab.charity.model.UserDto;

import java.util.List;

public interface UserService {

        User findByUsername(String username);
        List<User> findAllWhereRoleIsAdmin();
        void saveUser(UserDto user);
        void saveAdmin(UserDto user);
        User findById(Long id);
    }

