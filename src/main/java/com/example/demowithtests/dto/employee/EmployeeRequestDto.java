package com.example.demowithtests.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@ToString
public class EmployeeRequestDto {

    @NotNull
    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long")
    @Schema(description = "Name of an employee.", example = "Sergio", required = true)
    public String name;

    @Email
    @NotNull
    @Schema(description = "Email of an employee.", example = "123@gmail.com", required = true)
    public String email;

//    @NotNull
    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long")
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
