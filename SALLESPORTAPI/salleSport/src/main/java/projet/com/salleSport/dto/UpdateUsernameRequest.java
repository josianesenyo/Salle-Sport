package projet.com.salleSport.dto;

import lombok.Data;

@Data
public class UpdateUsernameRequest  {
    private String currentUsername;
    private String newUsername;
}
