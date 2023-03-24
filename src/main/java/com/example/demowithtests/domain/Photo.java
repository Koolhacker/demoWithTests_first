package com.example.demowithtests.domain;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "photos")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String linkPhoto;
    private Integer xHigh;
    private Integer yLength;
    private Date createDate = Date.from(Instant.now());
    private Boolean isVisible = Boolean.TRUE;

}
