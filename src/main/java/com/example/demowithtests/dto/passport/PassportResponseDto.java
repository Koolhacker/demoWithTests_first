package com.example.demowithtests.dto.passport;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@Data
@ToString
public class PassportResponseDto {

    public Integer id;
    public final UUID serialNumber = UUID.randomUUID();
    public String firstName;
    public String secondName;
    public LocalDate birthDate;
    public Set<RegistrationResponseDto> registrations = new HashSet<>();
}
