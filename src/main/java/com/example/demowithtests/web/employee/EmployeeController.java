package com.example.demowithtests.web.employee;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.employee.EmployeeRequestDto;
import com.example.demowithtests.dto.employee.EmployeeResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

public interface EmployeeController {

    @Operation(summary = "This is endpoint to add a new employee.", description = "Create request to add a new employee.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The new employee is successfully created and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    EmployeeRequestDto saveEmployee(@RequestBody EmployeeRequestDto employeeRequestDto);
    @Operation(summary = "This is endpoint for show employee by id.", description = "You need know id.", tags = {"Employee"})
    EmployeeResponseDto getEmployeeById(@PathVariable String id);
    @Operation(summary = "This is endpoint for show all employees.", tags = {"Employee"})
    List<Employee> getAllUsers();

    @Operation(summary = "This is endpoint update employee by id.", tags = {"Employee"})
    Employee refreshEmployee(@PathVariable("id") Integer id, @RequestBody Employee employee);

    @Operation(summary = "Don`t touch this button ))", tags = {"Employee"})
    void removeAllUsers();

    @Operation(summary = "Fill the gap Age for all Employee", tags = {"Employee"})
    void processorAgeSet();

    @Operation(summary = "This is endpoint to add employee passport by params")
    @PutMapping("/addPassport")
    @ResponseStatus(HttpStatus.OK)
    EmployeeResponseDto addPassport(@RequestParam  Integer employeeId, @RequestParam Integer passportId);

    @Operation(summary = "This is endpoint to add employee passport by pathVariable")
    @PatchMapping("/users/{uid}/passports/{pid}")
    @ResponseStatus(HttpStatus.OK)
    EmployeeResponseDto addPassportSafely(@PathVariable("uid")  Integer employeeId, @PathVariable("pid") Integer passportId);

    @Operation(summary = "This is endpoint to add passport.", description = "Create request to add a new employee.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The passport is successfully added to employee and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    @PatchMapping("/users/passports/{id}")
    @ResponseStatus(HttpStatus.OK)
    EmployeeResponseDto addPassport(@PathVariable ("id") Integer employeeId);

    @Operation(summary = "This is endpoint to add workplace for an employee.", description = "Create request to add workplace for an employee.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The workplace for an employee and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee or workplace request not found."),
            @ApiResponse(responseCode = "409", description = "Employee or workplace already exists")})
    @PatchMapping("/users/employee/{eid}/workplaces/{wid}")
    @ResponseStatus(HttpStatus.OK)
    EmployeeResponseDto addWorkplace(@PathVariable ("eid") Integer employeeId, @PathVariable("wid") Integer workplaceId);

    @Operation(summary = "This is endpoint to create an employee by method save_Through_Entity_Manager.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The employee added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    @PostMapping("/saveThroughEntityManager")
    @ResponseStatus(HttpStatus.CREATED)
    void saveThroughEntityManager(@RequestBody Employee employee);

    @Operation(summary = "This is endpoint to detach an employee.",  tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The employee detached in database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee not found.")})
    @GetMapping("/detachThroughEntityManager/{eid}")
    @ResponseStatus(HttpStatus.OK)
    void detachThroughEntityManager(@PathVariable ("eid") Integer id);

    @Operation(summary = "This is endpoint to remove an employee.",  tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The employee added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee not found.")})
    @GetMapping("/removeThroughEntityManager/{eid}")
    @ResponseStatus(HttpStatus.OK)
    void removeThroughEntityManager(@PathVariable ("eid") Integer id);
}
