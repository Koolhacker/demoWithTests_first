package com.example.demowithtests.web.employee;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.employee.EmployeeDto;
import com.example.demowithtests.dto.employee.EmployeeReadDto;
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
    EmployeeDto saveEmployee(@RequestBody EmployeeDto employeeDto);
    @Operation(summary = "This is endpoint for show employee by id.", description = "You need know id.", tags = {"Employee"})
    EmployeeReadDto getEmployeeById(@PathVariable String id);
    @Operation(summary = "This is endpoint for show all employees.", tags = {"Employee"})
    List<Employee> getAllUsers();

    @Operation(summary = "This is endpoint update employee by id.", tags = {"Employee"})
    Employee refreshEmployee(@PathVariable("id") Integer id, @RequestBody Employee employee);

    @Operation(summary = "Don`t touch this button ))", tags = {"Employee"})
    void removeAllUsers();

    @Operation(summary = "Fill the gap Age for all Employee", tags = {"Employee"})
    void processorAgeSet();

    @Operation(summary = "This is endpoint to add employee passport by params")
    @PatchMapping("/addPassport")
    @ResponseStatus(HttpStatus.OK)
    EmployeeReadDto addPassport(@RequestParam  Integer employeeId, @RequestParam Integer passportId);

    @Operation(summary = "This is endpoint to add employee passport by pathVariable")
    @PatchMapping("/users/{uid}/passports/{pid}")
    @ResponseStatus(HttpStatus.OK)
    EmployeeReadDto addPassportSafely(@PathVariable("uid")  Integer employeeId, @PathVariable("pid") Integer passportId);
}
