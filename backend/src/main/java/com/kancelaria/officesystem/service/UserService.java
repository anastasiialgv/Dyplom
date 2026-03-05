package com.kancelaria.officesystem.service;

import com.kancelaria.officesystem.DTOMapper;
import com.kancelaria.officesystem.model.dto.UserDTO;
import com.kancelaria.officesystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final DTOMapper dtoMapper;

    public UserDTO getUserById(Integer id) {
        return userRepository.findById(id).map(dtoMapper::mapUserDTO)
                .orElseThrow(() -> new RuntimeException("User not found"));

    }
}
