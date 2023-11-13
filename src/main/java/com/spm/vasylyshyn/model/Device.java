package com.spm.vasylyshyn.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spm.vasylyshyn.enums.CType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numberOfDevice;

    private String cantoraName;

    private CType counterType;
    private String address;
    private Long frequency;
    private String password;
    private Integer price;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User owner;
    @OneToMany(mappedBy="device")
    private List<DisplayCount> displayCounts = new ArrayList<>();

    @Column(updatable = false)
    private String createdDate;

    @PrePersist
        protected void onCreate(){
       createdDate =  ZonedDateTime
                .now(
                        ZoneId.of( "Europe/Bucharest" )
                )
                .format(
                        DateTimeFormatter
                                .ofLocalizedDateTime( FormatStyle.SHORT )
                                .withLocale(
                                        new Locale( "ro" , "RO" )   // Romanian in Romania.
                                )
                );

    }






}
