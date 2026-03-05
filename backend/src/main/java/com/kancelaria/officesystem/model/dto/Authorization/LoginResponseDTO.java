package com.kancelaria.officesystem.model.dto.Authorization;

import com.kancelaria.officesystem.model.enums.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDTO {
    private boolean success;
    private String message;       // User not found, Wrong password, Success
    private Integer userId;
    private UserRole role;
}
