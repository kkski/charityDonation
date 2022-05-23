package pl.coderslab.charity.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import pl.coderslab.charity.model.Role;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.model.UserDto;
import pl.coderslab.charity.repositories.RoleRepository;
import pl.coderslab.charity.repositories.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAllWhereRoleIsAdmin() {
        Role admin = roleRepository.findByName("ROLE_ADMIN");
        return userRepository.findAll().stream().filter(user -> user.getRoles().contains(admin)).collect(Collectors.toList());
    }
    @Override
    public List<User> findAllWhereRoleIsUser() {
        Role admin = roleRepository.findByName("ROLE_USER");
        return userRepository.findAll().stream().filter(user -> user.getRoles().contains(admin)).collect(Collectors.toList());
    }

    @Override
    public void saveUser(UserDto dto) {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEnabled(1);
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public void saveAdmin(UserDto dto) {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEnabled(1);
        Role userRole = roleRepository.findByName("ROLE_ADMIN");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public void updateLoggedUser(Long userId, UserDto dto) {
        User userToSave = userRepository.getById(userId);
        userToSave.setFirstName(dto.getFirstName());
        userToSave.setLastName(dto.getLastName());
        userToSave.setUsername(dto.getUsername());
        userToSave.setEmail(dto.getEmail());
        userToSave.setPassword(passwordEncoder.encode(dto.getPassword()));
        userToSave.setEnabled(1);
        userRepository.save(userToSave);
    }

    @Override
    public User findById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public void disableUser(Long userId) {
        User user = userRepository.getById(userId);
        user.setEnabled(0);
        userRepository.save(user);
    }
//rozbić, uzyc repo nie serwis EXISTS



}


