package com.example.demowithtests.domain;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "photoByHands")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder

public class PhotoByHands {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String linkPhoto;
    private Integer high;
    private Integer length;
    private Date createDate = Date.from(Instant.now());
    private Boolean isVisible = Boolean.TRUE;


}
