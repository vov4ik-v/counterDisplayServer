package com.spm.vasylyshyn.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisplayCount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long displayCount;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;

    @PrePersist
    protected void onCreate(){
        this.createdDate = LocalDateTime.now();

    }

}
