package com.example.demowithtests.util.config;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.EmployeeDto;
import com.example.demowithtests.dto.EmployeeReadDto;
import org.mapstruct.Mapping;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {
    @Mapping(target = "name", source = "employeeDto.name")
    @Mapping(target = "email", source = "employeeDto.email")
    @Mapping(target = "country", source = "employeeDto.country")
    @Mapping(target = "age", source = "employeeDto.age")
    Employee employeeDtoToEmployee(EmployeeDto employeeDto);

    @Mapping(target = "name", source = "employee.name")
    @Mapping(target = "email", source = "employee.email")
    @Mapping(target = "country", source = "employee.country")
    @Mapping(target = "age", source = "employee.age")
    EmployeeDto employeeToEmployeeDto(Employee employee);

    @Mapping(target = "name", source = "employee.name")
    @Mapping(target = "email", source = "employee.email")
    @Mapping(target = "country", source = "employee.country")
    EmployeeReadDto employeeToEmployeeReadDto (Employee employee);


    Employee employeeReadDtoToEmployee(EmployeeReadDto employeeReadDto);
}
