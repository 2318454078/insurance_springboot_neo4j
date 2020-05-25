package com.appleyk.node;

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

    public int getInsurance_id() {
        return insurance_id;
    }

    public void setInsurance_id(int insurance_id) {
        this.insurance_id = insurance_id;
    }

    public String getInsurance_name() {
        return insurance_name;
    }

    public void setInsurance_name(String insurance_name) {
        this.insurance_name = insurance_name;
    }

    public int getInsurance_toubaonianling() {
        return insurance_toubaonianling;
    }

    public void setInsurance_toubaonianling(int insurance_toubaonianling) {
        this.insurance_toubaonianling = insurance_toubaonianling;
    }

    public String getInsurance_kuanxianqi() {
        return insurance_kuanxianqi;
    }

    public void setInsurance_kuanxianqi(String insurance_kuanxianqi) {
        this.insurance_kuanxianqi = insurance_kuanxianqi;
    }

    public String getInsurance_youyuqi() {
        return insurance_youyuqi;
    }

    public void setInsurance_youyuqi(String insurance_youyuqi) {
        this.insurance_youyuqi = insurance_youyuqi;
    }

    public String getInsurance_zerentiaokuan() {
        return insurance_zerentiaokuan;
    }

    public void setInsurance_zerentiaokuan(String insurance_zerentiaokuan) {
        this.insurance_zerentiaokuan = insurance_zerentiaokuan;
    }

    public String getInsurance_mianzetiaokuan() {
        return insurance_mianzetiaokuan;
    }

    public void setInsurance_mianzetiaokuan(String insurance_mianzetiaokuan) {
        this.insurance_mianzetiaokuan = insurance_mianzetiaokuan;
    }

    public String getInsurance_jiaofeifangshi() {
        return insurance_jiaofeifangshi;
    }

    public void setInsurance_jiaofeifangshi(String insurance_jiaofeifangshi) {
        this.insurance_jiaofeifangshi = insurance_jiaofeifangshi;
    }

    public String getInsurance_baoxianqijian() {
        return insurance_baoxianqijian;
    }

    public void setInsurance_baoxianqijian(String insurance_baoxianqijian) {
        this.insurance_baoxianqijian = insurance_baoxianqijian;
    }

    public String getInsurance_shouyiren() {
        return insurance_shouyiren;
    }

    public void setInsurance_shouyiren(String insurance_shouyiren) {
        this.insurance_shouyiren = insurance_shouyiren;
    }

    public int getInsurancecompanyid() {
        return insurancecompanyid;
    }

    public void setInsurancecompanyid(int insurancecompanyid) {
        this.insurancecompanyid = insurancecompanyid;
    }

    public int getInsurancetypeid() {
        return insurancetypeid;
    }

    public void setInsurancetypeid(int insurancetypeid) {
        this.insurancetypeid = insurancetypeid;
    }

    @Column(name = "insurance_companyid")
    private int insurancecompanyid;
    @Column(name = "insurance_typeid")
    private int insurancetypeid;


}