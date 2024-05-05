package com.spm.vasylyshyn.model;


import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class StatisticSettings {
    private Long statisticFrequency;
    private Float tariff;
}
