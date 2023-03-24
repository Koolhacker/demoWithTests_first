package com.example.demowithtests.dto;

import com.example.demowithtests.domain.Family;
import com.example.demowithtests.domain.Photo;
import com.example.demowithtests.domain.PhotoByHands;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class EmployeeDto {
    public String name;
    public String email;

    public String country;
    public Integer age;
    public Boolean isDeleted = Boolean.FALSE;

//    public Date today = Date.from(Instant.now());
    public Set<AddressDto> addresses = new HashSet<>();

    public Set<PhotoDto> photos = new HashSet<>();
    public Set<PhotoByHandsDto> photoByHands = new HashSet<>();

    public Set<FindBaggaDto> findBaggas = new HashSet<>();

    public Set<FamilyDto> families = new HashSet<>();


}
