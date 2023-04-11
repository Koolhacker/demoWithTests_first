package com.example.demowithtests.web.workplace;

import com.example.demowithtests.dto.passport.PassportRequestDto;
import com.example.demowithtests.dto.passport.PassportResponseDto;
import com.example.demowithtests.dto.workplace.WorkplaceRequestDto;
import com.example.demowithtests.dto.workplace.WorkplaceResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface WorkplaceController {

    @Operation(summary = "This is endpoint to create new workplace", description = "Create request to add a new workplace.", tags = {"Workplace"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The workplace is successfully  added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified workplace request not found."),
            @ApiResponse(responseCode = "409", description = "Workplace already exists")})
    @PostMapping("/workplace")
    @ResponseStatus(HttpStatus.CREATED)
    WorkplaceResponseDto saveWorkplace(@RequestBody WorkplaceRequestDto workplaceRequestDto);

    @Operation(summary = "This is endpoint to get list of workplaces")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The list of workplaces is successfully created."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified list of workplaces request not found."),
            @ApiResponse(responseCode = "409", description = "List of workplaces already exists")})
    @GetMapping("/workplace")
    @ResponseStatus(HttpStatus.OK)
    List<WorkplaceResponseDto> getAllWorkplaces();

    @Operation(summary = "This is endpoint to get workplace by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The workplace is successfully  got from database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified workplace request not found."),
            @ApiResponse(responseCode = "409", description = "Workplace already exists")})
    @GetMapping("/workplace/{id}")
    @ResponseStatus(HttpStatus.OK)
    WorkplaceResponseDto getWorkplaceById(@PathVariable Integer id);
}
