package com.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	@Query("SELECT e FROM Employee e WHERE " 
			+ " CONCAT(e.empid, e.name, e.email, e.salary, e.jobname, e.department.dname)"
			+ " LIKE %?1%")
	public Page<Employee> findAll(String keyword, Pageable pageable);

	@Query("SELECT MAX(empid) FROM Employee")
	public int maxEmp();
}
