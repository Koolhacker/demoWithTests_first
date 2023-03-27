package com.example.demowithtests.dto;

import com.example.demowithtests.domain.Family;
import com.example.demowithtests.domain.Photo;
import com.example.demowithtests.domain.PhotoByHands;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class EmployeeDto {

    @Schema(description = "Name of an employee.", example = "Sergio", required = true)
    public String name;

    @Schema(description = "Email of an employee.", example = "123@gmail.com", required = true)
    public String email;

    @Schema(description = "Country of an employee.", example = "Ukraine", required = true)
    public String country;

    @Schema(description = "Age of an employee.", example = "33", required = true)
    public Integer age;

    @Schema(description = "Technical field of an employee.", example = "False", required = true)
    public Boolean isDeleted = Boolean.FALSE;

//    public Date today = Date.from(Instant.now());
    public Set<AddressDto> addresses = new HashSet<>();

    public Set<PhotoDto> photos = new HashSet<>();

    public Set<PhotoByHandsDto> photoByHands = new HashSet<>();

    public Set<FindBaggaDto> findBaggas = new HashSet<>();

    public Set<FamilyDto> families = new HashSet<>();


}
