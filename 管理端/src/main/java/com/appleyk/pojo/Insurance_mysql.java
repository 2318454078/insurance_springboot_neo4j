package com.appleyk.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = " insurance")
public class Insurance_mysql {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int insurance_id;

    private String insurance_name;

    private int insurance_toubaonianling;

    private String insurance_kuanxianqi;

    private String insurance_youyuqi;

    private String insurance_zerentiaokuan;

    private String insurance_mianzetiaokuan;

    private String insurance_jiaofeifangshi;

    private String insurance_baoxianqijian;

    private String insurance_shouyiren;
    @Column(name = "insurance_companyid")
    private int insurancecompanyid;

    private int insurance_typeid;


}
