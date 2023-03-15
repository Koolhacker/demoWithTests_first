package com.example.demowithtests.dto;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class EmployeeDto {
    public String email;
    public String name;
    public String country;

//    public Date today = Date.from(Instant.now());
public Set<AddressDto> addresses = new HashSet<>();
}
