package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.model.Department;
import com.example.repository.DepartmentRepository;
import com.example.service.DepartmentService;

@Controller
public class DepartmentController {

	@Autowired
	private DepartmentRepository depRepo;

	@Autowired
	private DepartmentService service;

	@GetMapping("/department")
	public String listDepartments(Model model) {
//		List<Department> listDepartments = depRepo.findAll();
//		model.addAttribute("listDepartments", listDepartments);
		String keyword = null;
		return listDepartmentsByPage(model, 1, "did", "asc", keyword);
	}

	@GetMapping("/department/new")
	public String showNewDepartmentForm(Model model) {
		model.addAttribute("department", new Department());
		return "department_form";
	}

	@PostMapping("/department/add")
	public String saveDepartment(Department department, HttpServletRequest request) {
		depRepo.save(department);
		return "redirect:/department";
	}

	@GetMapping("/department/edit/{did}")
	public String editDepartment(@PathVariable("did") int did, Model model) {
		Department department = depRepo.findById(did).get();
		model.addAttribute("department", department);
		return "department_form";
	}

	@GetMapping("/department/delete/{did}")
	public String deleteDepartment(@PathVariable("did") int did, Model model) {
		depRepo.deleteById(did);
		return "redirect:/department";
	}

	@GetMapping("/dpage/{pageNumber}")
	public String listDepartmentsByPage(Model model, @PathVariable("pageNumber") int currentPage,
			@Param("sortField") String sortField, @Param("sortDir") String sortDir, @Param("keyword") String keyword) {
		Page<Department> page = service.listAll(currentPage, sortField, sortDir, keyword);
		Long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();
		List<Department> listDepartments = page.getContent();

		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("listDepartments", listDepartments);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("keyword", keyword);

		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		model.addAttribute("reverseSortDir", reverseSortDir);
		return "department";
	}

}
