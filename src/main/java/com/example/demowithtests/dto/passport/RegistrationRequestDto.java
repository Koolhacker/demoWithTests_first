package com.example.demowithtests.dto.passport;

import com.example.demowithtests.domain.Registration;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

//@Data
@Getter
@Setter
public class RegistrationRequestDto {
    public String countryRegistration;
    public String cityRegistration;
    public String streetRegistration;
    public LocalDate dateRegistration;
    public Boolean isActive = Boolean.TRUE;

}
