package net.unit8.example.entity;

import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Data
public class Address implements Serializable {
    private String postalCd;
    private String prefectureCd;
    private String city;
    private String line1;
    private String line2;
}
