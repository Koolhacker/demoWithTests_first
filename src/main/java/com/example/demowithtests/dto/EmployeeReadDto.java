package com.example.demowithtests.dto;

import com.example.demowithtests.domain.Address;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class EmployeeReadDto { // !!!!!!!!!! This will see user  !!!!!!!!!!!


    public String name;
    public String email;

//  public String string;  // work, initialise - null

    public String country;

    public Date today = Date.from(Instant.now());

//    public Set<AddressDto> addresses = new HashSet<>();

    public Set<PhotoDto> photos = new HashSet<>();
}
