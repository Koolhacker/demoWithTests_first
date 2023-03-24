package com.example.demowithtests.util.config;

import com.example.demowithtests.domain.*;
import com.example.demowithtests.dto.*;
import org.mapstruct.Mapping;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {
    Employee employeeDtoToEmployee(EmployeeDto employeeDto);

    EmployeeDto employeeToEmployeeDto(Employee employee);

    EmployeeReadDto employeeToEmployeeReadDto(Employee employee);

    Employee employeeReadDtoToEmployee(EmployeeReadDto employeeReadDto);

    Address addressDtoToAddress (AddressDto addressDto);

    AddressDto addressToAddressDto (Address address);

    Photo photoDtoToPhoto (PhotoDto value);

    PhotoDto PhotoToPhotoDto(Photo value);

    FindBagga map (FindBaggaDto findBaggaDto);

    FindBaggaDto map (FindBagga findBagga);

    Family map (FamilyDto familyDto);

    FamilyDto map(Family family);
}
