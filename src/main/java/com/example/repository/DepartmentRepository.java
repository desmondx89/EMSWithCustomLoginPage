package com.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
	
	@Query("SELECT d FROM Department d WHERE " + " CONCAT(d.did, d.dname, d.address)" + " LIKE %?1%")
	public Page<Department> findAll(String keyword, Pageable pageable);

}
