package com.example.demowithtests.web.passport;

import com.example.demowithtests.dto.passport.PassportRequestDto;
import com.example.demowithtests.dto.passport.PassportResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface PassportController {
    @Operation(summary = "This is endpoint to create new passport")
    @PostMapping("/passports")
    @ResponseStatus(HttpStatus.CREATED)
    PassportResponseDto savePassport(@RequestBody PassportRequestDto passportRequestDto);

    @Operation(summary = "This is endpoint to get list of passports")
    @GetMapping("/passports")
    @ResponseStatus(HttpStatus.CREATED)
    List<PassportResponseDto> getAllPassports();

    @Operation(summary = "This is endpoint to get passport by id")
    @GetMapping("/passports/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    PassportResponseDto getPassportById(@PathVariable String id);

    @Operation(summary = "This is endpoint to update passport by id")
    @PutMapping("/passports/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    PassportResponseDto refreshPassport(@PathVariable("id") Integer id, @RequestBody PassportRequestDto passportRequestDto);

}
