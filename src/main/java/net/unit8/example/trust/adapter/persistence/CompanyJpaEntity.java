package net.unit8.example.trust.adapter.persistence;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "companies")
@Data
public class CompanyJpaEntity {
    @Id
    private String id;
    private String name;
}
