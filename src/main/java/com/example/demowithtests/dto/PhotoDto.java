package com.example.demowithtests.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

//@Data
@Getter
@Setter
public class PhotoDto {

    @Schema(description = "Name of an employee.", example = "Sergio", required = true)
    public String linkPhoto;

    @Schema(description = "Name of an employee.", example = "Sergio", required = true)
    public Integer high;

    @Schema(description = "Name of an employee.", example = "Sergio", required = true)
    public Integer length;

    @Schema(description = "Name of an employee.", example = "Sergio", required = true)
    public Date createDate = Date.from(Instant.now());

    @Schema(description = "Name of an employee.", example = "Sergio", required = true)
    public Boolean isVisible = Boolean.TRUE;
}
