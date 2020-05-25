package com.appleyk.node;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Type extends BaseEntity{


	private Long gid;
	private String  name;

	public Type(){

	}

	public Long getGid() {
		return gid;
	}

	public void setGid(Long gid) {
		this.gid = gid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

