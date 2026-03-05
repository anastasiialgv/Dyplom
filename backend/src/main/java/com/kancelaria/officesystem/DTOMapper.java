package com.kancelaria.officesystem;

import com.kancelaria.officesystem.model.dto.*;
import com.kancelaria.officesystem.model.dto.Case.CaseDTO;
import com.kancelaria.officesystem.model.dto.Contact.ContactDTO;
import com.kancelaria.officesystem.model.dto.File.FileDTO;
import com.kancelaria.officesystem.model.entity.*;
import org.springframework.stereotype.Component;

@Component
public class DTOMapper {
    // --- USER ---
    public UserDTO mapUserDTO(User user) {
        if (user == null) return null;
        return UserDTO.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .surname(user.getSurname())
                .phone(user.getPhone())
                .email(user.getEmail())
                .role(user.getRole()).build();
    }

    // --- DRIVER ---
    public DriverDTO mapToDriverDTO(Driver driver) {
        if (driver == null) return null;
        return DriverDTO.builder()
                .idDriver(driver.getIdDriver())
                .name(driver.getName())
                .surname(driver.getSurname())
                .birthDate(driver.getBirthDate())
                .pesel(driver.getPesel())
                .passportNumber(driver.getPassportNumber())
                .email(driver.getEmail())
                .phone(driver.getPhone())
                .address(driver.getAddress())
                .notes(driver.getNotes())
                .build();
    }

    // --- VEHICLE ---
    public VehicleDTO mapToVehicleDTO(Vehicle vehicle) {
        if (vehicle == null) return null;
        return VehicleDTO.builder()
                .plateNumber(vehicle.getPlateNumber())
                .brand(vehicle.getBrand())
                .model(vehicle.getModel())
                .color(vehicle.getColor())
                .idDriver(vehicle.getDriver() != null ? vehicle.getDriver().getIdDriver() : null)
                .build();
    }

    // --- CASE ---
    public CaseDTO mapToCaseDTO(Case lawCase) {
        if (lawCase == null) return null;
        return CaseDTO.builder()
                .numberCase(lawCase.getNumberCase())
                .status(lawCase.getStatus())
                .violationDate(lawCase.getViolationDate())
                .fineAmount(lawCase.getFineAmount())
                .overdueCount(lawCase.getOverdueCount())
                .address(lawCase.getAddress())
                .closedDate(lawCase.getClosedDate())
                .userId(lawCase.getEmployee() != null ? lawCase.getEmployee().getUserId() : null)
                .plateNumber(lawCase.getVehicle() != null ? lawCase.getVehicle().getPlateNumber() : null)
                .driverId(lawCase.getDriver() != null ? lawCase.getDriver().getIdDriver() : null)
                .build();
    }

    // --- FILE ---
    public FileDTO mapToFileEmployeeDTO(File file) {
        if (file == null) return null;
        return FileDTO.builder()
                .fileId(file.getFileId())
                .fileType(file.getFileType())
                .uploadedAt(file.getUploadedAt())
                .numberCase(file.getLawCase().getNumberCase())
                .build();
    }

    public FileDTO mapToFileCaseDTO(File file) {
        if (file == null) return null;
        return FileDTO.builder()
                .fileId(file.getFileId())
                .fileType(file.getFileType())
                .uploadedAt(file.getUploadedAt())
                .build();
    }

    // --- REPORT ---
    public ReportDTO mapToReportDTO(Report report) {
        if (report == null) return null;
        return ReportDTO.builder()
                .reportId(report.getReportId())
                .generatedAt(report.getGeneratedAt())
                .reportType(report.getReportType())
                .filePath(report.getFilePath())
                .userId(report.getAccountant() != null ? report.getAccountant().getUserId() : null)
                .build();
    }

    // --- CONTACT ---
    public ContactDTO mapToContactDTO(Contact contact) {
        if (contact == null) return null;
        return ContactDTO.builder()
                .idMessage(contact.getIdMessage())
                .contactDate(contact.getContactDate())
                .contactType(contact.getContactType())
                .result(contact.getResult())
                .caseNumber(contact.getLawCase() != null ? contact.getLawCase().getNumberCase() : null)
                .build();
    }

    public ContactDTO mapToContactCaseDTO(Contact contact) {
        if (contact == null) return null;
        return ContactDTO.builder()
                .idMessage(contact.getIdMessage())
                .contactDate(contact.getContactDate())
                .contactType(contact.getContactType())
                .result(contact.getResult())
                .build();
    }
}
