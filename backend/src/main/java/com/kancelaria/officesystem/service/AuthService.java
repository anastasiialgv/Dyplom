package com.kancelaria.officesystem.service;

import com.kancelaria.officesystem.model.dto.Authorization.LoginRequestDTO;
import com.kancelaria.officesystem.model.dto.Authorization.LoginResponseDTO;
import com.kancelaria.officesystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.kancelaria.officesystem.model.entity.User;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    public LoginResponseDTO authorization(LoginRequestDTO loginRequestDTO) {
        Optional<User> userOptional = userRepository.findByEmail(loginRequestDTO.getEmail());

        if (userOptional.isEmpty())
            return LoginResponseDTO.builder()
                    .success(false)
                    .message("User not found. Please check email and try again")
                    .build();

        User user = userOptional.get();
        if (user.getPassword().equals(loginRequestDTO.getPassword()))
            return LoginResponseDTO.builder().
                    success(true)
                    .role(user.getRole())
                    .message("Success")
                    .userId(user.getUserId())
                    .build();
        else
            return LoginResponseDTO.builder().
                    success(false)
                    .message("Wrong Pass").build();

        }
    }

