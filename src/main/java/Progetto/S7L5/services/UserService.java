package Progetto.S7L5.services;

import Progetto.S7L5.entities.User;
import Progetto.S7L5.entities.UserRole;
import Progetto.S7L5.repositories.UserRepository;
import Progetto.S7L5.payloads.UserDTO;
import Progetto.S7L5.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("L'utente con ID " + id + " non è stato trovato"));
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new NotFoundException("L'utente con ID " + id + " non è stato trovato");
        }
    }
}
