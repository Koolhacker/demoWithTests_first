package com.example.demowithtests.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "families")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String haslo;
    private String vidpovid;
    private Integer kids;
    private Integer cats;
    private Integer dogs;
    private Integer parrots;
}
