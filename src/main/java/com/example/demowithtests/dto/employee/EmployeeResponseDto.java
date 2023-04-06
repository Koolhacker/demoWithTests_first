package com.example.demowithtests.dto.employee;

import com.example.demowithtests.dto.passport.PassportResponseDto;
import lombok.Data;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class EmployeeResponseDto { // !!!!!!!!!! This will see user  !!!!!!!!!!!


    public String name;
    public String email;

//  public String string;  // work, initialise - null

    public String country;

    public Date today = Date.from(Instant.now());

    public Set<AddressDto> addresses = new HashSet<>();

    public Set<PhotoByHandsDto> photoByHandsDtos = new HashSet<>();
    public Set<PhotoDto> photos = new HashSet<>();

    public PassportResponseDto passport;
}
