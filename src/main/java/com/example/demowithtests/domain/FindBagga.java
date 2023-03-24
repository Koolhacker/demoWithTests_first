package com.example.demowithtests.domain;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

//@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "findBaggas")
public class FindBagga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nameBagga;
    private Integer numberRequest;
    private int numberResponce;
    private Date createDate = Date.from(Instant.now());
    private Boolean isVisible = Boolean.TRUE;
    private Boolean isVisiblePrimitive = Boolean.TRUE;

}
