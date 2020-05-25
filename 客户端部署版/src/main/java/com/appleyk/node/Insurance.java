package com.appleyk.node;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@NodeEntity
public class Insurance extends BaseEntity{

    private Long in_id;
    private String in_name;
    private String in_toubaonianling;
    private String in_kuanxianqi;
    private String in_youyuqi;
    private String in_zerentiaokuan;
    private String in_mianzetiaokuan;
    private String in_jiaofeifangshi;
    private String in_baoxianqijian;
    private String in_shouyiren;
    //private String in_companyid;
    //private String in_typeid;

    @Relationship(type = "insurance_to_type")
    @JsonProperty("保险类型")
    private List<Type> types;


    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public List<Company> getCompany() {
        return company;
    }

    public void setCompany(List<Company> company) {
        this.company = company;
    }

    @Relationship(type = "insurance_to_company")
    @JsonProperty("保险公司")
    private List<Company> company;

    public  Insurance(){

    }

    public Long getIn_id(){
        return in_id;
    }
    public void setIn_id(Long id){
        this.in_id = id;
    }

    public String getIn_name(){return in_name;}
    public void setIn_name(String name){
        this.in_name = name;
    }

    public String getIn_toubaonianling(){
        return in_toubaonianling;
    }
    public void setIn_toubaonianling(String age){
        this.in_toubaonianling = age;
    }

    public String getIn_kuanxianqi() {
        return in_kuanxianqi;
    }
    public void setIn_kuanxianqi(String Str){
        this.in_kuanxianqi = Str;
    }

    public String getIn_youyuqi(){
        return in_youyuqi;
    }
    public void setIn_youyuqi(String Str){
        this.in_youyuqi = Str;
    }

    public String getIn_zerentiaokuan(){return in_zerentiaokuan; }
    public  void setIn_zerentiaokuan(String Str){
        this.in_zerentiaokuan = Str;
    }

    public String getIn_mianzetiaokuan(){
        return in_mianzetiaokuan;
    }
    public void setIn_mianzetiaokuan(String Str){
        this.in_mianzetiaokuan = Str;
    }

    public String getIn_jiaofeifangshi(){
        return in_jiaofeifangshi;
    }
    public void setIn_jiaofeifangshi(String Str){
        this.in_jiaofeifangshi = Str;
    }

    public String getIn_shouyiren(){
        return in_shouyiren;
    }
    public void setIn_shouyiren(String Str){
        this.in_shouyiren = Str;
    }

    public String getIn_baoxianqijian() {
        return in_baoxianqijian;
    }
    public void setIn_baoxianqijian(String Str){
        this.in_baoxianqijian = Str;
    }

}