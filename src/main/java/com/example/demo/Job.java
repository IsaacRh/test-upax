package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Job {
	@Id
    @GeneratedValue
    private Long id;
    private String name;
    private double salary;
    
    public Job() {}
    public Job(String name, double salary) { this.name = name; this.salary = salary; }
}
  