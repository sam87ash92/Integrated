package com.company.exchange.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "dept_id")
	private int dept_id;

	@Column(name = "dept_name")
	private String dept_name;

	@Column(name = "location")
	private String location;

	public Department() {
	}

	public Department(int dept_id, String dept_name, String location) {
		this.dept_id = dept_id;
		this.dept_name = dept_name;
		this.location = location;
	}

	public int getDept_id() {
		return dept_id;
	}

	public String getDept_name() {
		return dept_name;
	}

	public String getLocation() {
		return location;
	}
}
