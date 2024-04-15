package com.spm.vasylyshyn.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spm.vasylyshyn.enums.CounterType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Device {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numberOfDevice;

    private String cantoraName;

    private CounterType counterType;
    private String address;
    private Long frequency;
    private String password;
    private Integer price;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User owner;
    @OneToMany(mappedBy="device")
    private List<Measurement> measurements = new ArrayList<>();

    @Column(updatable = false)

    private LocalDateTime createdDate;

    @PrePersist
    protected void onCreate(){
        this.createdDate = LocalDateTime.now();
    }






}
