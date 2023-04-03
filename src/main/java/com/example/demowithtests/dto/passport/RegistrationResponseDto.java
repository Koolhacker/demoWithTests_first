package com.example.demowithtests.dto.passport;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

//@Data
@Getter
@Setter
public class RegistrationResponseDto {
    public String countryRegistration;
    public String cityRegistration;
    public String streetRegistration;
    public LocalDate dateRegistration;

}
