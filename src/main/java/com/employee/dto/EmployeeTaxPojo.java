package com.employee.dto;

public class EmployeeTaxPojo {
	private Long id;
	private String firstName;
	private String lastName;
	private double yearlySalary;
	private double taxAmount;
	private double cessAmount;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public double getYearlySalary() {
		return yearlySalary;
	}
	public void setYearlySalary(double yearlySalary) {
		this.yearlySalary = yearlySalary;
	}
	public double getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}
	public double getCessAmount() {
		return cessAmount;
	}
	public void setCessAmount(double cessAmount) {
		this.cessAmount = cessAmount;
	}
}
