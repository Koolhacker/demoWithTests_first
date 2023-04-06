package com.example.demowithtests.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "passports")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private final UUID serialNumber = UUID.randomUUID();
    private String firstName;
    private String secondName;
    private LocalDate birthDate;
    private Boolean isFree = Boolean.TRUE;

    @OneToOne(mappedBy = "passport")
    private Employee employee;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "passport_id")
    private Set<Registration> registrations = new HashSet<>();

}
