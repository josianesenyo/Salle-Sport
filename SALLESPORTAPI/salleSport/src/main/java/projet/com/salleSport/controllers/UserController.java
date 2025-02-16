package projet.com.salleSport.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import projet.com.salleSport.dto.UpdatePasswordRequest;
import projet.com.salleSport.dto.UpdateRoleRequest;
import projet.com.salleSport.dto.UpdateUsernameRequest;
import projet.com.salleSport.services.UserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;

    @PutMapping("/update-password")
    public ResponseEntity<String> updatePassword (@PathVariable String username, @RequestBody UpdatePasswordRequest request){
        userService.updatePassword(username, request);
        return ResponseEntity.ok("Mot de passe mis à jour avec succès !🎉🎉🎉");
    }

    @PutMapping("/update-username")
    public ResponseEntity<String> updateUsername(@RequestBody UpdateUsernameRequest request) {
        userService.updateUsername(request.getCurrentUsername(), request.getNewUsername());
        return ResponseEntity.ok("Utilisateur mis à jour avec succès !🎉🎉🎉");
    }

    @PutMapping("/update-role")
    public ResponseEntity<String> updateUserRole(@RequestBody UpdateRoleRequest request) {
        System.out.println("fgfffffffffffffffffffffffffffffffffffffffffff");
        userService.updateUserRole(request.getAdminUsername(), request.getTargetUsername(), request.getNewRole());
        return ResponseEntity.ok("Rôle mis à jour avec succès !🎉🎉🎉");
    }
}
