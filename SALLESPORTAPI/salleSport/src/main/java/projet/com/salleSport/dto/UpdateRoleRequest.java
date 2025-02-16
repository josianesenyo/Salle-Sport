package projet.com.salleSport.dto;

import lombok.Data;

@Data
public class UpdateRoleRequest {
    private String adminUsername;
    private String targetUsername;
    private String newRole;
}
