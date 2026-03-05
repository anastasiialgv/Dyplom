package com.kancelaria.officesystem.service;

import com.kancelaria.officesystem.DTOMapper;
import com.kancelaria.officesystem.model.dto.Case.CaseDTO;
import com.kancelaria.officesystem.model.dto.Case.CaseLongDTO;
import com.kancelaria.officesystem.model.dto.Case.CaseShortDTO;
import com.kancelaria.officesystem.model.entity.Case;
import com.kancelaria.officesystem.model.entity.File;
import com.kancelaria.officesystem.model.enums.FileType;
import com.kancelaria.officesystem.repository.CaseRepository;
import com.kancelaria.officesystem.repository.FileRepository;
import com.kancelaria.officesystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CaseService {
    private final CaseRepository caseRepository;
    private final UserRepository userRepository;
    private final FileRepository fileRepository;
    private final DTOMapper dtoMapper;
    //#########-------------4-------------#########//
    public List<CaseShortDTO> getCasesForEmployeeShort(Integer employeeId){
        if (!userRepository.existsById(employeeId)){
            throw new RuntimeException("User not found");
        }
        return caseRepository.findCasesByEmployeeId(employeeId);
    }

    public List<CaseDTO> getAllCases(){
        return caseRepository.findAll()
                .stream()
                .map(dtoMapper::mapToCaseDTO)
                .collect(Collectors.toList());
    }

    public CaseDTO getCaseById(Integer id){
        return caseRepository.findById(id).map(dtoMapper::mapToCaseDTO)
                .orElseThrow(() -> new RuntimeException("Case not found"));
    }

    //#########-------------3-------------#########//
    public CaseLongDTO getCaseDetails(Integer caseId) {
        Case lawCase = caseRepository.findById(caseId)
                .orElseThrow(() -> new RuntimeException("Case not found"));

        Optional<File> paymentProof = fileRepository.findByLawCase_NumberCaseAndFileType(caseId, FileType.PAYMENT_CONFIRMATION);

        return CaseLongDTO.builder()
                .numberCase(lawCase.getNumberCase())
                .status(lawCase.getStatus())
                .violationDate(lawCase.getViolationDate())
                .fineAmount(lawCase.getFineAmount())
                .address(lawCase.getAddress())
                .driver(dtoMapper.mapToDriverDTO(lawCase.getDriver()))
                .vehicle(dtoMapper.mapToVehicleDTO(lawCase.getVehicle()))
                .isPaymentProofUploaded(paymentProof.isPresent())
                .paymentProofFileId(paymentProof.map(File::getFileId).orElse(null))
                .paymentProofDownloadUrl(paymentProof.isPresent()
                        ? "/api/files/download/" + paymentProof.get().getFileId()
                        : null)
                .build();
    }

}
