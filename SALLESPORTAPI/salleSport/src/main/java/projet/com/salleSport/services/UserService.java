package projet.com.salleSport.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import projet.com.salleSport.dto.UpdatePasswordRequest;
import projet.com.salleSport.models.User;
import projet.com.salleSport.repositories.UserRepository;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void updatePassword(String username, UpdatePasswordRequest request) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Impossible de mettre à jour le mot de passe: Utilisateur non trouvé !"));

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Ancien mot de passe incorrect");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    public void updateUsername (String currentUsername, String newUsername) {
        User user = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("Impossible de mettre à jour le nom d'utilisateur: Utilisateur non trouvé"));

        user.setUsername(newUsername);
        userRepository.save(user);
    }

    public void updateUserRole(String adminUsername, String targetUsername, String newRole) {
        System.out.println(adminUsername + targetUsername);
        User admin = userRepository.findByUsername(adminUsername)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        System.out.println(admin.getUsername());
        if (!admin.getRole().equals("ADMIN")) {
            throw new RuntimeException("Accès refusé: Seul L'administrateur peut changer le rôle d'un utilisateur.");
        }

        User user = userRepository.findByUsername(targetUsername)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        System.out.println(user.getUsername());

        user.setRole(newRole);
        userRepository.save(user);
    }
}
