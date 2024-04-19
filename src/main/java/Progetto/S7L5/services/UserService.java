package Progetto.S7L5.services;


import Progetto.S7L5.entities.User;
import Progetto.S7L5.entities.UserRole;
import Progetto.S7L5.payloads.UserDTO;
import Progetto.S7L5.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(UserDTO userDTO) {
        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        // Hashing della password prima di salvarla
        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
        newUser.setPassword(hashedPassword);
        // Impostazione del ruolo predefinito per gli utenti
        newUser.setRole(UserRole.USER);
        // Salvataggio dell'utente nel database
        return userRepository.save(newUser);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
