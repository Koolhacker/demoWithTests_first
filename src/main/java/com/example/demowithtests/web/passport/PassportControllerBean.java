package com.example.demowithtests.web.passport;

import com.example.demowithtests.domain.Passport;
import com.example.demowithtests.dto.passport.PassportRequestDto;
import com.example.demowithtests.dto.passport.PassportResponseDto;
import com.example.demowithtests.service.passport.PassportService;
import com.example.demowithtests.util.config.mapstruct.PassportMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Passport", description = "Passport API")

public class PassportControllerBean implements PassportController {

    private final PassportService passportService;
    private final PassportMapper passportMapper;

    @Override
    public PassportResponseDto savePassport(PassportRequestDto passportRequestDto) {
        log.info("*** method savePassport >>>  START ");
        Passport passport = passportMapper.toPassport(passportRequestDto);
        PassportResponseDto passportResponseDto = passportMapper.toResponseDto(passportService.create(passport));
        log.info("*** method savePassport >>>  FINISH ");
        return passportResponseDto;
    }

    @Override
    public List<PassportResponseDto> getAllPassports() {
        log.info("*** method getAllPassports >>>  START ");
        List<Passport> passports = passportService.getAll();
        List<PassportResponseDto> passportResponseDtos = new ArrayList<>();
        for (Passport passport : passports) {
            passportResponseDtos.add(passportMapper.toResponseDto(passport));
        }
        log.info("*** method getAllPassports >>>  FINISH ");
        return passportResponseDtos;
    }

    @Override
    public PassportResponseDto getPassportById(String id) {
        log.info("*** method getPassportById >>>  START ");
        Passport passport = passportService.getById(id);
        PassportResponseDto passportResponseDto = passportMapper.toResponseDto(passport);
        log.info("*** method getPassportById >>>  FINISH ");
        return passportResponseDto;
    }

    @Override
    public PassportResponseDto refreshPassport(String id, PassportRequestDto passportRequestDto) {
        log.info("*** method refreshPassport >>>  START ");
        PassportResponseDto passportResponseDto = passportMapper
                .toResponseDto(passportService.updateById((id), passportMapper.toPassport(passportRequestDto)));
        log.info("*** method refreshPassport >>>  FINISH ");
        return passportResponseDto;
    }
}
