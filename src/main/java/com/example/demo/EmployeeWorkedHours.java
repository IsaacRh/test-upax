package com.example.demo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class EmployeeWorkedHours {

	@Id
    @GeneratedValue
    private Long id;
    private double workedHours;
    private Date workedDate;
    
    public EmployeeWorkedHours() {}
    public EmployeeWorkedHours(double workedHours,  Date workedDate) { 
    	this.workedHours = workedHours;
    	this.workedDate = workedDate;
    }
   
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="employe_id")
    private Employee employe;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getWorkedHours() {
		return workedHours;
	}
	public void setWorkedHours(double workedHours) {
		this.workedHours = workedHours;
	}
	public Date getWorkedDate() {
		return workedDate;
	}
	public void setWorkedDate(Date workedDate) {
		this.workedDate = workedDate;
	}
	public Employee getEmploye() {
		return employe;
	}
	public void setEmploye(Employee employe) {
		this.employe = employe;
	}
    

}
