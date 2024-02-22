package com.spm.vasylyshyn.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class Address {
    private String region;
    private String city;
    private String street;
    private String number;
}
