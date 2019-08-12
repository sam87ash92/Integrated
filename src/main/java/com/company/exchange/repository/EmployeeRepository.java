package com.company.exchange.repository;

import org.springframework.data.repository.CrudRepository;

import com.company.exchange.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>{

}
