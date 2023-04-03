package com.example.demowithtests.util.config.mapstruct;

import com.example.demowithtests.domain.Passport;
import com.example.demowithtests.domain.Registration;
import com.example.demowithtests.dto.passport.PassportRequestDto;
import com.example.demowithtests.dto.passport.PassportResponseDto;
import com.example.demowithtests.dto.passport.RegistrationRequestDto;
import com.example.demowithtests.dto.passport.RegistrationResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PassportMapper {

    Passport toPassport(PassportRequestDto passportRequestDto);

    Passport toPassport(PassportResponseDto passportResponseDto);

    PassportRequestDto toRequestDto(Passport passport);

    PassportResponseDto toResponseDto(Passport passport);

    RegistrationRequestDto toRegistrationRequestDto(Registration registration);

    RegistrationResponseDto toRegistrationResponseDto(Registration registration);

    Registration toRegistrationFromRequestDto(RegistrationRequestDto registrationRequestDto);

    Registration toRegistrationFromResponseDto(RegistrationResponseDto registrationResponseDto);

}
