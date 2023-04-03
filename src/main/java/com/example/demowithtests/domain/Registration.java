package com.example.demowithtests.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "registrations")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String countryRegistration;
    private String cityRegistration;
    private String streetRegistration;
    private LocalDate dateRegistration;
    private Boolean isActive = Boolean.TRUE;
}
