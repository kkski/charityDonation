package pl.coderslab.charity.services;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.model.UserDto;

import javax.validation.Valid;
import java.util.List;

public interface UserService {
        User findByEmail(String email);
        User findByUsername(String username);
        List<User> findAllWhereRoleIsAdmin();
        List<User> findAllWhereRoleIsUser();
        void saveUser(UserDto user);
        void saveAdmin(UserDto user);

        void updateLoggedUser(Long id, UserDto dto);
        User findById(Long id);
        void disableUser(Long userId);


    }

