package com.employee.serviceI;



import com.employee.dto.EmployeePojo;
import com.employee.dto.EmployeeTaxPojo;
import com.employee.model.Employee;


public interface EmployeeServiceI {
	public Employee saveEmployee(EmployeePojo emp);
	public EmployeeTaxPojo findTaxDetails(Long id);
}
