package demo.model;

import lombok.Data;

@Data
public class ChangePassword {
    private String username;
    private String currentPassword;
    private String newPassword;
}
