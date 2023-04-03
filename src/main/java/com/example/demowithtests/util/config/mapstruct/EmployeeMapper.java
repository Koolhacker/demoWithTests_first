package com.example.demowithtests.util.config.mapstruct;

import com.example.demowithtests.domain.*;
import com.example.demowithtests.dto.employee.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee employeeDtoToEmployee(EmployeeDto employeeDto);
    EmployeeDto employeeToEmployeeDto(Employee employee);
//    @Mapping(target = "passport", source = "employee.passport")
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
    PhotoByHands map (PhotoByHandsDto photo);
    PhotoByHandsDto map (PhotoByHands photoByHands);

}
