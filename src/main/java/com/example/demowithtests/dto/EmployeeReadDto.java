package com.example.demowithtests.dto;

import com.example.demowithtests.domain.Address;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class EmployeeReadDto {

    public String email;
    public String name;
    public String country;

    public Date today = Date.from(Instant.now());

    public Set<AddressDto> addresses = new HashSet<>();
}
