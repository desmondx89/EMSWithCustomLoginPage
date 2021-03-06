package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.model.Department;
import com.example.repository.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository depRepo;
	
	public Page<Department> listAll(int pageNumber, String sortField, String sortDir, String keyword) {
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(pageNumber - 1, 5, sort);
		if (keyword != null) {
			return depRepo.findAll(keyword, pageable);
		}
		return depRepo.findAll(pageable);

	}

	public void save(Department department) {
		depRepo.save(department);
	}

	public Department get(int did) {
		return depRepo.findById(did).get();
	}

	public void delete(int did) {
		depRepo.deleteById(did);
	}

}
