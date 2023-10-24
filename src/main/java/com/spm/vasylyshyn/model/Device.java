package com.spm.vasylyshyn.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.spm.vasylyshyn.enums.CType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    private Integer price;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "device")
    private List<DisplayCount> displayCounts = new ArrayList<>();

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @PrePersist
    protected void onCreate(){
        this.createdDate = LocalDateTime.now();

    }






}
