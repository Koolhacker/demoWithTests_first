package com.example.demowithtests.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

//@Getter
//@Setter
public class AddressDto {

    public Boolean addressHasActive = Boolean.TRUE;

    public String country;
    public String city;
    public String street;

    public Date date = Date.from(Instant.now());
}

