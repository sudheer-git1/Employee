package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.dto.EmployeePojo;
import com.employee.dto.EmployeeTaxPojo;
import com.employee.model.Employee;
import com.employee.serviceI.EmployeeServiceI;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	@Autowired	
	private EmployeeServiceI employeeService;	
		
	@PostMapping("/employees")
	public ResponseEntity<?> saveEmployee(@RequestBody EmployeePojo emp){
		
			Employee e=employeeService.saveEmployee(emp);
			return new  ResponseEntity<Employee>(e,HttpStatus.OK);
	}
	@GetMapping("/employees/{employeeId}/tax-deductions")
	public ResponseEntity<?> getEmployees(@PathVariable Long employeeId){
		try {
			EmployeeTaxPojo list=employeeService.findTaxDetails(employeeId);
			return new  ResponseEntity<EmployeeTaxPojo>(list,HttpStatus.OK);
		} catch (Exception e) {
			
			return new  ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}  
}
