package com.example.demo;

public class GenderNotFoundException extends RuntimeException{
	public GenderNotFoundException(Long id) {
		super("gender itn");
	}
}
