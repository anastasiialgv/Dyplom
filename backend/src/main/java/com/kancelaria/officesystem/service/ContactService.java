package com.kancelaria.officesystem.service;

import com.kancelaria.officesystem.DTOMapper;
import com.kancelaria.officesystem.model.dto.Contact.ContactDTO;
import com.kancelaria.officesystem.model.entity.Contact;
import com.kancelaria.officesystem.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;
    private final DTOMapper dtoMapper;

    //#########-------------12-------------#########//
    public List<ContactDTO> getContactsByCaseId(Integer caseId) {
        return contactRepository.findByLawCase_NumberCase(caseId)
                .stream()
                .map(dtoMapper::mapToContactCaseDTO)
                .collect(Collectors.toList());
    }

    public List<ContactDTO> getContactsByEmployeeId(Integer userId) {
        return contactRepository.findByLawCase_Employee_UserId(userId)
                .stream()
                .map(dtoMapper::mapToContactDTO)
                .collect(Collectors.toList());
    }

    //#########-------------6-------------#########//
    public ContactDTO getContactById(Integer contactId) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new RuntimeException("Contact not found"));

        return dtoMapper.mapToContactDTO(contact);
    }

}
