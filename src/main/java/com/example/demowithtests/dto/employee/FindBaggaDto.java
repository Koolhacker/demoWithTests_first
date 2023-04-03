package com.example.demowithtests.dto.employee;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;


public class FindBaggaDto {

    public String nameBagga;
    public Integer numberRequest;
    public int numberResponce;
    public Date createDate = Date.from(Instant.now());
    public Boolean isVisible = Boolean.TRUE;
    public Boolean isVisiblePrimitive = Boolean.TRUE;

}
