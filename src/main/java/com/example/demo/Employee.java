package com.example.demo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Employee {
	@Id
    @GeneratedValue
    private Long id;
    private String name;
    private String lastName;
    private String birthdate;
    
    public Employee() {}
    public Employee(String name,  String lastName, String birthdate) { 
    	this.name = name;
    	this.lastName = lastName;
    	this.birthdate = birthdate;
    }
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="gender_id")
    private Gender gender;
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="job_id")
    private Job job;

	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
    
    
}
