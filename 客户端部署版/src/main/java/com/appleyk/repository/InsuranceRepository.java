package com.appleyk.repository;

import com.appleyk.node.Insurance;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InsuranceRepository extends Neo4jRepository<Insurance, Long>{

	 List<Insurance> findById(@Param("in_name") String in_name);
	 @Query("match(n:insurance)-[:insurance_to_company]->(m:Company) where n.name={in_name} return m.name")
	 List<String> getInsuranceNames();
}
