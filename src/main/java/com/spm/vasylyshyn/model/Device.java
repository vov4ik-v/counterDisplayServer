package com.spm.vasylyshyn.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spm.vasylyshyn.enums.CounterType;
import com.spm.vasylyshyn.enums.Regularity;
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
    private Long id;

    @Column(updatable = false,nullable = false,unique = true)
    private Long serialNumber;

    private String name;

    private String cantoraName;

    @Column(nullable = false)
    private Boolean isCalibrated;

    @OneToMany(mappedBy="device")
    private List<Measurement> measurements = new ArrayList<>();

//    private StatisticSettings statisticSettings;



    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User owner;

    private CounterType counterType;
    private Address address;






}
