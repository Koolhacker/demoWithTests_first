package com.example.demowithtests.dto.workplace;

import com.example.demowithtests.domain.Employee;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class WorkplaceRequestDto {
//    @NotNull(message = "Id may not be null")
//    @Schema(description = "Id of workplace.", example = "1", required = true)
//    public Integer id;

    @NotNull(message = "Name may not be null")
    @Size(min = 1, max = 42, message = "Name must be between 1 and 42 characters long")
    @Schema(description = "Name of workplace.", example = "Cabinet 44", required = true)
    public String name;

    @NotNull(message = "Address may not be null")
    @Size(min = 1, max = 42, message = "Address must be between 1 and 42 characters long")
    @Schema(description = "Address of workplace.", example = "Volodumurska str. ", required = true)
    public String address;

    @Schema(description = "isActive of workplace.", example = "true / false. ", required = true)
    public Boolean isActive = Boolean.TRUE;

    @NotNull(message = "Address may not be null")
    @Size(min = 1, max = 10, message = "Address must be between 1 and 102 characters long")
    @Schema(description = "Capacity of workplace.", example = " 5 ", required = true)
    public Integer capacity;

//    @Schema(description = " Set<Employee> ", example = " ... ", required = true)
//    public Set<Employee> users = new HashSet<>();
}
