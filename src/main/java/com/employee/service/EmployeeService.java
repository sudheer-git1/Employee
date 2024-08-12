package com.employee.service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.dto.EmployeePojo;
import com.employee.dto.EmployeeTaxPojo;
import com.employee.exception.ValidationException;
import com.employee.model.Employee;
import com.employee.repo.EmployeeRepository;
import com.employee.serviceI.EmployeeServiceI;

@Service
public class EmployeeService implements EmployeeServiceI {
	@Autowired
	private EmployeeRepository employeeRepo;

	public Employee saveEmployee(EmployeePojo emp) {
		Employee finalEmp = new Employee();

		if (emp.getFirstName() == null) {
			throw new ValidationException("First name is mandatory");
		} else {
			finalEmp.setFirstName(emp.getFirstName());
		}

		if (emp.getLastName() == null) {
			throw new ValidationException("Last name is mandatory");
		} else {
			finalEmp.setLastName(emp.getLastName());
		}

		if (emp.getEmail() == null) {
			throw new ValidationException("Mail id is mandatory");
		} else if (emp.getEmail() != null) {

			Pattern pattern = Pattern.compile("^(.+)@(.+)$");
			Matcher matcher = pattern.matcher(emp.getEmail());
			if (!matcher.matches()) {
				throw new ValidationException("Give the valid mailId ");
			} else {
				finalEmp.setEmail(emp.getEmail());
			}
		}

		if (emp.getSalary() == 0) {
			throw new ValidationException("Salary could not be empty");
		} else {
			finalEmp.setSalary(emp.getSalary());
		}

		if (emp.getDoj() == null) {
			throw new ValidationException("Date of joining could not be empty");
		} else {
			finalEmp.setDoj(emp.getDoj());
		}

		if (emp.getPhoneNumbers() != null) {
			String finalNumber = "";
			for (String num : emp.getPhoneNumbers()) {
				Long number;
				if (num.length() < 10 || num.length() > 10) {
					throw new ValidationException("Moblie number must be 10 numbers only");
				}

				try {
					number = Long.parseLong(num);
				} catch (NumberFormatException e) {
					throw new ValidationException("Give valid mobile number");
				}
				finalNumber = finalNumber + number + ",";
			}
			finalEmp.setPhoneNumbers(finalNumber);
		} else {
			throw new ValidationException("Phone Numbers could not be empty");
		}
		return employeeRepo.save(finalEmp);
	}

	public EmployeeTaxPojo findTaxDetails(Long id) {
		Optional<Employee> opt = employeeRepo.findById(id);
		EmployeeTaxPojo pojo = new EmployeeTaxPojo();

		if (opt.isPresent()) {
			Employee emp = opt.get();
			pojo.setFirstName(emp.getFirstName());
			pojo.setLastName(emp.getLastName());
			pojo.setYearlySalary(emp.getSalary() * 12);
			if (pojo.getYearlySalary() <= 250000)
				pojo.setTaxAmount(0);
			else if (pojo.getYearlySalary() > 250000 && pojo.getYearlySalary() <= 500000) {
				Double tax = pojo.getYearlySalary() * 0.05;
				pojo.setTaxAmount(tax);
			} else if (pojo.getYearlySalary() > 500000 && pojo.getYearlySalary() <= 1000000) {
				Double tax = pojo.getYearlySalary() * 0.1;
				pojo.setTaxAmount(tax);
			} else if (pojo.getYearlySalary() > 1000000) {
				Double tax = pojo.getYearlySalary() * 0.2;
				pojo.setTaxAmount(tax);
			}
			if (pojo.getYearlySalary() > 2500000) {
				Double cess = pojo.getYearlySalary() * 0.02;
				pojo.setCessAmount(cess);
			} else {
				pojo.setCessAmount(0);
			}
		} else {
			throw new ValidationException("Employee not found with the given id");
		}
		return pojo;
	}

}
