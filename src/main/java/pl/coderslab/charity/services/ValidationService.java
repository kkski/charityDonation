package pl.coderslab.charity.services;

import org.springframework.validation.BindingResult;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.model.UserDto;

public interface ValidationService {

    void validateUser(UserDto userForm, BindingResult bindingResult);

    void validateLoggedUser(UserDto userForm, BindingResult bindingResult, User loggedUser);
}
