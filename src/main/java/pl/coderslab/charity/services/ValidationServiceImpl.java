package pl.coderslab.charity.services;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.model.UserDto;
import pl.coderslab.charity.repositories.UserRepository;

@Service
public class ValidationServiceImpl implements ValidationService {
    private final BCryptPasswordEncoder b;
    private final UserRepository userRepository;

    public ValidationServiceImpl(BCryptPasswordEncoder b, UserRepository userRepository) {
        this.b = b;
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

    @Override
    public void validateLoggedUser(UserDto userForm, BindingResult bindingResult, User loggedUser) {


// TODO !!! BCRYPT MATCHES FAILS TO COMPARE INPUT WITH STORED PASSWORD
        if (userForm.getOldPassword() != null) {
            if(!b.matches(userForm.getPassword(),userRepository.findByUsername(loggedUser.getUsername()).getPassword())) {
                bindingResult.rejectValue("oldPassword", "oldPassword",
                        "Błąd w haśle!");
            }
        }

        if (!userForm.getPassword().equals(userForm.getPassword2())) {
            bindingResult.rejectValue("password", "password",
                    "Hasła do siebie nie pasują!");
            bindingResult.rejectValue("password2", "password2",
                    "Hasła do siebie nie pasują!");
        }




    }
}
