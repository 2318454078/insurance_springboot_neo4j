package com.appleyk.Dao;

import com.appleyk.pojo.Company_mysql;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyDao_mysql extends JpaRepository <Company_mysql,Integer>{
//    Company_mysql findCompany_mysqlByCompany_id(Integer company_id);
}
