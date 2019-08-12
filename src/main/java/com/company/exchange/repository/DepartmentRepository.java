package com.company.exchange.repository;

import org.springframework.data.repository.CrudRepository;

import com.company.exchange.model.Department;

public interface DepartmentRepository extends CrudRepository<Department, Integer> {
	
}
