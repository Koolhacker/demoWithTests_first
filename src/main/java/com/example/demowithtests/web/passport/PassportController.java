package com.example.demowithtests.web.passport;


import com.example.demowithtests.dto.passport.PassportRequestDto;
import com.example.demowithtests.dto.passport.PassportResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

public interface PassportController {

    @Operation(summary = "This is endpoint to create new passport", description = "Create request to add a new passport.", tags = {"Passport"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The passport is successfully  added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified passport request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    @PostMapping("/passports")
    @ResponseStatus(HttpStatus.CREATED)
    PassportResponseDto savePassport(@RequestBody PassportRequestDto passportRequestDto);

    @Operation(summary = "This is endpoint to get list of passports")
    @GetMapping("/passports")
    @ResponseStatus(HttpStatus.OK)
    List<PassportResponseDto> getAllPassports();

    @Operation(summary = "This is endpoint to get passport by id")
    @GetMapping("/passports/{id}")
    @ResponseStatus(HttpStatus.OK)
    PassportResponseDto getPassportById(@PathVariable Integer id);

    @Operation(summary = "This is endpoint to update passport by id")
    @PutMapping("/passports/{id}")
    @ResponseStatus(HttpStatus.OK)
    PassportResponseDto refreshPassport(@PathVariable("id") Integer id, @RequestBody PassportRequestDto passportRequestDto);


}
