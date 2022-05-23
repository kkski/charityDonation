package pl.coderslab.charity.services;

import org.springframework.validation.BindingResult;
import pl.coderslab.charity.model.UserDto;
import pl.coderslab.charity.repositories.UserRepository;

public class ValidationServiceImpl implements ValidationService {

    private final UserRepository userRepository;

    public ValidationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateUser(UserDto userForm, BindingResult bindingResult) {

        if (!userForm.getPassword().equals(userForm.getPassword2())) {
            bindingResult.rejectValue("password", "password",
                    "Hasła do siebie nie pasują!");
            bindingResult.rejectValue("password2", "password2",
                    "Hasła do siebie nie pasują!");
        }
        if (userRepository.existsByUsername(userForm.getUsername()) == true) {
            bindingResult.rejectValue("username", "username.exists",
                    "Taki uzytkownik juz istnieje!");
        }
        if (userRepository.existsByEmail(userForm.getEmail()) == true) {
            bindingResult.rejectValue("email", "email.exists",
                    "Taki email jest juz w uzyciu!");
        }

    }
}
