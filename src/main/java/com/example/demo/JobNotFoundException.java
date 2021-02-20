package com.example.demo;

public class JobNotFoundException extends RuntimeException{
	public JobNotFoundException(Long id) {
		super("job itn");
	}
}
