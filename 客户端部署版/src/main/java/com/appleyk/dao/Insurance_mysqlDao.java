package com.appleyk.dao;

import com.appleyk.node.Insurance_mysql;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Insurance_mysqlDao extends JpaRepository<Insurance_mysql,Integer> {
    List<Insurance_mysql> findByInsurancecompanyid(Integer company_id);
}
