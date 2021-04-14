package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository empRepo;
	
	public Page<Employee> listAll(int pageNumber, String sortField, String sortDir, String keyword) {
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(pageNumber - 1, 10, sort);
		if (keyword != null) {
			return empRepo.findAll(keyword, pageable);
		}
		return empRepo.findAll(pageable);

	}

	public void save(Employee employee) {
		empRepo.save(employee);
	}

	public Employee get(int empid) {
		return empRepo.findById(empid).get();
	}

	public void delete(int empid) {
		empRepo.deleteById(empid);
	}

}
