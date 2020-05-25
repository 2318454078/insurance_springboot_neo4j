package com.appleyk.Dao;
import com.appleyk.pojo.Insurance_mysql;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InsuranceDao_mysql extends JpaRepository<Insurance_mysql,Integer> {
    List<Insurance_mysql> findByInsurancecompanyid(Integer company_id);
}
