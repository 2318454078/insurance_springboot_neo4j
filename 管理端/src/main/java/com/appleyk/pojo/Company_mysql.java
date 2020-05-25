package com.appleyk.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "company")
public class Company_mysql {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int company_id;
    private String company_name;
    private String company_introduction;
}
