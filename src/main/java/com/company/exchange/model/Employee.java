package com.company.exchange.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Employee")
public class Employee {

	@Id
	@Column(name = "")
	private int empId;

	@Column(name = "")
	private String firstName;

	@Column(name = "")
	private String lastName;

	@Column(name = "")
	private String email;

	@Column(name = "")
	private String gender;

	@Column(name = "")
	private String department;

	public Employee() {
	}

	public Employee(int empId, String firstName, String lastName, String email, String gender, String department) {
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.department = department;
	}

	public int getEmpId() {
		return empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getGender() {
		return gender;
	}

	public String getDepartment() {
		return department;
	}

}
