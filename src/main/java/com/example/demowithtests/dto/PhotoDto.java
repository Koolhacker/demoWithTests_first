package com.example.demowithtests.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

//@Data
@Getter
@Setter
public class PhotoDto {

    public String linkPhoto;
    public Integer xHigh;;
    public Integer yLength;
    public Date createDate = Date.from(Instant.now());
    public Boolean isVisible = Boolean.TRUE;
}
