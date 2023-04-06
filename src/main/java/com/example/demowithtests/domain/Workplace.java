package com.example.demowithtests.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "workplaces")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

public class Workplace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String address;
    private Boolean isActive = Boolean.TRUE;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "workplaces")
    @JsonIgnore
    private Set<Employee> users = new HashSet<>();


}
