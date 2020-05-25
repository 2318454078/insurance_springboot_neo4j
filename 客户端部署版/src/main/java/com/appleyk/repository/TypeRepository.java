package com.appleyk.repository;

import com.appleyk.node.Type;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TypeRepository extends Neo4jRepository<Type, Long>{
	
	 @Query("MATCH (n:Type) where n.name={name} return n")
	 List<Type> getTypes(@Param("name") String name);
	
	 List<Type> findById(@Param("name") String name);
}
