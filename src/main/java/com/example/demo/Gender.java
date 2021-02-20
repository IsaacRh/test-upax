package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Gender {
	@Id
    @GeneratedValue
    private Long id;
    private String name;
    
    public Gender() {}
    public Gender(String name) { this.name = name; }
}
    