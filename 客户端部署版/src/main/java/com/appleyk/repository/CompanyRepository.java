package com.appleyk.repository;

import com.appleyk.node.Company;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyRepository extends Neo4jRepository<Company, Long>{
	 List<Company> findById(@Param("name") String name);
}
