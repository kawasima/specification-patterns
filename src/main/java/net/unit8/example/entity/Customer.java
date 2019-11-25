package net.unit8.example.entity;

import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Data
public class Customer implements Serializable {
    public String firstName;
    public String lastName;
    public Address address;
    public CustomerClassification classification;
}
