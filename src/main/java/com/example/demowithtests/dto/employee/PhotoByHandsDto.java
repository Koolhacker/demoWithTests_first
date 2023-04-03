package com.example.demowithtests.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;


//@Data
public class PhotoByHandsDto {

    public String linkPhoto;
    public Integer high;
    public Integer length;
    public Date createDate = Date.from(Instant.now());
    public Boolean isVisible = Boolean.TRUE;


}
